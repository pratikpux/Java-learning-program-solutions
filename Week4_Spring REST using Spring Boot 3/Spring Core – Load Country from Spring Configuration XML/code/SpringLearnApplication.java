package com.cognizant.spring_learn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringLearnApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

	public static void main(String[] args) {
		LOGGER.debug("Starting Spring Learn Application");
		displayCountry();
	}

	public static void displayCountry() {
		// Load Spring configuration file and create application context
		ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");

		// Get the country bean from the context
		Country country = (Country) context.getBean("country", Country.class);

		// Display country details
		LOGGER.debug("Country : {}", country.toString());
	}
}