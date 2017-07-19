package com.fifthgentech.sample.PortfolioTracker.service;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fifthgentech.sample.PortfolioTracker.exception.PortfolioTrackerException;
import com.fifthgentech.sample.PortfolioTracker.helper.PortfolioTrackerServiceHelper;

/**
 * The Class PortfolioTrackerService.
 */
@Component
public class PortfolioTrackerService {

	/** The portfolio tracker service helper. */
	@Autowired
	private PortfolioTrackerServiceHelper portfolioTrackerServiceHelper;
	
	/**
	 * Start service.
	 *
	 * @param args the args
	 * @throws PortfolioTrackerException the portfolio tracker exception
	 */
	public void startService(String[] args) throws PortfolioTrackerException  {
		
		if(args.length==0||StringUtils.isEmpty(args[0])){
			throw new PortfolioTrackerException("filepath is not provided through commandline");
		}
		Stream<String> fileContent = portfolioTrackerServiceHelper.retrieveFilecontent(args[0]);
		Stream<String>  sortedContent = portfolioTrackerServiceHelper.processContent(fileContent);
		sortedContent.forEach(System.out::println);
	}
	
	

}
