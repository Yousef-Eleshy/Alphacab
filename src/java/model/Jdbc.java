/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import static java.sql.Types.NULL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author me-aydin
 */
public class Jdbc {
    
    Connection connection = null;
    Statement statement = null;
    ResultSet rs = null;
    //String query = null;
    
    
    public Jdbc(String query){
        //this.query = query;
    }

    public Jdbc() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void connect(Connection con){
       connection = con;
    }
    
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
    
    private void select(String query){        
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
    public String retrieve(String query) throws SQLException {
        String results="";
        select(query);
        return makeTable(rsToList());//results;
    }
    
    public String selectDriver()
    {
        
        
        return "";
    }
    
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
    
    public boolean existsCustomer(String user) {
        boolean bool = false;
        try  {
            select("select username from Customer where username='"+user+"'");
            if(rs.next()) {
                System.out.println("TRUE");         
                bool = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bool;
    }
    
    public boolean existsDriver(String user) {
        boolean bool = false;
        try  {
            select("select username from Drivers where username='"+user+"'");
            if(rs.next()) {
                System.out.println("TRUE");         
                bool = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bool;
    }
    
    public void insertCustomer(String[] str){
        PreparedStatement ps = null;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS rowcount FROM Customer");
            rs.next();
            int count = rs.getInt("rowcount");
            rs.close();
                       
            ps = connection.prepareStatement("INSERT INTO Customer VALUES (?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, str[0].trim()); 
            ps.setString(2, str[1]);
            ps.setString(3, str[3]);
            ps.setString(4, str[4]);
            ps.setInt(5, (count+1));
            ps.executeUpdate(); 
            //connection.commit();
            ps.close();
            System.out.println("1 row added.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public void insertDriver(String[] str){
        PreparedStatement ps = null;
        try {            
            ps = connection.prepareStatement("INSERT INTO DRIVERS VALUES (?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, str[0].trim()); 
            ps.setString(2, str[1]);
            ps.setString(3, str[2]);
            ps.setString(4, str[3]);
            ps.executeUpdate(); 
            ps.close();
            System.out.println("1 row added.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public void bookTaxi(String[] str){
        PreparedStatement ps = null;
        try {      
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS rowcount FROM Customer");
            rs.next();
            int count = rs.getInt("rowcount");
            rs.close();
            
            ps = connection.prepareStatement("INSERT INTO Demands VALUES (?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, str[0].trim()); 
            ps.setString(2, str[1]);
            ps.setString(3, str[2]);
            ps.setString(4, str[3]);
            ps.setString(5, str[4]);
            ps.setString(6, "Outstanding");
            ps.setInt(7, (count+1));
            ps.executeUpdate(); 
            //connection.commit();
            ps.close();
            System.out.println("1 row added.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public void insertDemand(String[] str){
        PreparedStatement ps = null;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS rowcount FROM Journey");
            rs.next();
            int count = rs.getInt("rowcount");
            rs.close();        
            ps = connection.prepareStatement("INSERT INTO Journey VALUES (?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, (count+1));           
            ps.setInt(2, 1);
            ps.setString(3, "test");
            ps.setInt(4, 5);
            ps.setString(5, "REG1");
            ps.setString(6, str[3]);
            ps.setString(7, "09:30");
            ps.executeUpdate(); 
            //connection.commit();
            ps.close();
            System.out.println("1 row added.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public void updateCustomer(String[] str) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("Update Customer Set password=? where username=?",PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, str[1].trim()); 
            ps.setString(2, str[0].trim());
            ps.executeUpdate();
        
            ps.close();
            System.out.println("1 rows updated.");
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
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
    
    public void updateDriver(String[] str) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("Update Drivers Set password=? where username=?",PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, str[1].trim()); 
            ps.setString(2, str[0].trim());
            ps.executeUpdate();
        
            ps.close();
            System.out.println("1 rows updated.");
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
    public void delete(String user){
       
      String query = "DELETE FROM Users " +
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
    public void closeAll(){
        try {
            rs.close();
            statement.close(); 		
            //connection.close();                                         
        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }
    
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
    
    public boolean checkCustomerPassword(String u, String p)
    {
        boolean flag = false;
        try{
            String query = "SELECT username, password FROM Customer";
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
    
    public boolean checkDriverPassword(String u, String p)
    {
        boolean flag = false;
        try{
            String query = "SELECT username, password FROM Driver";
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
     
    public static void main(String[] args) throws SQLException {
        String str = "select * from Users";
        String insert = "INSERT INTO `Users` (`username`, `password`) VALUES ('meaydin', 'meaydin')";
        String update = "UPDATE `Users` SET `password`='eaydin' WHERE `username`='meaydin' ";
        String db = "MyDB";
        
        Jdbc jdbc = new Jdbc(str);
        Connection conn = null;
                try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
//Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Alphacab","root","root");
//conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db.trim(), "root", "");
        }
        catch(ClassNotFoundException | SQLException e){
            
        }
        jdbc.connect(conn);
        
        
        System.out.println(jdbc.retrieve(str));
        jdbc.closeAll();
    }            
}
