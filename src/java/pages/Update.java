/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Admin;
import model.Customer;
import model.Driver;
import model.Main;

/**
 *
 * @author Sean
 */
public class Update extends HttpServlet {

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
        String [] query = new String[5];
        query[0] = (String)request.getParameter("username");
        query[1] = (String)request.getParameter("password");
        query[2] = (String)request.getParameter("newpasswd");
        query[3] = (String)request.getParameter("currentPassword");
        query[4] = (String)request.getParameter("usertype");
        
        Customer customer = (Customer) session.getAttribute("dbbean"); 
        
        Admin admin = (Admin) session.getAttribute("dbbean2"); 
        
        Driver driver = (Driver) session.getAttribute("dbbean3");
        
        //If session is invalidated, redirect to index
        if (user == null) {
            request.setAttribute("msg", "Session has ended.  Please login.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
              
        //If connection fails, display error message
        if (customer == null)
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);

        //Check password length is appropriate
        else if(query[1].length() < 5)
        {
            request.setAttribute("msg", "Password must be at least 5 characters long");  
        }
        
        else if (query[1].equals("") || query[2].equals("") || query[3].equals("") || query[4].equals("")){
            request.setAttribute("msg", "All fields all mandatory");  
        }

        //Check if the inputted passwords are the same
        else if(!query[2].trim().equals(query[1].trim())) {
            request.setAttribute("msg", "The passwords do not match"); 
        }

        //Check administrator credentials
        if (query[4].equals("Administrator"))
        {
            if (!admin.existsAdmin(query[0]))
            {
                request.setAttribute("msg", query[0]+" The inputted username does not exist");
            }
            else{
                admin.updateAdmin(query);     
                request.setAttribute("msg", ""+query[0]+"'s password has been changed");
            }
        }

        //Check customer credentials
        else if (query[4].equals("Customer")){
            if (!customer.existsCustomer(query[0]))
            {
                request.setAttribute("msg", query[0]+" The inputted username does not exist");
            }
            else{
                customer.updateCustomer(query);     
                request.setAttribute("msg", ""+query[0]+"'s password has been changed");
            }
        }

        //Check driver credentials
        else if (query[4].equals("Driver")){
            if (!driver.existsDriver(query[0]))
            {
                request.setAttribute("msg", query[0]+" The inputted username does not exist");
            }
            else{
                driver.updateDriver(query);     
                request.setAttribute("msg", ""+query[0]+"'s password has been changed");
            }
        }
        
        //Direct to password change jsp
        request.getRequestDispatcher("/WEB-INF/passwdChange.jsp").forward(request, response); 
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
