package com.fifthgentech.sample.PortfolioTracker.helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.fifthgentech.sample.PortfolioTracker.exception.PortfolioTrackerException;

/**
 * The Class PortfolioTrackerServiceHelper.
 */
@Component
public class PortfolioTrackerServiceHelper {

	/**
	 * Retrieve file content.
	 *
	 * @param filePath the file path
	 * @return the stream
	 * @throws PortfolioTrackerException the portfolio tracker exception
	 */
	public Stream<String> retrieveFilecontent(String filePath) throws PortfolioTrackerException{
		
		try {
			return Files.lines(Paths.get(filePath));
		} catch (IOException e) {
			throw new PortfolioTrackerException("IO Exception occured while reading the file content");
		}
	}
	
	/**
	 * Process content.
	 * returns the sorted content
	 * @param content the content
	 * @return the stream
	 */
	public Stream<String> processContent(Stream<String>  content){		
		
		return content.sorted(Comparator.comparing(defineSum()).reversed());
		

	}

	/**
	 * Define sum.
	 *
	 * @return the function<? super string,? extends integer>
	 */
	private Function<? super String, ? extends Integer> defineSum() {
		return line ->
			 Arrays.stream(line.split(",")).map(text->StringUtils.substringAfter(text, "-").trim().replace(" ", "")).
					mapToInt(value->Integer.valueOf(value)).sum();
	}
	
}
