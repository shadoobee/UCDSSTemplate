/*******************************************************************************
 * Copyright © 2011 The Regents of the University of California. All 
 * Rights Reserved.
 *   
 * Unpublished rights reserved under the copyright laws of the United 
 * States. The Software contained on this media is proprietary to and 
 * embodies the confidential technology of the University of California, 
 * Davis. Possession, use, duplication or dissemination of the software 
 * and media is authorized only pursuant to a valid written license from
 * University of California, Davis.
 *   
 * RESTRICTED RIGHTS LEGEND
 * Use, duplication, or disclosure by the U.S. Government is subject to 
 * restrictions as set forth in Subparagraph (c) (1) (ii) of DFARS 
 * 252.227-7013 or in FAR 52.227-19, as applicable.
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ******************************************************************************/
package edu.ucdavis.ss.lmsreports.web;


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import edu.ucdavis.ss.lmsreports.domain.Person;
import edu.ucdavis.ss.lmsreports.domain.Reports;
import edu.ucdavis.ss.lmsreports.service.LMSReportService;
import edu.ucdavis.ss.lmsreports.service.PersonService;



@Controller
public class LMSReportController {
	
	
     
    @Autowired
    private PersonService personService;
    
    @Autowired
    private LMSReportService lmsReportService;


	





	@RequestMapping(value="/index.htm", method = RequestMethod.POST)
	public String handleRequest(Model model,	HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
		personService.reconcilePerson(request);
		}catch( IllegalAccessException e){
			model.addAttribute("access", false);
			return "login";
		}catch(Exception e){
			return "login";
		}
		if(request.getSession() == null){
			model.addAttribute("access", true);
			return "login";
		}
		Person person= (Person)request.getSession().getAttribute("person");
	
		if(person == null){
			 return "login";
		}
		List<Reports> reports = lmsReportService.getReportList();
		model.addAttribute("formBean", reports);
		model.addAttribute("access", true);
		return "index";
	}
	

	
	
	
	


	
	
	@RequestMapping(value="/index.htm", method = RequestMethod.GET)
	public String indexPage(Model model,	HttpServletRequest request, HttpServletResponse response) throws Exception{
	
		Person person=(Person)request.getSession().getAttribute("person");
		if(person == null){
			return "login";
		}
		
		List<Reports> reports = lmsReportService.getReportList();
		model.addAttribute("formBean", reports);
		return "index";
	}
	
	}