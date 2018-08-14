package com.shoes.controller;

import com.shoes.model.Admin;
import com.shoes.model.AdminDao;
import com.shoes.model.Category;
import com.shoes.model.CategoryDao;
import com.shoes.model.Orders;
import com.shoes.model.OrdersDao;
import com.shoes.model.Shoe;
import com.shoes.model.ShoeDao;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.portlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    CategoryDao categoryDao;
    
    @Autowired
    ShoeDao shoeDao;
    
    @Autowired
    OrdersDao ordersDao;
    
     @Autowired
    AdminDao adminDao;
     
      
     HttpServletRequest request;
     HttpSession session;
     
        @RequestMapping("/")
    public String index(HttpSession session ){
        if(session.getAttribute("login") == null){
        return "admin/login";
        }else{
        return "admin/index";
        }
    }
    
    @RequestMapping("/categories")
    public String categories(@RequestParam(required = false) Integer id, ModelMap model, HttpSession session){
        if(session.getAttribute("login") == null){
        return "admin/login";
        }else{
        List<Category> categories = categoryDao.find(); 
        model.addAttribute("categories", categories);
        if(id!=null){
            Category selectedCategory = categoryDao.geById(id);
            model.addAttribute("selectedCategory",selectedCategory);
        }
        return "admin/categories";
    }
    }
    
        @RequestMapping("/updatecategory")
    public String updateCategory(
            @RequestParam Integer id,
            @RequestParam String name,
            @RequestParam String description,
            ModelMap model){
         
        Category selectedCategory = categoryDao.geById(id);
        selectedCategory.setName(name);
        categoryDao.update(selectedCategory);
        List<Category> categories = categoryDao.find();
        model.addAttribute("categories", categories);
        model.addAttribute("selectedCategory",selectedCategory);
        return "admin/categories";
         
    }
    
        @RequestMapping("/shoes")
    public String shoes(@RequestParam(defaultValue = "1") Integer page, ModelMap model,HttpSession session){
        if(session.getAttribute("login") == null){
        return "admin/login";
        }else{
        List<Shoe> shoes = shoeDao.findByPage(page-1);
        model.addAttribute("shoes", shoes);
        model.addAttribute("totalpages",shoeDao.pages());
        return "admin/shoes";
        }
    }
    
     @RequestMapping(value="/updateshoe", method = RequestMethod.POST)
    public String updateShoePost(
            @RequestParam Integer id, 
            @RequestParam String name,
            @RequestParam Integer price, 
            @RequestParam Integer category,
            @RequestParam MultipartFile photo,
            ModelMap model,HttpServletRequest request) throws FileNotFoundException, IOException{ 
        List<Category> categories = categoryDao.find();
         
        
        
        model.addAttribute("categories", categories);
        Shoe shoe = shoeDao.geById(id); 
        shoe.setName(name);
        
        if(photo!=null&&!photo.isEmpty()){
            String filepath = request.getServletContext().getRealPath("resources/images"); 
            FileOutputStream  fos = new FileOutputStream(filepath+"/"+photo.getOriginalFilename());
            shoe.setPhoto(photo.getOriginalFilename());
            fos.write(photo.getBytes());
            fos.close();
        }
        
        
        shoe.setCategory(category);
        shoe.setPrice(price); 
        shoeDao.update(shoe);
        model.addAttribute("shoe", shoe);
        return "admin/updateshoe";
    }
        @RequestMapping(value="/updateshoe", method=RequestMethod.GET)
    public String updateShoe(@RequestParam Integer id, ModelMap model){
        Shoe shoe = shoeDao.geById(id);
        List<Category> categories = categoryDao.find(); 
        model.addAttribute("categories", categories);
        model.addAttribute("shoe", shoe);
        return "admin/updateshoe";
    }
    
@RequestMapping(value="/login", method=RequestMethod.POST)
    public String Admin(@RequestParam String username,String password, ModelMap model, HttpServletRequest request){
    List<Admin> admins=adminDao.checkLogin(username, password);
    if ((admins != null) && (admins.size() > 0)) {
     String userlogin= admins.get(0).getUsername();
       session = request.getSession();
       request.getSession().setAttribute("login", userlogin);
      model.addAttribute("admin", userlogin);
     return "admin/index";
     }else{
    return "admin/error";
    }    
    }
    
   @RequestMapping(value="/login", method=RequestMethod.GET)
    public String Admin(ModelMap model){
    return"admin/login";
    }

        @RequestMapping(value="/orders", method=RequestMethod.GET)
    public String orders(ModelMap model, HttpSession session){
        if(session.getAttribute("login") == null){
        return "admin/login";
        }else{
        
        List<Orders> orders = ordersDao.find();
        model.addAttribute("orders", orders);
        
        return "admin/orders";
        }
    }
    
    @RequestMapping(value="/ordersdelete", method=RequestMethod.GET)
    public String ordersdeleteGet(@RequestParam Integer id,ModelMap model){
        Orders orders = ordersDao.geById(id);
        model.addAttribute("orders", orders); 
        return "admin/ordersdelete";
    }
    
    @RequestMapping(value="/ordersdelete", method=RequestMethod.POST)
    public String ordersdeletePost(@RequestParam Integer id,ModelMap model){
     List<Orders> orders = ordersDao.findById(id);
     ordersDao.delete(orders.get(0));
     return "admin/index";
    }
    

    

    @RequestMapping(value="/addshoe", method = RequestMethod.POST)
    public String addShoePost(
            @RequestParam String shoename,
            @RequestParam Integer shoeprice, 
            @RequestParam Integer shoesize, 
            @RequestParam Integer shoecategories,
            @RequestParam MultipartFile photo,
            ModelMap model,HttpServletRequest request) throws FileNotFoundException, IOException{ 
        Shoe shoe = new Shoe();
        shoe.setName(shoename);
        
        if(photo!=null&&!photo.isEmpty()){
            String filepath = request.getServletContext().getRealPath("resources/images"); 
            FileOutputStream  fos = new FileOutputStream(filepath+"/"+photo.getOriginalFilename());
            shoe.setPhoto(photo.getOriginalFilename());
            fos.write(photo.getBytes());
            fos.close();
        }
        
        
        shoe.setCategory(shoecategories);
        shoe.setSize(shoesize);
        shoe.setPrice(shoeprice); 
        shoeDao.save(shoe);
        model.addAttribute("shoe", shoe);
        return "admin/addshoe";
    }
        @RequestMapping(value="/addshoe", method=RequestMethod.GET)
    public String addShoe(ModelMap model,HttpSession session){
        if(session.getAttribute("login") == null){
        return "admin/login";
        }else{
        List<Category> categories = categoryDao.find(); 
        model.addAttribute("categories", categories);
        return "admin/addshoe";
        }
    }
    
    
    @RequestMapping(value="/delete", method=RequestMethod.GET)
    public String deleteGet(@RequestParam Integer id, ModelMap model){
        Shoe shoe = shoeDao.geById(id);
        model.addAttribute("shoe", shoe);
        return "admin/delete";
    }
    
    @RequestMapping(value="/delete", method=RequestMethod.POST)
    public String deletePost(@RequestParam Integer id,ModelMap model){
     List<Shoe> shoe = shoeDao.findById(id);
    shoeDao.delete(shoe.get(0));
     return "admin/index";
    }
    
    
        @RequestMapping(value="/addcategories", method=RequestMethod.GET)
    public String addCategoriesGet(HttpSession session){
        if(session.getAttribute("login") == null){
        return "admin/login";
        }else{
        return "admin/addcategories";
        }
    }
    
    @RequestMapping(value="/addcategories", method=RequestMethod.POST)
    public String addCategoriesPost(@RequestParam String categoryname,ModelMap model){
     Category category = new Category();
     category.setName(categoryname);
     categoryDao.save(category);
     return "admin/index";
    }
    
    
        @RequestMapping("/logout")
    public String categories(HttpSession session){
    session.invalidate();
    return "admin/login";
    }
}
