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

        LocalDate date = LocalDate.now();
        HttpSession session = request.getSession();
       
        String qry1 = "select * from CUSTOMER";
        String qry2 = "select * from DRIVERS";
        String qry3 = "select * from DEMANDS where date = '"+date+"'"; 
        String qry4 = "select * from DEMANDS";
        String qry5 = "select * from JOURNEY";
        //String qry4 = "SELECT Drivers.Name, Drivers.Registration FROM Drivers JOIN Journey ON Journey.Registration = Drivers.Registration LEFT JOIN Demands ON Demands.Time = Journey.Time WHERE Demands.id IS NULL";
        //String qry5 = "SELECT Drivers.Name, Drivers.Registration FROM Drivers LEFT JOIN Journey ON Journey.Registration = Drivers.Registration LEFT JOIN Demands ON Demands.Time = Journey.Time WHERE Demands.id IS NULL";  
        
        
        response.setContentType("text/html;charset=UTF-8");
        
        Jdbc dbBean = new Jdbc();
        dbBean.connect((Connection)request.getServletContext().getAttribute("connection"));
        session.setAttribute("dbbean", dbBean);
        
        if((Connection)request.getServletContext().getAttribute("connection")==null)
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
        
        if (request.getParameter("tbl").equals("ListCustomers")){
            String msg="No customers";
            try {
                msg = dbBean.retrieve(qry1);
            } catch (SQLException ex) {
                Logger.getLogger(AdminsHomepage.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("query", msg);
            request.getRequestDispatcher("/WEB-INF/administratorHomepage.jsp").forward(request, response);
        }
        else if (request.getParameter("tbl").equals("ListDrivers")){
            String msg="No drivers";
            try {
                msg = dbBean.retrieve(qry2);
            } catch (SQLException ex) {
                Logger.getLogger(AdminsHomepage.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("query", msg);
            request.getRequestDispatcher("/WEB-INF/administratorHomepage.jsp").forward(request, response);
        }
      
        else if (request.getParameter("tbl").equals("BookDemands"))
        {
            String msg="No drivers";
            String msg2="No journeys";
            String msg3="No drivers";
            try {
                msg = dbBean.retrieve(qry4);
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
        else if(request.getParameter("tbl").equals("NewUser")){
            request.getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);
        } 
        else if(request.getParameter("tbl").equals("Update")){
            request.getRequestDispatcher("/WEB-INF/passwdChange.jsp").forward(request, response);    
        }
        else if(request.getParameter("tbl").equals("RegisterDriver")){
            request.getRequestDispatcher("/WEB-INF/registerDriver.jsp").forward(request, response);    
        }
        else {
            request.setAttribute("msg", "del");
            request.getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response); 
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
