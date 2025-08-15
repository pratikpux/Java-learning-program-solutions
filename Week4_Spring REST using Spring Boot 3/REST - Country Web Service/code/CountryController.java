package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.model.Country;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {

    // Using both annotations for better compatibility
    @RequestMapping("/country")
    @GetMapping("/country")
    public Country getCountryIndia() {
        // Load Spring XML configuration
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        // Get India bean from spring context
        Country indiaCountry = (Country) context.getBean("indiaCountry");

        // Return the country object (will be automatically converted to JSON)
        return indiaCountry;
    }
}