/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sumologic.webpagetestscratch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author gkroon
 */
public class JobStatus {
    
    //private final String url;
    public StringBuffer response;
    //JobStatus(String url) {
        
      //  this.url = url;
        
        
   // }

    
    
    
    
    public String JobState(String url) throws Exception {
        
        String Status = "test";
        
        
        while (Status != null ) {
        
        URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", "");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
                
                BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
                        Thread.sleep(10000);
		}
		in.close();
                
		//print result
		//System.out.println(response.toString());
                
                
                JSONParser parser = new JSONParser();
                
                Object obj1;
                obj1 = parser.parse(response.toString());
                JSONObject jsonObject = (JSONObject) obj1;
                //System.out.println("Object" + jsonObject);
                JSONObject data = (JSONObject) jsonObject.get("data");
                //JSONObject testInfo = (JSONObject) data.get("statusText");
                
                //System.out.println("\n" + testInfo);
                Status = (String) data.get("statusText");
               // String JSONurl = (String) data.get("jsonUrl");
                System.out.println("\n" + Status);
                 
        
        }
        
        
     return response.toString();
    
    
    
    
    
    
}

}