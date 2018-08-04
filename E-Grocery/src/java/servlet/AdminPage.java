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
@WebServlet(name = "AdminPage", urlPatterns = {"/adminpage"})
public class AdminPage extends HttpServlet {

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
       Connection conn=null;
       PreparedStatement ps=null;
       ResultSet rs=null;
       if (session.getAttribute("username") == null  | session.getAttribute("status").equals("0")) {
        response.sendRedirect("login.jsp");
      } else {
           
      
      if(request.getParameter("rusername")!=null){
      String rUsername = request.getParameter("rusername");
      conn =  model.Database.getConnection();
       ps = conn.prepareStatement("select * from users where username=? ");
         ps.setString(1, rUsername);
         rs = ps.executeQuery();
      if(rs.next()){
      ps=conn.prepareStatement("update users set status=0 where username=?");
      ps.setString(1, rUsername);
      ps.executeUpdate();
      response.sendRedirect("succesfulAD.jsp");
         }else{
      response.sendRedirect("unsuccessfulAD.jsp");
      }}else{
      if(request.getParameter("ausername")!=null){
      String aUsername = request.getParameter("ausername");
      conn =  model.Database.getConnection();
       ps = conn.prepareStatement("select * from users where username=? ");
         ps.setString(1, aUsername);
         rs = ps.executeQuery();
      if(rs.next()){
      ps=conn.prepareStatement("update users set status=1 where username=?");
      ps.setString(1, aUsername);
      ps.executeUpdate();
      response.sendRedirect("succesfulAD.jsp");
         }else{
      response.sendRedirect("unsuccessfulAD.jsp");
      }}else{    
          
      if(request.getParameter("productname")!=null && request.getParameter("productprice")!=null && request.getParameter("pavailable")!=null && request.getParameter("picture")!=null){          
      String ProductName= request.getParameter("productname");
      String ProductPrice = request.getParameter("productprice");
      String ProductAvailable = request.getParameter("pavailable");
      String Picture = request.getParameter("picture");
      conn =  model.Database.getConnection();
       ps = conn.prepareStatement("insert into products values (null,?,?,?,?)");
      ps.setString(1, ProductPrice);
      ps.setString(2, ProductName);
      ps.setString(3, ProductAvailable);
      ps.setString(4, Picture);
      ps.executeUpdate();
      response.sendRedirect("succesfulAD.jsp");
      
      }else{
      if(request.getParameter("dproductname")!=null){
      String dProductName= request.getParameter("dproductname");
      conn =  model.Database.getConnection();
       ps = conn.prepareStatement("select * from products where name=? ");
         ps.setString(1, dProductName);
         rs = ps.executeQuery();
         if(rs.next()){
       conn =  model.Database.getConnection();
       ps = conn.prepareStatement("delete from products where name=?");
      ps.setString(1, dProductName);
      ps.executeUpdate();
      response.sendRedirect("succesfulAD.jsp");
         }else{
          response.sendRedirect("unsuccessfulAD.jsp");
         }
      }else{
      if(request.getParameter("orderid")!=null){
      String OrderId= request.getParameter("orderid");
      conn =  model.Database.getConnection();
       ps = conn.prepareStatement("select * from orders where id=? ");
         ps.setString(1, OrderId);
         rs = ps.executeQuery();
         if(rs.next()){
       conn =  model.Database.getConnection();
       ps = conn.prepareStatement("update orders set orderstatus= 'complete' where id=?");
      ps.setString(1, OrderId);
      ps.executeUpdate();
      response.sendRedirect("succesfulAD.jsp");
         }else{
          response.sendRedirect("unsuccessfulAD.jsp");
         }   
      }else{
       if(request.getParameter("ddproductname")!=null && request.getParameter("productquantity")!=null){
       String ddProductName= request.getParameter("ddproductname");
       String ProductQuantity= request.getParameter("productquantity");
      conn =  model.Database.getConnection();
       ps = conn.prepareStatement("select * from products where name=? ");
         ps.setString(1, ddProductName);
         rs = ps.executeQuery();
         if(rs.next()){
       conn =  model.Database.getConnection();
       ps = conn.prepareStatement("update products set quantity=quantity+? where name=?");
      ps.setString(1, ProductQuantity);
      ps.setString(2, ddProductName);
      ps.executeUpdate();
      response.sendRedirect("succesfulAD.jsp");
         }else{
          response.sendRedirect("unsuccessfulAD.jsp");
         }   
       
       }
      
      }
      }
      }
      }
      }
      
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
            Logger.getLogger(AdminPage.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AdminPage.class.getName()).log(Level.SEVERE, null, ex);
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
