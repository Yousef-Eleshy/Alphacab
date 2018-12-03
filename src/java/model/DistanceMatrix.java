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

/**
 *
 * @author Sean
 */
public class DistanceMatrix {
 
    //int for changing price
    public static int newprice;
    //calculate the distance
    public String getDistance(String origin, String destination) throws MalformedURLException, IOException {
       
        URL url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins="+origin+"&destinations="+destination+"&key=AIzaSyCL6YJdl1YfNYO91hv_tgVCILZbJGB8vw0");
        //URL url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=Cornwall,UK&destinations=London,UK&key=AIzaSyCL6YJdl1YfNYO91hv_tgVCILZbJGB8vw0");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        String line, outputString = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        while ((line = reader.readLine()) != null) {

            if (line.contains("distance")) {
                outputString = reader.readLine().trim();
                String[] splitted = outputString.split("\\s+");                
                return splitted[2].substring(1);
            }     
        }
       
        return outputString;
    }
    //increase price by £2
    public int increasePrice() {
        newprice += 2;
        return newprice;
    }
    //decrease price by £2
    public int decreasePrice() {
        newprice -= 2;
        return newprice;
    }
    
    public int calculatePrice(Integer distance){
        
        int fee = distance * 2; 
              
        return fee;
    }

    
    
    
}
