package edu.ucdavis.ss.lmsreports.service;

import java.util.ArrayList;

import java.util.List;

import org.apache.log4j.Logger;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import edu.ucdavis.its.authpack.ShibHeaderValues;
import edu.ucdavis.its.authpack.util.ShibHeaderSessionInjector;



public class UserDetailsService implements AuthenticationUserDetailsService {
	
	private static Logger log = Logger.getLogger(UserDetailsService.class); 


	public UserDetails loadUserDetails(Authentication a) throws UsernameNotFoundException{
		
	 String username = (String) a.getPrincipal();
  
    ServletRequestAttributes sra = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes());
    HttpServletRequest request = sra.getRequest();
    log.debug("***********USERNAME " + username);
    
    //load Shibboleth Values (will be unencrypted)
    ShibHeaderValues shibValues = (ShibHeaderValues)request.getSession(false).getAttribute(ShibHeaderSessionInjector.SHIB_DEFAULT_SESSION_INJECTION_KEY);
    log.debug("shib values - last name: " + shibValues.getShibLastName() + " user id: " + shibValues.getShibUserId() + " email: " + shibValues.getShibEmailAddress());
	
    String loginId = extractLoginIdFromUsername(username, shibValues.getShibIdentityProvider());
    
    List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(1);
    authList.add(new GrantedAuthorityImpl("0"));
    GrantedAuthority[] auth = new GrantedAuthority[authList.size()];
    authList.toArray(auth);

    return  new org.springframework.security.core.userdetails.User(loginId, loginId, true, true, true, true, authList);
	
}
	
	 private String extractLoginIdFromUsername(String username, String identityProvider) {
		  String loginId = null;
		  
		  if(identityProvider.toLowerCase().indexOf("davis") > 0) {
			  int endString = username.indexOf("@"); 
			  loginId = username.substring(0, endString);
		  }
		  
	     if (loginId == null || loginId == "") {
	    	throw new UsernameNotFoundException("Unable to extract valid Login ID using eppn " + username);
	     }
		  
         log.debug("Login ID extracted as :" + loginId);
         return loginId;
	  }

}
