package com.counter.demo.rest.api.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.counter.demo.rest.api.model.Count;
import com.counter.demo.rest.api.model.SearchText;
import com.counter.demo.rest.api.service.ICounterService;
import com.counter.demo.rest.api.utils.CounterApiUtil;
import com.counter.demo.rest.api.utils.SpringMVCUtils;

@Controller
/**
 * This class has the logic for acting on web requests.
 * 
 * @author Pratik Tandon
 *
 */
public class WelcomeController {
	//Values from the properties file
	@Value("#{props.reportname}")
    private String reportName;
	@Value("#{props.txtdocumentName}")
    private String txtdocumentName;
	@Value("#{props.searchparam}")
	private String searchParam;
	
	@Autowired
	private ICounterService counterService;
	@RequestMapping(value = "/")
	public ResponseEntity<String> hellos() {
		return new ResponseEntity<String>("<h1>Hello!</h1>", HttpStatus.OK);
	}

	@RequestMapping(value = "/counterapi/search",produces={MediaType.APPLICATION_JSON_VALUE}, method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView searchParagraph(@RequestParam(required = true, value = "searchText") String searchText) throws IOException {
		File file = new File(getClass().getClassLoader().getResource(txtdocumentName).getFile());
		TreeMap<String, Integer>  p = counterService.searchParagraph(file,searchText); 
		return SpringMVCUtils.getOutputModel(Count.createNew(p));
	}
	
	@RequestMapping(value = "/counterapi/top/{id}",produces={MediaType.APPLICATION_JSON_VALUE}, method = { RequestMethod.GET,
			RequestMethod.POST })
	public void searchTop(HttpServletResponse response,
			@PathVariable(value = "id") int id) throws IOException {
		response.setContentType("text/csv");
		File file = new File(getClass().getClassLoader().getResource(txtdocumentName).getFile());
		response.setHeader("Content-disposition", "attachment;filename="+ reportName);
		Map<String, Integer> result = counterService.searchTop(file, searchParam, id);
    	CounterApiUtil.csvResponse(result,response);
	}
		
	
	
}
