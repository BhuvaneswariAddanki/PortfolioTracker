package com.fifthgentech.sample.PortfolioTracker.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.fifthgentech.sample.PortfolioTracker.config.BaseConfig;
import com.fifthgentech.sample.PortfolioTracker.exception.PortfolioTrackerException;
import com.fifthgentech.sample.PortfolioTracker.service.PortfolioTrackerService;

/**
 * The Class PortfolioTracker.
 */
public class PortfolioTracker {

	
/**
 * The main method.
 *
 * @param args the arguments
 */
public static void main(String[] args) {
		
		start(args);	
	}

/**
 * Start.
 *
 * @param args the args
 */
public static void start(String[] args) {
	
	Long cycleStartTime = System.currentTimeMillis();
	AbstractApplicationContext context = new AnnotationConfigApplicationContext(BaseConfig.class);
	context.registerShutdownHook();
	PortfolioTrackerService portfolioTrackerService = context.getBean(PortfolioTrackerService.class);
	
	try {	
		portfolioTrackerService.startService(args);
	} catch(PortfolioTrackerException e){
		System.out.println("Exception occured while processing Portfolio Tracker Services : "+ e.getErrorMessage());
	
	}
	catch(Exception e){
		System.out.println("Generic Exception occured while processing Portfolio Tracker Services : "+ e.getMessage());
	
	}
	context.close();
	context.destroy();
	System.out.println("Total time taken by the transaction is :"+(System.currentTimeMillis()-cycleStartTime) +"milliseconds");
}
}
