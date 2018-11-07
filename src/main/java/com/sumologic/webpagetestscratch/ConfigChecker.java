/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sumologic.webpagetestscratch;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author gkroon
 */
public class ConfigChecker {
    
    private Document doc;
    
    
    public void Checker() throws Exception {
        
        
        
        File fXmlFile = new File("Config.xml");
        if(fXmlFile.exists() == false) {
        
        System.out.println("Error : Config.xml does not exist in the SumoDataPump directory" );
        System.out.println("Exiting program");
        System.exit(0);
        }
        
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        
       
        
        try{
        
        doc = dBuilder.parse(fXmlFile);
        
        } catch(IOException SAXException) {
        
        System.out.println("Error : Encountered a parse error parsing Config.xml , is the XML well formed ?" );
        System.out.println("Exiting program");
        System.exit(0);
            
        }
        
        //doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
            
        try{
        NodeList WebPageTestServer = doc.getElementsByTagName("WebPageTestServer");
        String webpagetestserver = WebPageTestServer.item(0).getTextContent();
        
        if (webpagetestserver == "") {

            System.out.println("Error : WebPageTestServer value not set in Config.xml - Set Ip Address or Hostname");
            System.exit(0);
        }
        //System.exit(0);
        }catch(NullPointerException  exception) {
            
            System.out.println("Error : WebPageTestServer value not set in Config.xml - Set Ip Address or Hostname");
            System.exit(0);
        }   
            
        try{
        NodeList SumoHttpEndpoint = doc.getElementsByTagName("SumoHttpEndpoint");
        String sumohttpendpoint = SumoHttpEndpoint.item(0).getTextContent();
        if (sumohttpendpoint == "") {

            System.out.println("Error : SumoHttpEndpoint value not set in Config.xml - Example https://collectors.au.sumologic.com/receiver/v1/http/#####");
            System.exit(0);
            
        }
        //System.exit(0);
        }catch(NullPointerException  exception) {
            
            System.out.println("Error : SumoHttpEndpoint value not set in Config.xml - Example https://collectors.au.sumologic.com/receiver/v1/http/#####");
            System.exit(0);
        }
        
        try{
        NodeList WebPageTestLocation = doc.getElementsByTagName("WebPageTestLocation");
        String webpagetestlocation = WebPageTestLocation.item(0).getTextContent();
        
        if (webpagetestlocation == "") {
            
            System.out.println("Error : WebPageTestLocation value not set in Config.xml - Example ap-southeast-2:Chrome");
            System.exit(0);
        }

        //System.exit(0);
        }catch(NullPointerException  exception) {
            
            System.out.println("Error : WebPageTestLocation value not set in Config.xml - Example ap-southeast-2:Chrome");
            System.exit(0);
        }
        
        try{
        NodeList WebPageTestAPIKey = doc.getElementsByTagName("WebPageTestAPIKey");
        String webpagetestapikey = WebPageTestAPIKey.item(0).getTextContent();
        
        if (webpagetestapikey == "") {

            System.out.println("Error : WebPageTestAPIKey value not set in Config.xml");
            System.exit(0);
        }
        //System.exit(0);
        }catch(NullPointerException  exception) {
            
            System.out.println("Error : WebPageTestAPIKey value not set in Config.xml");
            System.exit(0);
        }
        
        try{
        NodeList ScheduleIntervalMins = doc.getElementsByTagName("ScheduleIntervalMins");
        String scheduleintervalmins = ScheduleIntervalMins.item(0).getTextContent();
        if (scheduleintervalmins == "") {
            
            System.out.println("Error : ScheduleIntervalMins value not set in Config.xml - Example 5 for 5 minutes");
            System.exit(0);
        
        }

        //System.exit(0);
        }catch(NullPointerException  exception) {
            
            System.out.println("Error : ScheduleIntervalMins value not set in Config.xml - Example 5 for 5 minutes");
            System.exit(0);
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        

        }
        
        
        
        
        
        
        
        
        

}
    
