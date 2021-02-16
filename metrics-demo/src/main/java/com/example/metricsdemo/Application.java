package com.example.metricsdemo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
	
//	@Bean
//	ApplicationRunner runner (MeterRegistry mr) {
//		return args -> {
//			this.executorService.scheduleWithFixedDelay( new Runnable() {
//				
//				@Override
//				public void run() {
//					mr.timer("test-timer").record(Duration.ofMillis((long) Math.random()*1000));
//					
//				}
//			}, 500, 500, TimeUnit.MILLISECONDS); 
//				
//		};
//	}
	
    @Bean
    TimedAspect timedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }

}
