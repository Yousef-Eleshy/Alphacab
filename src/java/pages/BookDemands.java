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
import model.Admin;
import model.DistanceMatrix;
import model.Jdbc;

/**
 *
 * @author Sean
 */
@WebServlet(name = "BookDemands", urlPatterns = {"/BookDemands"})
public class BookDemands extends HttpServlet {

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
        
        DistanceMatrix distance = (DistanceMatrix) session.getAttribute("dbbean4");
        Admin admin = (Admin) session.getAttribute("dbbean2");
        //Useful queries
        String qry1 = "select * from DRIVERS";
        String qry2 = "select * from DEMANDS where Status='Outstanding'";
        String qry3 = "select * from JOURNEY";
        //String qry6 = "SELECT Drivers.Name, Drivers.Registration FROM Drivers LEFT JOIN Journey ON Journey.Registration = Drivers.Registration LEFT JOIN Demands ON Demands.Time = Journey.Time WHERE Demands.id IS NULL";
        
        String[] query = new String[8];
        query[0] = (String) request.getParameter("name");
        query[1] = (String) request.getParameter("address");
        query[2] = (String) request.getParameter("destination");
        query[3] = (String) request.getParameter("date");
        query[4] = (String) request.getParameter("time");
        query[5] = "SELECT ID FROM Customer WHERE name = '"+query[0]+"'";
        query[6] = (String) request.getParameter("registration");
        query[7] = (String) request.getParameter("id");
        

        //If session is invalidated, redirect to index
        if (user == null) {
            request.setAttribute("Error", "Session has ended.  Please login.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        
        
        //If connection fails, display error
        if (admin == null) {
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
        }
        //If username field is empty, display message
        if (query[0].equals("")) {
            request.setAttribute("msg", "Name field is empty");   
        }
        //Insert the demand
        else{
            Double theDistance = Double.parseDouble(distance.getDistance(query[1],query[2]));
            Double fee = distance.calculatePrice(theDistance);
            admin.insertDemand(query,fee,theDistance);
            admin.updateDemandStatus(query[7]);
            request.setAttribute("msg", "Booked!");
        }
        
        //Update tables on JSP
        String msg = "No current demands";
        String msg2 = "No current journeys";
        String msg3 = "No current drivers";      
        try{
            msg = admin.retrieve(qry2);
            msg2 = admin.retrieve(qry3);
            msg3 = admin.retrieve(qry1);                
        }catch(SQLException ex){
            Logger.getLogger(BookDemands.class.getName()).log(Level.SEVERE, null, ex);            
        }
        request.setAttribute("query", msg);
        request.setAttribute("query1", msg2);
        request.setAttribute("query2", msg3);
        
        //Direct to book demands jsp
        request.getRequestDispatcher("/WEB-INF/bookDemands.jsp").forward(request, response);
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
