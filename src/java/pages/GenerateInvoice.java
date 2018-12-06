/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Admin;
import model.Customer;
import model.GoogleMapsAPI;

/**
 *
 * @author Sean
 */
@WebServlet(name = "generateInvoice", urlPatterns = {"/generateInvoice"})
public class GenerateInvoice extends HttpServlet {

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
        
        //Get admin bean   
        Admin admin = (Admin) session.getAttribute("dbbean2");
                
        //Useful queries
        String query = (String) request.getParameter("id");      
        String qry1 = "select * from invoices where jid = "+query+"";
        
        //If session is invalidated, redirect to index
        if (user == null) {
            request.setAttribute("msg", "Session has ended.  Please login.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        
        //If connection fails, display error
        if (admin == null) {
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
        }
        
        //User input authentification
        else if (query.equals("")){
            request.setAttribute("msg", "A journey ID must be inputted");
            request.getRequestDispatcher("/WEB-INF/generateInvoice.jsp").forward(request, response);
        }
        //Generate invoice
        else{
            request.setAttribute("msg", "Invoice has been generated");
            admin.generateInvoice(query);
            String msg="No drivers";
            try {
                msg = admin.retrieve(qry1);
            } catch (SQLException ex) {
                Logger.getLogger(GenerateInvoice.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("query", msg);
        }
        request.getRequestDispatcher("/WEB-INF/viewInvoice.jsp").forward(request, response);
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
