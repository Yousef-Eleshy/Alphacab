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
    
//    Connection connection = null;
//    Statement statement = null;
//    ResultSet rs = null;
//    
//    public Jdbc(String query){
//        //this.query = query;
//    }
//
//    public Jdbc() {
//        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    
//    public void connect(Connection con){
//       connection = con;
//    }
//    
//    //Get records from database
//    private ArrayList rsToList() throws SQLException {
//        ArrayList aList = new ArrayList();
//        ResultSetMetaData metaData = rs.getMetaData();
//        int count = metaData.getColumnCount(); //number of column
//        String columnName[] = new String[count];
//
//        for (int i = 1; i <= count; i++)
//        {
//            columnName[i-1] = metaData.getColumnLabel(i);
//        }
//        aList.add(columnName);
//        
//        int cols = rs.getMetaData().getColumnCount();
//        while (rs.next()) { 
//          String[] s = new String[cols];
//          for (int i = 1; i <= cols; i++) {
//            s[i-1] = rs.getString(i);
//          } 
//          aList.add(s);
//        } // while    
//        return aList;
//    } //rsToList
// 
//    //Make table for JSP
//    private String makeTable(ArrayList list) {
//        StringBuilder b = new StringBuilder();
//        String[] row;
//        b.append("<table border=\"3\">");
//        for (Object s : list) {
//          b.append("<tr>");
//          row = (String[]) s;
//            for (String row1 : row) {
//                b.append("<td>");
//                b.append(row1);
//                b.append("</td>");
//            }
//          b.append("</tr>\n");
//        } // for
//        b.append("</table>");
//        return b.toString();
//    }//makeHtmlTable
//    
//    //Select from database
//     void select(String query){        
//        try {
//            statement = connection.createStatement();
//            rs = statement.executeQuery(query);
//            //statement.close();
//        }
//        catch(SQLException e) {
//            System.out.println("way way"+e);
//            //results = e.toString();
//        }
//    }
//    //Retrieve from database
//    public String retrieve(String query) throws SQLException {
//        String results="";
//        select(query);
//        return makeTable(rsToList());//results;
//    }
//
//            
//    //Close statement
//    public void closeAll(){
//        try {
//            rs.close();
//            statement.close(); 		
//            //connection.close();                                         
//        }
//        catch(SQLException e) {
//            System.out.println(e);
//        }
//    }
              
    //Main
    public static void main(String[] args) throws SQLException {
//        String str = "select * from Users";
//        String insert = "INSERT INTO `Users` (`username`, `password`) VALUES ('meaydin', 'meaydin')";
//        String update = "UPDATE `Users` SET `password`='eaydin' WHERE `username`='meaydin' ";
//        String db = "MyDB";
        
//        Jdbc jdbc = new Jdbc(str);
//        Connection conn = null;
//                try {
//            Class.forName("org.apache.derby.jdbc.ClientDriver");
////Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Alphacab","root","root");
////conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db.trim(), "root", "");
//        }
//        catch(ClassNotFoundException | SQLException e){
//            
//        }
//        jdbc.connect(conn);
        
        
//        System.out.println(jdbc.retrieve(str));
//        jdbc.closeAll();
    }            
}
