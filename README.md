# SumoLogic-Synthetic-Monitoring-WebPageTest V1.0

This project is a SumoLogic data pump between private instances of WebPageTest and Sumologic .
The aim is to provide a Synthetic monitoring capability to SumoLogic to compliment the real user monitoring capability available in 
the sister project (SumoLogic Real User monitoring)

With this capability customers can automatically test their websites for availability and performance using SumoLogic to 
Alert and dashboard monitoring data .

![alt text](https://github.com/gregkroon/SumoLogic-Synthetic-Monitoring-WebPageTest/blob/master/images/SyntheticSearchResults.png)



**Pre-requsites**

1. Install and configure a webpagetest private instance . There are many ways to do this 
   please refer to the webpagetest documentation here.
   https://github.com/WPO-Foundation/webpagetest-docs/blob/master/user/Private%20Instances/README.md
   
   SumoDataPump has been tested against the out of the box AWS AMI's found here.
   https://github.com/WPO-Foundation/webpagetest/blob/master/docs/EC2/Server%20AMI.md
   
   Example config to put under the advanced section 
   ```
   ec2_key=<Your Key>
   ec2_secret=<Your secret>
   api_key=<Your Key>
   headless=0
   iq=80
   pngss=1
   EC2.ap-southeast-2-linux.min=1
   EC2.ap-southeast-2-linux.max=5

2. Build the data pump or use the prebuilt version 

   This project is built using maven and the maven shade jar plugin to build a fat executable jar .
   pom.xml is included with all dependancies . Build using "mvn install"
   
   If you prefer not to roll your own , included is a directory "build" with a prebuilt jar (SumoDataPump.jar) and example      config file  


3. The Config.xml file

```The Config.xml file must be located in the same directory as the jar file for the datapump to work.
all parameters are mandatory , explanation of them here .

WebPageTestServer     - endpoint ip address of your WebPageTest private instance 
SumoHttpEndpoint      - http collector url from Sumo
WebPageTestLocation   - location/browser i.e. https://sites.google.com/a/webpagetest.org/docs/private-instances/locations
WebPageTestAPIKey     - your private api key you set on your instance 
ScheduleIntervalMins  - schedule for the datapump to run i.e. 5 minute intervals 
url                   - url to test , for multiple urls use the xml tags to enumerate by 1 ,2 , 3

		        <site id="1">
		        	<url>www.sumologic.com</url>
			</site>
			
			
Example configuration file testing 3 urls against private instance in southeast 2 region on chrome at 5 minute intervals .
			
<?xml version="1.0"?>
<configuration>
	<WebPageTestServer>10.1.1.1</WebPageTestServer>
	<SumoHttpEndpoint>https://collectors.au.sumologic.com/receiver/v1/http/#########</SumoHttpEndpoint>
	<WebPageTestLocation>ap-southeast-2-linux:Chrome.Native</WebPageTestLocation>
	<WebPageTestAPIKey>yourkeyhere</WebPageTestAPIKey>
	<ScheduleIntervalMins>5</ScheduleIntervalMins>
	<site id="1">
		<url>www.sumologic.com</url>
	</site>
	<site id="2">
		<url>www.url2.com</url>
	</site>
	<site id="3">
		<url>www.url3.com</url>
	</site>
</configuration>
```

4. Install and Deploy 

   Place the jar file and the Config.xml file in the same directory 
   Execute in a terminal window with java -jar SumoDataPump.jar , if everything is working you should see logging to 	      	 the terminal similar to this 

```

Sending 'GET' request to URL : http://10.1.1.1/jsonResult.php?test=180417_P3_B
Response Code : 200

Waiting at the front of the queue...

Eventually it will change to something like the following 

Test Started 35 seconds ago

Sending 'POST' request to URL : https://collectors.au.sumologic.com/receiver/v1/http/#####
Post parameters : {"summary":"http:\/\/10.1.1.1\/results.php?test=180417_19_C","loadtime":18543,"location":"ap-southeast-2:Chrome","ttfb":1188,"url":"http:\/\/www.sumologic.com"}
Response Code : 200
```

5. Making use of the data in SumoLogic




TODO :

- Error handling and logging in the datapump
- Testing around timeout scenarios and impact to schedule lag

