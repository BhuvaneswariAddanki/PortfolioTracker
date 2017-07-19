package com.fifthgentech.sample.PortfolioTracker.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.BeforeClass;
import org.junit.Test;

import com.fifthgentech.sample.PortfolioTracker.exception.PortfolioTrackerException;

/**
 * The Class PortfolioTrackerServiceHelperTest.
 */
public class PortfolioTrackerServiceHelperTest {

	/** The portfolio tracker service helper. */
	PortfolioTrackerServiceHelper portfolioTrackerServiceHelper = new PortfolioTrackerServiceHelper();

	/** The content. */
	private static List<String> content;

	/**
	 * Sets the up.
	 */
	@BeforeClass
	public static void setUp(){
		framingContent();
	}

	/**
	 * Framing content.
	 */
	private static void framingContent() {
		content=new ArrayList<>();
		content.add("GOOG - 50, MS - 10");
		content.add("SGI - 100, GOOG - 50, MS - 10");
		content.add("GOOG - 100, AMZN - 90, MS - 80");		
	}

	/**
	 * Retrieve filecontent test for exception.
	 *
	 * @throws PortfolioTrackerException the portfolio tracker exception
	 */
	@Test(expected=PortfolioTrackerException.class)
	public void retrieveFilecontentTestForException() throws PortfolioTrackerException{
		portfolioTrackerServiceHelper.retrieveFilecontent("src/test/resources/samplefile.txt");
	}

	/**
	 * Retrieve filecontent testfor success.
	 *
	 * @throws PortfolioTrackerException the portfolio tracker exception
	 */
	@Test
	public void retrieveFilecontentTestforSuccess() throws PortfolioTrackerException{
		Stream<String> response =portfolioTrackerServiceHelper.retrieveFilecontent("src/test/resources/sample.txt");
		assertNotNull(response);
		response.forEach(line->assertTrue(content.stream().filter(actualLine->actualLine.equalsIgnoreCase(line)).findAny().isPresent()));
	}

	/**
	 * Process content test.
	 */
	@Test
	public void processContentTest(){
		Stream<String> sortedResponse = portfolioTrackerServiceHelper.processContent(content.stream());
		List<String> responseList=sortedResponse.collect(Collectors.toList());
		assertEquals(responseList.size(),3);
		assertEquals(responseList.get(0),"GOOG - 100, AMZN - 90, MS - 80");
		assertEquals(responseList.get(1),"SGI - 100, GOOG - 50, MS - 10");
		assertEquals(responseList.get(2),"GOOG - 50, MS - 10");
	}
}
