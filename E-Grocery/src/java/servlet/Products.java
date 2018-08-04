/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;
import java.sql.Connection;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Marko
 */
@WebServlet(name = "Products", urlPatterns = {"/Products"})
public class Products extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
        
        
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
        if (session.getAttribute("username") == null) {
        response.sendRedirect("login.jsp");
      } else {

        Connection conn=null;
        int cardCount = 0;
        String username = request.getSession().getAttribute("username").toString();
              if(request.getParameter("add")!=null){ 
            Map<Integer,Integer> card = (Map<Integer,Integer>)request.getSession().getAttribute("card");
            if(card != null){
                 int productId = Integer.parseInt(request.getParameter("add"));
                 if(card.containsKey(productId)){
                     card.put(productId, card.get(productId)+1);
                 } else {
                     card.put(productId, 1);
                 }
            } else {
                Map<Integer,Integer> newCard = new HashMap<>();
                int productId = Integer.parseInt(request.getParameter("add"));
                newCard.put(productId, 1);
                request.getSession().setAttribute("card", newCard); 
            }
               }
              
        Map<Integer,Integer> card = (Map<Integer,Integer>)request.getSession().getAttribute("card");
        if(card != null){
            for(Map.Entry<Integer,Integer> kvp : card.entrySet()){
         cardCount+=kvp.getValue();
            }
        }
             List<Map<String,String>> products = new ArrayList<Map<String,String>>();
             try{
             
              conn =  model.Database.getConnection();
             ResultSet rs = conn.createStatement().executeQuery("select * from products");
             while(rs.next()){
             Map<String,String> product = new HashMap<>();
             product.put("id", String.valueOf(rs.getInt("id")));
             product.put("name", rs.getString("name"));
             product.put("price", rs.getString("price"));
             product.put("quantity", rs.getString("quantity"));
             product.put("picture", rs.getString("picture"));
             products.add(product);
             }
             }catch(SQLException ex){}
            request.setAttribute("products", products);
            request.setAttribute("username", username);
            request.setAttribute("cardCount", cardCount);
            request.getSession().setAttribute("cardCount", cardCount);
            request.getRequestDispatcher("products.jsp").forward(request, response);
        
    }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
