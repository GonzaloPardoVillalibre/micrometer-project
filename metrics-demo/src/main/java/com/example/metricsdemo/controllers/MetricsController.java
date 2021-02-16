package com.example.metricsdemo.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.metricsdemo.services.MetricsService;


@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class MetricsController {
	
	private MetricsService metricsService;
	
	/**
	 * Constructor
	 * 
	 */
	public MetricsController(
			MetricsService metricsService) {
		this.metricsService = metricsService;
	}
	
	/**
	 *  Increment sever Instances (fictional)	
	 * 
	 * @param data
	 * @return
	 */
  @GetMapping("/addInstance")
   public String addInstance()
  	{
	  metricsService.addInstance();
	  return "Instance added properly";
  	}
  
  @GetMapping("/consultDatabase")
  public String consultDatabase()
 	{
	  metricsService.consultSomethingInDB();
	  return "Database consulted successfully";
 	}


}
