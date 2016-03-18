package com.counter.demo.rest.api.utils;

import org.springframework.web.servlet.ModelAndView;

/**
 * Utils for dealing with Spring MVC
 * 
 * @author Pratik Tandon
 * 
 */
public class SpringMVCUtils {

	public static final String SINGLE_OBJECT_RESPONSE = "responseObject";
		public static ModelAndView getOutputModel(Object obj) {
		return new ModelAndView("", SINGLE_OBJECT_RESPONSE, obj);
	}
}
