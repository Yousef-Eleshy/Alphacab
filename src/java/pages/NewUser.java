/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Jdbc;

/**
 *
 * @author me-aydin
 */
public class NewUser extends HttpServlet {

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
        
        //Useful queries
        String [] query = new String[5];
        query[0] = (String)request.getParameter("username");
        query[1] = (String)request.getParameter("password");
        query[2] = (String)request.getParameter("confPassword");
        query[3] = (String)request.getParameter("name");
        query[4] = (String)request.getParameter("address");
       
        Jdbc jdbc = (Jdbc)session.getAttribute("dbbean"); 
        
        //If connection fails, display error message
        if (jdbc == null)
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
        
        //If no username inputted, display error
        if(query[0].equals("") ) {
            request.setAttribute("message", "Username cannot be NULL");
        } 
        //If username is already taken, display error
        else if(jdbc.existsCustomer(query[0])){
            request.setAttribute("message", query[0]+" is already taken as username");
        }
        //If username = password, display error
        else if(query[0].equals(query[1])){
            request.setAttribute("message", "Username and password cannot be the same");
        }
        //If inputted passwords do not match, display error
        else if (!query[1].equals(query[2]))
        {
            request.setAttribute("message", "Passwords do not match");
        }
        //Create the customer
        else {          
            jdbc.insertCustomer(query);
            request.setAttribute("message", query[0]+" has been registered successfully!");
        }
         
        //Direct to user jsp
        request.setAttribute("back", "login");
        request.getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);
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
