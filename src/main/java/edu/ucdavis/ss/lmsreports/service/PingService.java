package edu.ucdavis.ss.lmsreports.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import edu.ucdavis.ss.lmsreports.data.PingDao;


public class PingService {
	@Autowired
	PingDao pingDao;
	public String pingDb(HttpServletRequest request, HttpServletResponse response){
		return pingDao.pingDB();
		
	}

}
