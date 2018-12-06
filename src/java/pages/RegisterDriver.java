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
import model.Admin;
import model.Driver;
import model.Main;

/**
 *
 * @author Sean
 */
@WebServlet(name = "RegisterDriver", urlPatterns = {"/RegisterDriver"})
public class RegisterDriver extends HttpServlet {

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
        
        //Useful queries
        String [] query = new String[4];
        query[0] = (String)request.getParameter("username");
        query[1] = (String)request.getParameter("password");
        query[2] = (String)request.getParameter("registration");
        query[3] = (String)request.getParameter("name");
      
        Driver driver = (Driver)session.getAttribute("dbbean3"); 
        
        //If session is invalidated, redirect to index
        if (user == null) {
            request.setAttribute("Error", "Session has ended.  Please login.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        
        
        //If connection fails, display error
        if (driver == null)
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
        
        //If username = null, display error
        if(query[0].equals("") ) {
            request.setAttribute("message", "You must input a username for the driver");
        } 
        
        else if (query[1].equals("")){
            request.setAttribute("message", "You must input a password for the driver");
        }
        
        else if (query[2].equals("")){
            request.setAttribute("message", "You must input a registratio for the drivern");
        }
        
        else if (query[3].equals("")){
            request.setAttribute("message", "You must input a name for the driver");
        }
        
        //If username is already taken, display message
        else if(driver.existsDriver(query[0])){
            request.setAttribute("message", query[0]+" is already taken as username");
        }
        //Register the new driver
        else{
            driver.insertDriver(query);
            request.setAttribute("msg", query[0]+" has been registered successfully!");
        }
        
        //Direct to register driver jsp
        request.getRequestDispatcher("/WEB-INF/registerDriver.jsp").forward(request, response);
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
