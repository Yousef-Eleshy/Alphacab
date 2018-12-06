/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sean
 */
public class GoogleMapsAPI {
 
    Connection connection = null;
    Statement statement = null;
    ResultSet rs = null;
    
    //int for price per mile
    public static double price = 2;
    //calculate the distance
    public String getDistance(String origin, String destination) throws MalformedURLException, IOException {  
        //Remove whitespace from user input
        String url1 = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins="+origin+"&destinations="+destination+"&key=AIzaSyCL6YJdl1YfNYO91hv_tgVCILZbJGB8vw0";
        url1 = url1.replaceAll("\\s", "%20");
        //Create the URL
        URL url = new URL(url1);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        String line, outputString = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        //Get the distance from the JSON
        while ((line = reader.readLine()) != null) {
            if (line.contains("distance")) {
                outputString = reader.readLine().trim();
                String[] splitted = outputString.split("\\s+");                
                return splitted[2].substring(1);
            }     
        }   
        return outputString;
    }
    
    //Simple calculation of price
    public double calculatePrice(Double distance){ 
        Double fee = distance * price;            
        return fee;
    }

    
    
    
}