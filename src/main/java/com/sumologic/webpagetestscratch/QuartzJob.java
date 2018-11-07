/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sumologic.webpagetestscratch;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 *
 * @author gkroon
 */

    

public class QuartzJob implements Job
{
	public void execute(JobExecutionContext context)
	throws JobExecutionException {
		
		
                    try {
            int MYTHREADS = 30;
            
            
            
            ExecutorService executor = Executors.newFixedThreadPool(MYTHREADS);
            
            File fXmlFile = new File("Config.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            
            doc.getDocumentElement().normalize();
            //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            
            NodeList WebPageTestServer = doc.getElementsByTagName("WebPageTestServer");
            NodeList SumoHttpEndpoint = doc.getElementsByTagName("SumoHttpEndpoint");
            NodeList WebPageTestLocation = doc.getElementsByTagName("WebPageTestLocation");
            NodeList WebPageTestAPIKey = doc.getElementsByTagName("WebPageTestAPIKey");
            NodeList SiteList = doc.getElementsByTagName("site");
            NodeList UrlList = doc.getElementsByTagName("url");
            
            
            
            for (int temp = 0; temp < SiteList.getLength(); temp++) {
                
               Node nNode = UrlList.item(temp);
                
               String urlBuild = "http://" + WebPageTestServer.item(0).getTextContent() + "/runtest.php?url=" + nNode.getTextContent() + "&f=json&r=12345&k=" + WebPageTestAPIKey.item(0).getTextContent() + "&location=" + WebPageTestLocation.item(0).getTextContent();
               String sSumoUrl =  SumoHttpEndpoint.item(0).getTextContent();
                
                
                JobThread s =  new JobThread(urlBuild,sSumoUrl);
                
                executor.execute(s);
                
                
                
                
            }
            
            executor.shutdown();
		// Wait until all threads are finish
		while (!executor.isTerminated()) {
 
		}
		System.out.println("\nFinished all threads");
            
            
            
            
            
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }   catch (SAXException ex) {
                Logger.getLogger(QuartzJob.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(QuartzJob.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
		
	}
	
}
    
    
    
    

