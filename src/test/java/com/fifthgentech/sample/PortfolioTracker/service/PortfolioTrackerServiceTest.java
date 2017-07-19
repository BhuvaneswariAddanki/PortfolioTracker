package com.fifthgentech.sample.PortfolioTracker.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.fifthgentech.sample.PortfolioTracker.exception.PortfolioTrackerException;
import com.fifthgentech.sample.PortfolioTracker.helper.PortfolioTrackerServiceHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class PortfolioTrackerServiceTest.
 */
public class PortfolioTrackerServiceTest {
	
	/** The Constant SRC_TEST_RESOURCES_NOFILE_TXT. */
	private static final String SRC_TEST_RESOURCES_NOFILE_TXT = "src/test/resources/nofile.txt";

	/** The Constant SRC_TEST_RESOURCES_FILE_TXT. */
	private static final String SRC_TEST_RESOURCES_FILE_TXT = "src/test/resources/sample.txt";

	/** The content. */
	private static List<String> content;
	
	/** The reponse. */
	private static List<String> reponse;

	/** The portfolio tracker service helper. */
	@Mock
	PortfolioTrackerServiceHelper portfolioTrackerServiceHelper;

	/** The portfolio tracker service. */
	@InjectMocks
	PortfolioTrackerService portfolioTrackerService;
	
	/**
	 * Sets the up.
	 */
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
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
	 * Framing response.
	 */
	private static void framingResponse() {
		reponse=new ArrayList<>();
		reponse.add("GOOG - 100, AMZN - 90, MS - 80");	
		reponse.add("SGI - 100, GOOG - 50, MS - 10");
		reponse.add("GOOG - 50, MS - 10");
		
		
	}
	
	/**
	 * Start service test.
	 *
	 * @throws PortfolioTrackerException the portfolio tracker exception
	 */
	@Test(expected=PortfolioTrackerException.class)
	public void startServiceTest() throws PortfolioTrackerException{
		String[] args ={};
		portfolioTrackerService.startService(args);
	}
	
	/**
	 * Start service test without file.
	 *
	 * @throws PortfolioTrackerException the portfolio tracker exception
	 */
	@Test
	public void startServiceTestWithoutFile() throws PortfolioTrackerException  {
		String[] args ={SRC_TEST_RESOURCES_NOFILE_TXT};
		Mockito.when(portfolioTrackerServiceHelper.retrieveFilecontent(SRC_TEST_RESOURCES_NOFILE_TXT)).thenThrow(new PortfolioTrackerException("IO Exception occured while reading the file content") );
		try{
		portfolioTrackerService.startService(args);
		assertTrue(false);
		}catch(PortfolioTrackerException e){
			assertEquals(e.getErrorMessage(),"IO Exception occured while reading the file content");
		}
		Mockito.verify(portfolioTrackerServiceHelper).retrieveFilecontent(SRC_TEST_RESOURCES_NOFILE_TXT);
	}
	
	/**
	 * Start service test with content.
	 *
	 * @throws PortfolioTrackerException the portfolio tracker exception
	 */
	@Test
	public void startServiceTestWithContent() throws PortfolioTrackerException{
		String[] args ={SRC_TEST_RESOURCES_FILE_TXT};
		framingContent();
		framingResponse();
		Mockito.when(portfolioTrackerServiceHelper.retrieveFilecontent(SRC_TEST_RESOURCES_FILE_TXT)).thenReturn(content.stream());
		Mockito.when(portfolioTrackerServiceHelper.processContent(Mockito.any())).thenReturn(reponse.stream());
		portfolioTrackerService.startService(args);		
		Mockito.verify(portfolioTrackerServiceHelper).retrieveFilecontent(SRC_TEST_RESOURCES_FILE_TXT);
	}
}
