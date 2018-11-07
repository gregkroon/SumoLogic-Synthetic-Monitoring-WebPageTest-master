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
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author gkroon
 */
public class Main {
    
    
    public static void main(String [ ] args) throws SchedulerException, ParserConfigurationException, SAXException, IOException, Exception  {
    
        
      ConfigChecker configChecker = new ConfigChecker();  
      configChecker.Checker();
     
      File fXmlFile = new File("Config.xml");
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(fXmlFile);
            
      doc.getDocumentElement().normalize();
      
            
      NodeList ScheduleIntervalMins = doc.getElementsByTagName("ScheduleIntervalMins");  
      String Minutes = ScheduleIntervalMins.item(0).getTextContent();
      int Mins = Integer.parseInt(Minutes);
        
    JobDetail job = JobBuilder.newJob(QuartzJob.class)
		.withIdentity("SumoJob", "SumoGroup").build();
    
    Trigger trigger = TriggerBuilder
		.newTrigger()
		.withIdentity("SumoTrigger", "SumoGroup")
		.withSchedule(
			SimpleScheduleBuilder.simpleSchedule()
		.withIntervalInMinutes(Mins).repeatForever())
		.build();
    	
    	//schedule it
    	Scheduler scheduler = new StdSchedulerFactory().getScheduler();
    	scheduler.start();
    	scheduler.scheduleJob(job, trigger);
    
    
    
    
    
}


}