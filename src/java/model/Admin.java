/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
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
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bool;
    }
    
    public String findDemandDetail(String query,String detail) {
        
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
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
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
    public void insertDemand(String[] str, Double fee, Double distance){
        PreparedStatement ps = null;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS rowcount FROM Journey");
            rs.next();
            int count = rs.getInt("rowcount");
            rs.close();      
            
            Statement stmt2 = connection.createStatement();
            ResultSet rs2 = stmt2.executeQuery("SELECT ID AS custID FROM Customer WHERE name = '"+str[0]+"'");
            rs2.next();
            int id = rs2.getInt("custID");
            
            ps = connection.prepareStatement("INSERT INTO Journey VALUES (?,?,?,?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, (count+1));           
            ps.setInt(2, id);
            ps.setString(3, str[1]);
            ps.setString(4, str[2]);
            ps.setDouble(5, distance);
            ps.setString(6, str[6]);
            ps.setString(7, str[3]);
            ps.setString(8, str[4]);
            ps.setDouble(9,fee);
            ps.setString(10, "Booked");
            ps.executeUpdate(); 
            //connection.commit();
            ps.close();
            System.out.println("1 row added.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
}
