/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Author Sean
package pages;

import java.io.IOException;
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
import model.Jdbc;

/**
 *
 * @author Sean
 */
public class AdminsHomepage extends HttpServlet {

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
        String qry1 = "select * from CUSTOMER";
        String qry2 = "select * from DRIVERS";
        String qry3 = "select * from DEMANDS where Status='Outstanding'";
        String qry5 = "select * from JOURNEY";
        //String qry4 = "SELECT Drivers.Name, Drivers.Registration FROM Drivers JOIN Journey ON Journey.Registration = Drivers.Registration LEFT JOIN Demands ON Demands.Time = Journey.Time WHERE Demands.id IS NULL";
        //String qry5 = "SELECT Drivers.Name, Drivers.Registration FROM Drivers LEFT JOIN Journey ON Journey.Registration = Drivers.Registration LEFT JOIN Demands ON Demands.Time = Journey.Time WHERE Demands.id IS NULL";  
        
        
        //Connect to database
        Jdbc dbBean = new Jdbc();
        dbBean.connect((Connection)request.getServletContext().getAttribute("connection"));
        session.setAttribute("dbbean", dbBean);
        
        //If connection fails, display connection error
        if((Connection)request.getServletContext().getAttribute("connection")==null)
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
        
        //List all customers
        if (request.getParameter("tbl").equals("ListCustomers")){
            String msg="No customers";
            try {
                msg = dbBean.retrieve(qry1);
            } catch (SQLException ex) {
                Logger.getLogger(AdminsHomepage.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("query", msg);
        }
        //List all drivers
        else if (request.getParameter("tbl").equals("ListDrivers")){
            String msg="No drivers";
            try {
                msg = dbBean.retrieve(qry2);
            } catch (SQLException ex) {
                Logger.getLogger(AdminsHomepage.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("query", msg);
        }
        //Book customer demands
        else if (request.getParameter("tbl").equals("BookDemands"))
        {
            String msg="No drivers";
            String msg2="No journeys";
            String msg3="No drivers";
            try {
                msg = dbBean.retrieve(qry3);
                msg2 = dbBean.retrieve(qry5);
                msg3 = dbBean.retrieve(qry2);
            } catch (SQLException ex) {
                Logger.getLogger(AdminsHomepage.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("query", msg);
            request.setAttribute("query1", msg2);
            request.setAttribute("query2", msg3);
            
            request.getRequestDispatcher("/WEB-INF/bookDemands.jsp").forward(request, response);
        }
        //Create new customer
        else if(request.getParameter("tbl").equals("NewUser")){
            request.getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);
        } 
        //Update a user
        else if(request.getParameter("tbl").equals("Update")){
            request.getRequestDispatcher("/WEB-INF/passwdChange.jsp").forward(request, response);    
        }
        //Register a new driver
        else if(request.getParameter("tbl").equals("RegisterDriver")){
            request.getRequestDispatcher("/WEB-INF/registerDriver.jsp").forward(request, response);    
        }
        //If all fails, direct to admin homepage jsp
        else{
            request.getRequestDispatcher("/WEB-INF/administratorHomepage.jsp").forward(request, response);
        }
        request.setAttribute("back", "login");
        request.getRequestDispatcher("/WEB-INF/administratorHomepage.jsp").forward(request, response);
        
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
