/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sumologic.webpagetestscratch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
/**
 *
 * @author gkroon
 */
public class JobThread implements Runnable {

    private final String url;
    private final String SumoUrl;
    
    
    public JobThread (String url , String SumoUrl) {
 
        this.url = url;
        this.SumoUrl = SumoUrl;
                        
                        
                        
		}
    
    
    public void run(){
        
        
        try {
            this.Execute(this.url,this.SumoUrl);
        } catch (Exception ex) {
            Logger.getLogger(JobThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public void Execute(String surl,String sSumoUrl) throws Exception{
   
     
    
     //Submit job parameter to Webpagetest instance and return handle
     
    JobSubmission jobSubmission = new JobSubmission();
    String jobSubmissionResult = jobSubmission.SubmitJob(surl);
    
    //Blocking class to loop until Job status changes to finished then return JSON payload
    
    JobStatus jobStatus = new JobStatus();
    String jobState = jobStatus.JobState(jobSubmissionResult);
    
    // Filter massive JSON payload for just the data we want 
    
    ResultsDataFilter resultsDataFilter = new ResultsDataFilter();
    String filteredResults = resultsDataFilter.Filter(jobState);
    
    //HTTP Send the filtered results in JSON format to Sumo 
    
    SumoDataSender sumodataSender = new SumoDataSender();
    sumodataSender.Sender(sSumoUrl, filteredResults);
    
    
    
                
                
                
                
                
                
    
}

   
    

}