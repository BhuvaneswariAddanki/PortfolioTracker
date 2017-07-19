package com.fifthgentech.sample.PortfolioTracker.main;

import org.junit.Test;

/**
 * The Class PortfolioTrackerTest.
 */
public class PortfolioTrackerTest {

	/**
	 * Main test.
	 */
	@Test
	public void mainTest(){
		String[] args={"src/test/resources/sample.txt"};
		PortfolioTracker.main(args);
	}
	
	/**
	 * Main testwithout file.
	 */
	@Test
	public void mainTestwithoutFile(){
		String[] args={"src/test/resources/sample1.txt"};
		PortfolioTracker.main(args);
	}

}
