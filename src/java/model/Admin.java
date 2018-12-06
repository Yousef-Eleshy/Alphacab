/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.io.FileOutputStream;
import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Sean
 */
public class Admin {
    
    Connection connection = null;
    Statement statement = null;
    ResultSet rs = null;
    
    
    public void connect(Connection con){
       connection = con;
    }
    
    //Get records from database
    private ArrayList rsToList() throws SQLException {
        ArrayList aList = new ArrayList();
        ResultSetMetaData metaData = rs.getMetaData();
        int count = metaData.getColumnCount(); //number of column
        String columnName[] = new String[count];

        for (int i = 1; i <= count; i++)
        {
            columnName[i-1] = metaData.getColumnLabel(i);
        }
        aList.add(columnName);
        
        int cols = rs.getMetaData().getColumnCount();
        while (rs.next()) { 
          String[] s = new String[cols];
          for (int i = 1; i <= cols; i++) {
            s[i-1] = rs.getString(i);
          } 
          aList.add(s);
        } // while    
        return aList;
    } //rsToList
 
    //Make table for JSP
    private String makeTable(ArrayList list) {
        StringBuilder b = new StringBuilder();
        String[] row;
        b.append("<table border=\"3\">");
        for (Object s : list) {
          b.append("<tr>");
          row = (String[]) s;
            for (String row1 : row) {
                b.append("<td>");
                b.append(row1);
                b.append("</td>");
            }
          b.append("</tr>\n");
        } // for
        b.append("</table>");
        return b.toString();
    }//makeHtmlTable
    
    //Select from database
     void select(String query){        
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            //statement.close();
        }
        catch(SQLException e) {
            System.out.println("way way"+e);
            //results = e.toString();
        }
    }
     
    //Update an admin
    public void updatePrice(String[] str) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("Update Journey Set price=? where JID=?",PreparedStatement.RETURN_GENERATED_KEYS);
            Double price = Double.parseDouble(str[1]);
            ps.setDouble(1, price); 
            ps.setString(2, str[0].trim());
            ps.executeUpdate();
        
            ps.close();
            System.out.println("1 rows updated.");
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public void updateJourneyStatus(String str) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("Update Journey Set Status='Invoiced' where JID=?",PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, str); 
            ps.executeUpdate();
        
            ps.close();
            System.out.println("1 rows updated.");
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    public void generateInvoice(String JID){
        
         PreparedStatement ps = null;
        try {
                     
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS rowcount FROM Invoices");
            rs.next();
            int count = rs.getInt("rowcount");
            rs.close();
            
            Statement stmt2 = connection.createStatement();
            ResultSet rs2 = stmt2.executeQuery("SELECT ID AS custID FROM Journey WHERE JID = "+JID+"");
            rs2.next();
            int id = rs2.getInt("custID");
            rs2.close();
            
            String qry1 = findSpecificDetail(("select name from Customer where id ="+id+""),"name");
            String qry2 = findSpecificDetail(("select address from Journey where jid ="+JID+""),"address");
            String qry3 = findSpecificDetail(("select destination from Journey where jid ="+JID+""),"destination");
            String qry4 = findSpecificDetail(("select date from Journey where jid ="+JID+""),"date");
            String qry5 = findSpecificDetail(("select time from Journey where jid ="+JID+""),"time");
            String qry6 = findSpecificDetail(("select price from Journey where jid ="+JID+""),"price");
            
            Integer journeyID = Integer.parseInt(JID);
            
            Double price = Double.parseDouble(qry6);
            Double vat = price * 1.2;
            
            ps = connection.prepareStatement("INSERT INTO Invoices VALUES (?,?,?,?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, count+1); 
            ps.setInt(2, journeyID);
            ps.setInt(3, id);
            ps.setString(4, qry1);
            ps.setString(5, qry2);
            ps.setString(6, qry3);
            ps.setString(7, qry4);
            ps.setString(8, qry5);
            ps.setDouble(9, price);
            ps.setDouble(10, vat);
            ps.executeUpdate(); 
            //connection.commit();
            ps.close();
            System.out.println("1 row added.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }  
        updateJourneyStatus(JID);
             
    }
    
    public void createDailyReport(){
        PreparedStatement ps = null;
        LocalDate today = LocalDate.now();
        String date = today.toString();
        double total = 0;
        int numCustomers = 0;
        try {                 
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS rowcount FROM Dailyreports");
            rs.next();
            int count = rs.getInt("rowcount");
            rs.close();
            
            Statement stmt2 = connection.createStatement();
            ResultSet rs2 = stmt2.executeQuery("SELECT Pricevat AS Price FROM Invoices WHERE Date ='"+date+"'");
            rs2.next();
            double price = rs2.getDouble("Price");
            total +=price;
            numCustomers++;
            rs2.close();
                       
            ps = connection.prepareStatement("INSERT INTO DAILYREPORTS VALUES (?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, count+1); 
            ps.setDouble(2, total);
            ps.setInt(3, numCustomers);
            ps.setString(4, date);      
            ps.executeUpdate(); 
            //connection.commit();
            ps.close();
            System.out.println("1 row added.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
        
    //Retrieve from database
    public String retrieve(String query) throws SQLException {
        String results="";
        select(query);
        return makeTable(rsToList());//results;
    }
    
     //Check admin exists
    public boolean existsAdmin(String user) {
        boolean bool = false;
        try  {
            select("select username from ADMIN where username='"+user+"'");
            if(rs.next()) {
                System.out.println("TRUE");         
                bool = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bool;
    }
       
    public String findSpecificDetail(String query,String detail) {
        
        String result = "";
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                result = rs.getString(detail);
            }
        }
        catch(SQLException e) {
            System.out.println("way way"+e);
            //results = e.toString();
        } 
        return result;
    }
    
    //Check driver exists
    public boolean existsDemand(String id) {
        boolean bool = false;
        try  {
            select("select ID from Demands where ID="+id+" and Status='Outstanding'");
            if(rs.next()) {
                System.out.println("TRUE");         
                bool = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bool;
    }
    
    //Check driver exists
    public boolean existsRegistration(String reg) {
        boolean bool = false;
        try  {
            select("select Registration from Drivers where registration='"+reg+"'");
            if(rs.next()) {
                System.out.println("TRUE");         
                bool = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bool;
    }
    
    //Update an admin
    public void updateAdmin(String[] str) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("Update Admin Set password=? where username=?",PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, str[1].trim()); 
            ps.setString(2, str[0].trim());
            ps.executeUpdate();
        
            ps.close();
            System.out.println("1 rows updated.");
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Delete an admin
    public void deleteAdmin(String user){
       
      String query = "DELETE FROM Admin " +
                   "WHERE username = '"+user.trim()+"'";
      
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        }
        catch(SQLException e) {
            System.out.println("way way"+e);
            //results = e.toString();
        }
    }
    
        //Check admin password credentials
    public boolean checkAdminPassword(String u, String p)
    {
        boolean flag = false;
        try{
            String query = "SELECT username, password FROM Admin";
            ResultSet results = statement.executeQuery(query);
            
            while(results.next()){
                String username = results.getString("username");
                String password = results.getString("password");
                
                if ((u.equals(username)) && (p.equals(password))){
                    flag = true;
                }
            }
            results.close();
     
        }catch (SQLException e){
            
        }
        return flag;
    } 
    
    //Insert a demand
    public void insertDemand(String[] str){
        PreparedStatement ps = null;
        try {
            
            String qry1 = findSpecificDetail(("select name from DEMANDS where id ="+str[0]+""),"name");
            String qry2 = findSpecificDetail(("select address from DEMANDS where id ="+str[0]+""),"address");
            String qry3 = findSpecificDetail(("select destination from DEMANDS where id ="+str[0]+""),"destination");
            String qry4 = findSpecificDetail(("select date from DEMANDS where id ="+str[0]+""),"date");
            String qry5 = findSpecificDetail(("select time from DEMANDS where id ="+str[0]+""),"time");
            
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS rowcount FROM Journey");
            rs.next();
            int count = rs.getInt("rowcount");
            rs.close();      
              
            
            Statement stmt2 = connection.createStatement();
            ResultSet rs2 = stmt2.executeQuery("SELECT ID AS custID FROM Customer WHERE name = '"+qry1+"'");
            rs2.next();
            int id = rs2.getInt("custID");
            rs.close();
            
            GoogleMapsAPI distance = new GoogleMapsAPI();
            
            Double theDistance = Double.parseDouble(distance.getDistance(qry2,qry3));
            
            Double fee = distance.calculatePrice(theDistance);
            
            if (theDistance < 5.0){
                fee += 2.0;
            }           
            ps = connection.prepareStatement("INSERT INTO Journey VALUES (?,?,?,?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, (count+1));           
            ps.setInt(2, id);
            ps.setString(3, qry2);
            ps.setString(4, qry3);
            ps.setDouble(5, theDistance);
            ps.setString(6, str[1]);
            ps.setString(7, qry4);
            ps.setString(8, qry5);
            ps.setDouble(9,fee);
            ps.setString(10, "Booked");
            ps.executeUpdate(); 
            //connection.commit();
            ps.close();
            System.out.println("1 row added.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public void updateDemandStatus(String str) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("Update Demands Set Status='Booked' where id=?",PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, str.trim()); 
            ps.executeUpdate();
        
            ps.close();
            System.out.println("1 rows updated.");
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
}
