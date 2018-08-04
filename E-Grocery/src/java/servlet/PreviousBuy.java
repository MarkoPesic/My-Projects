/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
@WebServlet(name = "PreviousBuy", urlPatterns = {"/previousbuy"})
public class PreviousBuy extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
                List<Map<String,String>> kupljeno = new ArrayList<Map<String,String>>();                
                HttpSession session = request.getSession(false);
                if (session.getAttribute("username") == null) {
                 response.sendRedirect("login.jsp");
                 } else {
                String username = request.getSession().getAttribute("username").toString();
                Connection conn =null;
                conn =model.Database.getConnection(); 
                ResultSet rs = conn.createStatement().executeQuery("select * from orders join order_details on order_details.order_id = orders.id join products on order_details.product_id = products.id where username = '"+username+"'"); 
                while(rs.next()){
                    Map<String,String> kupovano = new HashMap<>();
                    kupovano.put("id", String.valueOf(rs.getInt("order_id")));
                    kupovano.put("address", rs.getString("address"));
                    kupovano.put("productid", rs.getString("product_id"));
                    kupovano.put("quantity", rs.getString("quantity"));
                    kupovano.put("name", rs.getString("name"));
                    kupovano.put("orderstatus", rs.getString("orderstatus"));
                    kupovano.put("bill", rs.getString("bill"));
                    kupljeno.add(kupovano);
                } 
         request.setAttribute("kupljeno", kupljeno);
         request.getRequestDispatcher("previousbuy.jsp").forward(request, response); 
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
