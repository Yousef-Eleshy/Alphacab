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
import model.Jdbc;

/**
 *
 * @author me-aydin
 */
@WebServlet(name = "Delete", urlPatterns = {"/Delete.do"})
public class Delete extends HttpServlet {

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
        String[] query = new String[2];
        query[0] = (String) request.getParameter("username");
        query[1] = (String) request.getParameter("password");
        query[2] = (String) request.getParameter("confPassword");
        query[3] = (String) request.getParameter("usertype");

        Jdbc jdbc = (Jdbc) session.getAttribute("dbbean");
        
        //If connection fails, display error
        if (jdbc == null) {
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
        } 
        //If username is null, display error
        else if (query[0].equals("")) {
            request.setAttribute("msg", "Username cannot be NULL");
        }
        //Check credentials inputted for administrator usertype, if authorized then delete
        if (query[2].equals("Administrator")) {

            if (!jdbc.existsAdmin(query[0])) {
                request.setAttribute("msg", query[0] + " Username does not exist");
            }
            else if (!jdbc.checkAdminPassword(query[0], query[1])) {
                request.setAttribute("msg", "Username and password not valid");
            } 
            else if (!query[1].equals(query[2])){
                request.setAttribute("msg", "Passwords do not match");
            }
            else if (jdbc.checkAdminPassword(query[0], query[1])) {
                jdbc.deleteAdmin(query[0]);
                request.setAttribute("msg", "Deleted!");
            }
        } 
        //Check credentials inputted for customer usertype, if authorized then delete
        else if (query[2].equals("Customer")) {
            if (!jdbc.existsCustomer(query[0])) {
                request.setAttribute("msg", query[0] + " Username does not exist");
            } else if (!jdbc.checkCustomerPassword(query[0], query[1])) {
                request.setAttribute("msg", "Username and password not valid");
            } 
            else if (!query[1].equals(query[2])){
                request.setAttribute("msg", "Passwords do not match");
            }
            else if (jdbc.checkCustomerPassword(query[0], query[1])) {
                jdbc.deleteCustomer(query[0]);
                request.setAttribute("msg", "Deleted!");

            }
        } 
        //Check credentials for driver usertype, if authorized then delete
        else if (query[2].equals("Driver")) {
            if (!jdbc.existsDriver(query[0])) {
                request.setAttribute("msg", query[0] + " Username does not exist");
            } else if (!jdbc.checkDriverPassword(query[0], query[1])) {
                request.setAttribute("msg", "Username and password not valid");
            } 
            else if (!query[1].equals(query[2])){
                request.setAttribute("msg", "Passwords do not match");
            }
            else if (jdbc.checkDriverPassword(query[0], query[1])) {
                jdbc.deleteDriver(query[0]);
                request.setAttribute("msg", "Deleted!");
            }
        }

        //Direct to delete user jsp
        request.setAttribute("back", "login");
        request.getRequestDispatcher("/WEB-INF/deleteUser.jsp").forward(request, response);
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
