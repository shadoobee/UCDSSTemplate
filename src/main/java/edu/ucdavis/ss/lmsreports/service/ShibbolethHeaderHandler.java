package edu.ucdavis.ss.lmsreports.service;



import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.ucdavis.its.authpack.ShibHeaderValues;
import edu.ucdavis.its.authpack.ShibPostAgeException;
import edu.ucdavis.its.authpack.ShibPostTokenException;
import edu.ucdavis.its.authpack.service.ShibHandlerService;



@Component
public class ShibbolethHeaderHandler implements HeaderHandler {

	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ShibbolethHeaderHandler.class);
	
	@Autowired
	private ShibHandlerService shibHandlerService;
	
	

	@Override
	public ShibHeaderValues handle(HttpServletRequest request) {
		
		ShibHeaderValues results;
		try {
			results = shibHandlerService.handle(request);
		} catch (ShibPostTokenException e) {
			LOG.error("ShibPostTokenExcpetion - token has been reposted.", e);
			throw new RuntimeException("ShibPostTokenExcpetion - token has been reposted.", e);
		} catch (ShibPostAgeException e) {
			LOG.error("ShibPostTokenExcpetion - token has expired.", e);
			throw new RuntimeException("ShibPostTokenExcpetion - token has expired.", e);
		}


		return results;
	}	
}
