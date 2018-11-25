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
import model.Jdbc;

/**
 *
 * @author me-aydin
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
       
        HttpSession session = request.getSession(false);
        
        Jdbc jdbc = (Jdbc)session.getAttribute("dbbean"); 
        if (jdbc == null)
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
        else {
            String [] query = new String[5];
        
            query[0] = (String)request.getParameter("username");
            query[1] = (String)request.getParameter("password");
            query[2] = (String)request.getParameter("newpasswd");
            query[3] = (String)request.getParameter("currentPassword");
            query[4] = (String)request.getParameter("usertype");
            if (jdbc == null){
                request.getRequestDispatcher("/WEB-INF/passwdChange.jsp").forward(request, response);
            }
            
            else if(query[1].length() < 5)
            {
                request.setAttribute("msg", "Password must be at least 5 characters long"); 
                request.getRequestDispatcher("/WEB-INF/passwdChange.jsp").forward(request, response); 
            }
            
            else if(!query[2].trim().equals(query[1].trim())) {
                request.setAttribute("msg", "Your two passwords are not the same. </br> Please make sure you confirm the password</br>");
                request.getRequestDispatcher("/WEB-INF/passwdChange.jsp").forward(request, response); 
            }
            
            if (query[4].equals("Administrator"))
            {
                if (!jdbc.existsAdmin(query[0]))
                {
                    request.setAttribute("msg", query[0]+" The inputted username does not exist");
                    request.getRequestDispatcher("/WEB-INF/passwdChange.jsp").forward(request, response);
                }
                else{
                    jdbc.updateAdmin(query);     
                    request.setAttribute("msg", ""+query[0]+"'s password has been changed</br>");
                    request.getRequestDispatcher("/WEB-INF/passwdChange.jsp").forward(request, response);
                }
            }
            
            else if (query[4].equals("Customer")){
                if (!jdbc.existsCustomer(query[0]))
                {
                    request.setAttribute("msg", query[0]+" The inputted username does not exist");
                    request.getRequestDispatcher("/WEB-INF/passwdChange.jsp").forward(request, response);
                }
                else{
                    jdbc.updateCustomer(query);     
                    request.setAttribute("msg", ""+query[0]+"'s password has been changed</br>");
                    request.getRequestDispatcher("/WEB-INF/passwdChange.jsp").forward(request, response);
                }
            }

            else if (query[4].equals("Driver")){
                if (!jdbc.existsDriver(query[0]))
                {
                    request.setAttribute("msg", query[0]+" The inputted username does not exist");
                    request.getRequestDispatcher("/WEB-INF/passwdChange.jsp").forward(request, response);
                }
                else{
                    jdbc.updateDriver(query);     
                    request.setAttribute("msg", ""+query[0]+"'s password has been changed</br>");
                    request.getRequestDispatcher("/WEB-INF/passwdChange.jsp").forward(request, response);
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
