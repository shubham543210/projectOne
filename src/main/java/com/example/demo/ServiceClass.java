package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ServiceClass {
	
	@Autowired 
	Data data;
	
	@Autowired
	TimeSeries timeSeries;
	RestTemplate restTemplate = new RestTemplate();

	
	@Scheduled(fixedRate = 36000)
	public void getData() {
		//Data data = restTemplate.getForObject(
		//		"https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=ACN&interval=15min&apikey=JVNI9RRYW2W6WSKG",
		//		Data.class);
		
		
		 try { 

	            URL url = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=ACN&interval=15min&apikey=JVNI9RRYW2W6WSKG"); 
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
	          //  connection.setDoOutput(true); 
	          //  connection.setInstanceFollowRedirects(false); 
	            connection.setRequestMethod("GET"); 
	            connection.setRequestProperty("Content-Type", "application/json"); 

	           
	            
	            BufferedReader br = new BufferedReader(new InputStreamReader(
	                    (connection.getInputStream()))); // Getting the response from the webservice
	            String message = org.apache.commons.io.IOUtils.toString(br);
	            JSONObject json = new JSONObject(message);
	            Map<String,Object> map= new HashMap<>();
	            //map=  new ObjectMapper().readValue(json, HashMap.class);;
	            //HashMap<String, Object> yourHashMap = new Gson().fromJson(yourJsonObject.toString(), HashMap.class);
	           

	            connection.getResponseCode(); 
	            connection.disconnect(); 
	            
	            HashMap<String,Object> result =
	                    new ObjectMapper().readValue(message, HashMap.class);
	            Map<String,Object> output=(Map<String, Object>) result.get("Time Series (15min)");
	            
	            System.out.println("Output from Server .... \n"+output.keySet());
	            
	            int i=output.size();
	            
	            for (Entry<String, Object> entry : output.entrySet()) {
	            	timeSeries.setTime(entry.getKey());  
	            	Map<String,String> statics=(Map<String, String>) entry.getValue();
	            	timeSeries.setClose(statics.get("4. close"));
	            	timeSeries.setHigh(statics.get("2. high"));
	            	timeSeries.setLow(statics.get("3. low"));
	            	timeSeries.setOpen(statics.get("1. open"));
	            	timeSeries.setVolume(statics.get("5. volume"));
	            	
	            	data.setValue();
	            }
	           
	           
	        } catch(Exception e) { 
	            throw new RuntimeException(e); 
	        } 
		 
	}
	
	@Scheduled(fixedRate = 180000)
	public void removeAllService()
	{
		data.removeAll();
	}
}