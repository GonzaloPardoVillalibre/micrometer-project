package com.example.metricsdemo.services;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

@Service
public class MetricsService {
	
	private MeterRegistry meterRegistry;
	private Counter serverNodes;
	private Counter serverInstances;
	private Timer dbTimer;
	private Timer dbTimerFluent;
	
	/**
	 * Constructor
	 * 
	 */
	public MetricsService(
			MeterRegistry meterRegistry) {
		this.meterRegistry = meterRegistry;
		initMetricCounters();
	}
	
	private void initMetricCounters() {
	    serverNodes = this.meterRegistry.counter("metric.nodes", "type", "serverNodes"); // 1 - create a counter
	    serverInstances = Counter.builder("metric.instances")    // 2 - create a counter using the fluent API
	            .tag("type", "serverInstances")
	            .description("The number of server instances there are")
	            .register(meterRegistry);
	    
	    dbTimerFluent = Timer.builder("metric.DB.fluentLatency").register(meterRegistry);
	    dbTimer = this.meterRegistry.timer("metric.DB.latency");
	}
	
	@Timed(value = "metric.automaticTimer.addInstance")
	public void addInstance() {
		serverNodes.increment(1.0);
		serverInstances.increment();
	}
	
	public void consultSomethingInDB() {
    	Random rand = new Random();
    	// This is the simulation of the process taking a random period seconds
    	int time = rand.nextInt(26*1000);
		dbTimer.record(() -> {
		    try {
		    	TimeUnit.MILLISECONDS.sleep(time);
		    } catch (InterruptedException ignored) { }
		});
		
		dbTimerFluent.record(() -> {
		    try {
		    	TimeUnit.MILLISECONDS.sleep(time);
		    } catch (InterruptedException ignored) { }
		});
	}
	
}
