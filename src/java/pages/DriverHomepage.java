/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Customer;
import model.Driver;
import model.Jdbc;

/**
 *
 * @author Sean
 */
@WebServlet(name = "DriverHomepage", urlPatterns = {"/DriverHomepage"})
public class DriverHomepage extends HttpServlet {

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
        String user = session.getAttribute("loggedInUser").toString();    
        
        //Get today's date
        LocalDate date = LocalDate.now();
        
        //Useful queries
        String qry1 = "SELECT Journey.JID, Journey.ID, Journey.Address, Journey.Destination, Journey.Distance, Journey.Time FROM journey LEFT JOIN Drivers ON Drivers.Registration = Journey.Registration WHERE Drivers.Username='"+user+"' AND Journey.Status='Booked' AND Journey.Date = '"+date+"'";
        
        Driver driver = (Driver) session.getAttribute("dbbean3");
        
        //If connection fails, display error
        if((Connection)request.getServletContext().getAttribute("connection")==null)
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
        
        //Display drivers journeys for the day
        if (request.getParameter("tbl").equals("todaysJourneys")){
            
            String msg="No Demands";
            try {
                msg = driver.retrieve(qry1);
            } catch (SQLException ex) {
                Logger.getLogger(DriverHomepage.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("query", msg);
            request.getRequestDispatcher("/WEB-INF/completeJourney.jsp").forward(request, response);
        }
                
        //Direct to driver homepage jsp
        request.getRequestDispatcher("/WEB-INF/driverHomepage.jsp").forward(request, response);
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
