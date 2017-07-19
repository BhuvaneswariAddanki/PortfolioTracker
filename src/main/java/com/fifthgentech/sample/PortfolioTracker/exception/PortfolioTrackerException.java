package com.fifthgentech.sample.PortfolioTracker.exception;

import lombok.Getter;

/**
 * The Class PortfolioTrackerException.
 */
public class PortfolioTrackerException extends Exception {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Gets the error message.
	 *
	 * @return the error message
	 */
	@Getter
	private final String errorMessage;
	
	/**
	 * Instantiates a new portfolio tracker exception.
	 *
	 * @param errorMessage the error message
	 */
	public PortfolioTrackerException(String errorMessage){
		this.errorMessage=errorMessage;
	}
}
