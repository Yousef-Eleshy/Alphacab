/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Admin;


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
        String user = (String) session.getAttribute("loggedInUser");
        
        //Get today's date
        LocalDate today = LocalDate.now();
        String date = today.toString();
        
        //Useful queries
        String qry1 = "select * from CUSTOMER";
        String qry2 = "select * from DRIVERS";
        String qry3 = "select * from DEMANDS where Status='Outstanding'";
        String qry4 = "select Registration, name from DRIVERS";
        String qry5 = "select * from Dailyreports where Date = '"+date+"'";
        String qry6 = "select * from JOURNEY where Status ='Booked'";
        String qry7 = "select * from JOURNEY where Status = 'Completed'";
        
        //Get admin bean
        Admin admin = (Admin) session.getAttribute("dbbean2");
          
        //If session is invalidated, redirect to index
        if (user == null) {
            request.setAttribute("msg", "Session has ended.  Please login.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        
        //If connection fails, display connection error
        if((Connection)request.getServletContext().getAttribute("connection")==null)
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
        
        //List all customers
        if (request.getParameter("tbl").equals("ListCustomers")){
            String msg="No customers";
            try {
                msg = admin.retrieve(qry1);
            } catch (SQLException ex) {
                Logger.getLogger(AdminsHomepage.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("query", msg);
        }
        //List all drivers
        else if (request.getParameter("tbl").equals("ListDrivers")){
            String msg="No drivers";
            try {
                msg = admin.retrieve(qry2);
            } catch (SQLException ex) {
                Logger.getLogger(AdminsHomepage.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("query", msg);
        }
        //List all drivers jobs
        else if (request.getParameter("tbl").equals("ListJobs")){
            String msg="No drivers";
            try {
                msg = admin.retrieve(qry6);
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
                msg = admin.retrieve(qry3);
                msg2 = admin.retrieve(qry6);
                msg3 = admin.retrieve(qry4);
            } catch (SQLException ex) {
                Logger.getLogger(AdminsHomepage.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("query", msg);
            request.setAttribute("query1", msg2);
            request.setAttribute("query2", msg3);
            
            request.getRequestDispatcher("/WEB-INF/bookDemands.jsp").forward(request, response);
        }
        //Create a daily report
        else if (request.getParameter("tbl").equals("CreateDailyReport")){
            admin.createDailyReport();
//            String msg="No Reports";
//            try {
//                msg = admin.retrieve(qry5);;
//            } catch (SQLException ex) {
//                Logger.getLogger(AdminsHomepage.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            request.setAttribute("query", msg);
        }
        
        //View today's report
        else if (request.getParameter("tbl").equals("ViewDailyReport")){
            String msg="No Reports";
            try {
                msg = admin.retrieve(qry5);

            } catch (SQLException ex) {
                Logger.getLogger(AdminsHomepage.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("query", msg);

            request.getRequestDispatcher("/WEB-INF/viewDailyReport.jsp").forward(request, response);
        }
        
        //Create a customer invoice
        else if (request.getParameter("tbl").equals("CreateCustomerInvoice")){
            
            String msg="No journeys";
            try {
                msg = admin.retrieve(qry7);

            } catch (SQLException ex) {
                Logger.getLogger(AdminsHomepage.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("query", msg);
          
            request.getRequestDispatcher("/WEB-INF/generateInvoice.jsp").forward(request, response);
        }
          
        //Change price of a journey
        else if (request.getParameter("tbl").equals("ChangeJourneyPrice")){
            String msg="No journeys";
            try {
                msg = admin.retrieve(qry6);
            } catch (SQLException ex) {
                Logger.getLogger(AdminsHomepage.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("query", msg);
            request.getRequestDispatcher("/WEB-INF/changePriceOfJourney.jsp").forward(request, response);
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
        //Delete a user
        else if(request.getParameter("tbl").equals("Delete")){
            request.getRequestDispatcher("/WEB-INF/deleteUser.jsp").forward(request, response);    
        }
        //If all fails, direct to admin homepage jsp
        else{
            request.getRequestDispatcher("/WEB-INF/administratorHomepage.jsp").forward(request, response);
        }
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
