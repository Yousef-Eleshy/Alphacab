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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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
        HttpSession session = request.getSession(false);
        
        String yyyy = request.getParameter("year");
        String mm = request.getParameter("month");
        String dd = request.getParameter("day");
        String dateStr = yyyy + "-" + mm + "-" + dd;
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        try {
            java.util.Date utilDate = format.parse(dateStr);
        } catch (ParseException ex) {
            Logger.getLogger(BookDemands.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //LocalDate date = LocalDate.now();
        String qry1 = "select * from CUSTOMER";
        String qry2 = "select * from DRIVERS";
        //String qry3 = "select * from DEMANDS where date = '" + dateStr + "'";
        String qry4 = "select * from DEMANDS";
        String qry5 = "select * from JOURNEY";
        String qry6 = "SELECT Drivers.Name, Drivers.Registration FROM Drivers LEFT JOIN Journey ON Journey.Registration = Drivers.Registration LEFT JOIN Demands ON Demands.Time = Journey.Time WHERE Demands.id IS NULL";
        
        String[] query = new String[7];
        query[0] = (String) request.getParameter("name");
        query[1] = (String) request.getParameter("address");
        query[2] = (String) request.getParameter("destination");
        query[3] = dateStr;
        query[4] = (String) request.getParameter("time");
        query[5] = "SELECT ID FROM Customer WHERE name = '"+query[0]+"'";
        query[6] = (String) request.getParameter("registration");
        
        
       
        Jdbc dbBean = new Jdbc();
        dbBean.connect((Connection)request.getServletContext().getAttribute("connection"));
        session.setAttribute("dbbean", dbBean);
        
        Jdbc jdbc = (Jdbc) session.getAttribute("dbbean");

        if (jdbc == null) {
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
        }

        if (query[0].equals("")) {
            request.setAttribute("msg", "Username cannot be NULL");   
        }
        
        else{
            jdbc.insertDemand(query);
            //request.setAttribute("msg", "Booked!");
            request.setAttribute("msg", dateStr);
        }
        
        String msg = "No current demands";
        String msg2 = "No current journeys";
        String msg3 = "No current drivers";      
        try{
            msg = dbBean.retrieve(qry4);
            msg2 = dbBean.retrieve(qry5);
            msg3 = dbBean.retrieve(qry2);                
        }catch(SQLException ex){
            Logger.getLogger(BookDemands.class.getName()).log(Level.SEVERE, null, ex);            
        }
        request.setAttribute("query", msg);
        request.setAttribute("query1", msg2);
        request.setAttribute("query2", msg3);
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
