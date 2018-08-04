/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Marko
 */
@WebServlet(name = "Card", urlPatterns = {"/card"})
public class Card extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
                HttpSession session = request.getSession(false);
        if (session.getAttribute("username") == null) {
        response.sendRedirect("login.jsp");
      } else {
        
        
        double ukupno = 0;
        String username = request.getSession().getAttribute("username").toString();
        Connection conn=null;
        if(request.getParameter("doCheckout")!=null){ 
        String adress=request.getParameter("address");
        Map<Integer,Integer> card = (Map<Integer,Integer>)request.getSession().getAttribute("card");
        

        conn =  model.Database.getConnection();
        Statement st = conn.createStatement();
        
        st.execute("insert into orders values(null,'"+adress+"','"+username+"','uncompleted',0)",Statement.RETURN_GENERATED_KEYS);
        ResultSet generatedKey = st.getGeneratedKeys();
        if(generatedKey.next()){
        int primaryKey = generatedKey.getInt(1);
        if(card!=null){
        for(Map.Entry<Integer,Integer> item : card.entrySet()){
            System.out.println("Proizvod"+item.getKey()+",komada"+item.getValue());
            st.execute("insert into order_details values("+primaryKey+","+item.getKey()+","+item.getValue()+")");
             st.execute("update products set quantity=quantity-"+item.getValue()+" where id="+item.getKey()+"");
             String ukupno2 = request.getSession().getAttribute("bill").toString();
              st.execute("update orders set bill='"+ukupno2+"' where id='"+primaryKey+"'");

        }
       
        
        }
        
        }
       request.getSession().removeAttribute("card");
        }      
        if(request.getParameter("remove")!=null){ 
        Map<Integer,Integer> card = (Map<Integer,Integer>)request.getSession().getAttribute("card");
         if(card!=null){
         int productId= Integer.parseInt(request.getParameter("remove"));
         card.put(productId, card.get(productId)-1);
         if(card.get(productId)<1){
         card.remove(productId);
         if(card.get(productId)==null){
         response.sendRedirect("Products");
         return;
         }
         }
         }    
        }   
         List<Map<String,String>> products = new ArrayList<Map<String,String>>();
         List<String> idovi = new ArrayList<String>();
         Map<Integer,Integer> card = (Map<Integer,Integer>)request.getSession().getAttribute("card");
         if(card==null){
         response.sendRedirect("successfulorder.jsp");
         return;
         }
         for(Integer productId : card.keySet()){
         idovi.add(String.valueOf(productId));
         }
         String inString = String.join(",", idovi);
          
         conn =  model.Database.getConnection();
         ResultSet rs = conn.createStatement().executeQuery("select * from products where id in ("+inString+")");
         while(rs.next()){
             Map<String,String> product = new HashMap<>();
             product.put("id", String.valueOf(rs.getInt("id")));
             product.put("name", rs.getString("name"));
             product.put("quantity",String.valueOf(card.get(rs.getInt("id"))));
             product.put("price", rs.getString("price"));
             ukupno+=rs.getDouble("price")*card.get(rs.getInt("id"));
             products.add(product);
             
         }
         request.setAttribute("ukupno", ukupno);
         request.setAttribute("username", username);
         request.setAttribute("products", products);
         request.getSession().setAttribute("bill", ukupno);
         request.getRequestDispatcher("card.jsp").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Card.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Card.class.getName()).log(Level.SEVERE, null, ex);
        }
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
