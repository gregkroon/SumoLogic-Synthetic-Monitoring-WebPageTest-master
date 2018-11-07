/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sumologic.webpagetestscratch;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
/**
 *
 * @author gkroon
 */
public class ResultsDataFilter {
    
    
    public String Filter(String data) throws Exception {
        
        
        
        
        
        JSONParser parser = new JSONParser();
                
                Object obj1;
                obj1 = parser.parse(data.toString());
                JSONObject jsonObject = (JSONObject) obj1;
                //System.out.println("Object" + jsonObject);
                JSONObject data1 = (JSONObject) jsonObject.get("data");
                JSONObject runs = (JSONObject) data1.get("runs");
                JSONObject instance = (JSONObject) runs.get("1");
               // System.out.println("\n" + runs.toJSONString());
                JSONObject firstView = (JSONObject) instance.get("firstView");
                //JSONObject testInfo = (JSONObject) data.get("statusText");
                
                //System.out.println("\n" + testInfo);
                String url = (String) data1.get("url");
                String location = (String) data1.get("location");
                String summary = (String) data1.get("summary");
                Long loadtime = (Long) firstView.get("loadTime");
                Long ttfb = (Long) firstView.get("TTFB");
                Long resultcode = (Long) firstView.get("result");
                
                JSONObject resultsObj = new JSONObject();
                
                resultsObj.put("url",url);
                resultsObj.put("location",location);
                resultsObj.put("summary",summary);
                resultsObj.put("loadtime",loadtime);
                resultsObj.put("ttfb",ttfb);
                resultsObj.put("resultcode",resultcode);
               // String JSONurl = (String) data.get("jsonUrl");
                System.out.println("\n" + resultsObj.toJSONString());
        
        
        
        
        
        
        
        
                    return resultsObj.toJSONString();
    
        
        
        
        
        
}
    
}
