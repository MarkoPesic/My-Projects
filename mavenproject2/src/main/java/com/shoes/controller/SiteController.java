package com.shoes.controller;

 
import com.shoes.model.Category;
import com.shoes.model.CategoryDao;
import com.shoes.model.Orders;
import com.shoes.model.OrdersDao;
import com.shoes.model.Shoe;
import com.shoes.model.ShoeDao;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SiteController {
    
    @Autowired
    CategoryDao categoryDao;
    
    @Autowired
    ShoeDao shoeDao;
    
    @Autowired
    OrdersDao ordersDao;
    
    @RequestMapping("/")
    public String index(ModelMap model){ 
        List<Category> categories = categoryDao.find(); 
        model.addAttribute("categories", categories); 
        return byCategory(1, model);
    }
    
    @RequestMapping("/{id}")
    public String byCategory(@PathVariable int id, ModelMap model){ 
        List<Category> categories = categoryDao.find(); 
        List<Shoe> shoe = shoeDao.findByCategory(id);
        model.addAttribute("categories", categories); 
        model.addAttribute("shoe", shoe); 
        return "index";
    }
    
        @RequestMapping("/tobasket/{id}")
    public String toBasket(@PathVariable int id, ModelMap model){
        List<Category> categories = categoryDao.find(); 
        model.addAttribute("categories", categories); 
        Shoe shoe = shoeDao.geById(id);
        model.addAttribute("shoe", shoe);
        return "tobasket";
    }
    
     @RequestMapping("/addtobasket")
    public String addToBasket(ModelMap model, HttpServletRequest request, @RequestParam(required = true) Integer id,@RequestParam(required = true) Integer quantity){
        
        HttpSession session = request.getSession();
        HashMap<Integer,Shoe> cart;
        if(session.getAttribute("cart")==null){
            session.setAttribute("cart", new HashMap<Integer,Shoe>()); 
        }
        cart = (HashMap<Integer,Shoe>)session.getAttribute("cart");
        
        if(!cart.containsKey(id)){ 
            Shoe shoe = shoeDao.geById(id);
            shoe.quantity = quantity;
            cart.put(id,shoe);
        } else {
            Shoe shoeFromCart = cart.get(id);
            shoeFromCart.quantity+=quantity;
        }
        
        List<Category> categories = categoryDao.find(); 
        model.addAttribute("categories", categories); 
        
        return "addedtobasket";
    }
    
    @RequestMapping("/cart")
    public String cart(HttpServletRequest request, ModelMap model){
        
        List<Category> categories = categoryDao.find(); 
        model.addAttribute("categories", categories);
        
        List<Shoe> shoes = new ArrayList<Shoe>(); 
        
        HttpSession session = request.getSession();
//        int ukupno=0;
        int ukupno2 =0;
        
        if(session.getAttribute("cart")!=null){
            HashMap<Integer,Shoe> sessionShoe = (HashMap<Integer,Shoe>)session.getAttribute("cart");
            for(Map.Entry<Integer,Shoe> m : sessionShoe.entrySet()){
               shoes.add(m.getValue());
               ukupno2 +=m.getValue().getPrice()* m.getValue().getQuantity();
               
            }
        }
//        ukupno += shoes.get(0).getPrice()* shoes.get(0).getQuantity();
//        ukupno +=ukupno2;
        System.out.println(ukupno2);
        model.addAttribute("shoe", shoes);
        request.getSession().setAttribute("bill", ukupno2);
        return "cart";
    }
    
        @RequestMapping("/remove")
    public String remove(@RequestParam(required = true) int id, HttpServletRequest request, ModelMap model){ 
        List<Category> categories = categoryDao.find(); 
        model.addAttribute("categories", categories);
        
        HttpSession session = request.getSession();
        if(session.getAttribute("cart")!=null){
            HashMap<Integer,Shoe> shoes = (HashMap<Integer,Shoe>)session.getAttribute("cart");
            if(shoes.containsKey(id)){
                shoes.remove(id);
            }
        }
        
        return "remove";
    }
    
    @RequestMapping("/confirm")
    public String confirmOrder(@RequestParam(required = false) String userdata, HttpServletRequest request, ModelMap model){
        List<Category> categories = categoryDao.find(); 
        model.addAttribute("categories", categories);
        
        if(userdata==null){
             
             
        } else {
            
            StringBuilder sb = new StringBuilder();
            HttpSession session = request.getSession();
            HashMap<Integer,Shoe> sessionShoe = (HashMap<Integer,Shoe>)session.getAttribute("cart");
            sb.append("[");
            for(Map.Entry<Integer,Shoe> m : sessionShoe.entrySet()){ 
                sb.append("{\"name\":");
                sb.append(m.getValue().getName());
//                sb.append(m.getValue().getId());
                sb.append(",\"q\":");
                sb.append(m.getValue().getQuantity());
//                sb.append(",\"n\":");
//                sb.append(m.getValue().getName());
                sb.append("},");
            }
            String substr = sb.substring(0, sb.length()-1);
            substr += "]";
            Orders order = new Orders();
            order.setOrdertime(new Date(new java.util.Date().getTime()));
            order.setProducts(substr);
            order.setUserdata(userdata); 
            ordersDao.save(order);
            
            session.removeAttribute("cart");
            return "confirmsuccess";   
        }
        return "confirm";
    }
}
