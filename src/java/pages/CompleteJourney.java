/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages;

import java.io.IOException;
import java.io.PrintWriter;
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
import model.Driver;

/**
 *
 * @author Sean
 */
@WebServlet(name = "completeJourney", urlPatterns = {"/completeJourney"})
public class CompleteJourney extends HttpServlet {

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
        
        //Get driver bean
        Driver driver = (Driver) session.getAttribute("dbbean3");
        
        //Get today's date
        LocalDate date = LocalDate.now();
        
        //Useful queries
        String qry1 = "SELECT Journey.JID, Journey.ID, Journey.Address, Journey.Destination, Journey.Distance, Journey.Time FROM journey LEFT JOIN Drivers ON Drivers.Registration = Journey.Registration WHERE Drivers.Username='"+user+"' AND Journey.Status='Booked' AND Journey.Date = '"+date+"'";
        String[] query = new String[2];
        query[0] = (String) request.getParameter("journeyID");
        query[1] = (String) request.getParameter("status");
        
        //If session is invalidated, redirect to index
        if (user == null) {
            request.setAttribute("msg", "Session has ended.  Please login.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        
        //If connection fails, display error
        if (driver == null) {
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
        }
        
        //If no journey ID is inputted, display a message
        if (query[0].equals("")){
            request.setAttribute("msg", "Please input an ID for the journey");
        }
        
        //If no status is inputted, display a message
        else if (query[1].equals("")){
            request.setAttribute("msg", "Please input a status for the journey");
        }
        
        //If the inputted journey does not exist, display a message
        else if (!driver.existsJourney(query[0])){
            request.setAttribute("msg", "No journey found for the inputted journey ID");
        }
        
        //Update the status of the journey
        else{
            driver.updateJourneyStatus(query);
            request.setAttribute("msg", "Updated successfully!");
        }
        
        //Update tables on JSP
        String msg = "No current journeys";      
        try{
            msg = driver.retrieve(qry1);
              
        }catch(SQLException ex){
            Logger.getLogger(BookDemands.class.getName()).log(Level.SEVERE, null, ex);            
        }
        request.setAttribute("query", msg);
        
        request.getRequestDispatcher("/WEB-INF/completeJourney.jsp").forward(request, response);
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
