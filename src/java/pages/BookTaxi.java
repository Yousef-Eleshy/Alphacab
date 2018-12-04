/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Customer;
import model.DistanceMatrix;
import model.Jdbc;

/**
 *
 * @author Sean
 */
public class BookTaxi extends HttpServlet {

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

        //Get the login session
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("loggedInUser");
        
        //Get database     
        Customer customer = (Customer) session.getAttribute("dbbean");
        DistanceMatrix distance = (DistanceMatrix) session.getAttribute("dbbean4");
        
        String qry1 = customer.findCustomerName(user);
        
        //Useful queries
        String[] query = new String[5];
        query[0] = qry1;
        query[1] = (String) request.getParameter("address");
        query[2] = (String) request.getParameter("destination");
        query[3] = (String) request.getParameter("date");
        query[4] = (String) request.getParameter("time");
  
        //If session is invalidated, redirect to index
        if (user == null) {
            request.setAttribute("msg", "Session has ended.  Please login.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        
        //If connection fails, display error
        if (customer == null) {
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
        }
        
        //Book the customer's taxi
        else {
            Integer theDistance = Integer.parseInt(distance.getDistance(query[1],query[2]));
            Integer fee = distance.calculatePrice(theDistance);
            
            customer.bookTaxi(query, fee);
            request.setAttribute("msg", "Your request has been inputted, the price is: £"+fee.toString()+"");
        }    
        //Direct to customer homepage jsp
        request.getRequestDispatcher("/WEB-INF/customerHomepage.jsp").forward(request, response);
              
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
