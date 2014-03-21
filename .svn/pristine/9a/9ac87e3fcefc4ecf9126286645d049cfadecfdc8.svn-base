package edu.ucdavis.ss.lmsreports.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.ucdavis.its.authpack.ShibHeaderValues;
import edu.ucdavis.its.authpack.service.SpoofableUsersService;

@Service
public class ConfigurableSpoofableUserService  implements SpoofableUsersService {
	
	private List<ShibHeaderValues> SpoofableUsersList;

	public void init() {
		
	}
	
	/* (non-Javadoc)
	 * @see edu.ucdavis.its.authpack.service.impl.SpoofableUsersService#getSpoofableUsers()
	 */
	public List<ShibHeaderValues> getSpoofableUsers() {
		return SpoofableUsersList;
	}

	public List<ShibHeaderValues> getSpoofableUsersList() {
		return SpoofableUsersList;
	}


	public void setSpoofableUsersList(List<ShibHeaderValues> spoofableUsersList) {
		this.SpoofableUsersList = spoofableUsersList;
	}
}
