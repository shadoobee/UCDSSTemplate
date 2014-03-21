package edu.ucdavis.ss.lmsreports.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import edu.ucdavis.its.authpack.AuthenticationPackConstants;
import edu.ucdavis.its.authpack.ShibHeader;
import edu.ucdavis.its.authpack.ShibHeaderValues;
import edu.ucdavis.its.authpack.service.SpoofableUsersService;
import edu.ucdavis.its.authpack.service.StringEncrypterService;
import edu.ucdavis.its.authpack.service.UserSpoofingDetilsService;
import edu.ucdavis.its.authpack.util.LoggingUtils;

@Service("UserSpoofingDetailsService")
public class UserSpoofingDetailsService implements UserSpoofingDetilsService {
	
	private SpoofableUsersService spoofableUsersService;
	private StringEncrypterService stringEncrypterService;
	
	private boolean spoofingEnabled;
	private boolean encryptHeaderValues;
	private boolean generateNewTimestamps;
	private boolean generateNewUuids;
	
	private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(UserSpoofingDetailsService.class);
	
	public static boolean spoofCheck;
		
	public void init() {
		LoggingUtils.highlightLogMessages(LOG, LoggingUtils.LogTypeEnum.INFO
				, String.format("%s has been started:", UserSpoofingDetailsService.class.getSimpleName())
				, String.format("%s = %s", "spoofingEnabled", spoofingEnabled)
				, String.format("%s = %s", "encryptHeaderValues", encryptHeaderValues)
				, String.format("%s = %s", "generateNewTimestamps", generateNewTimestamps)
				, String.format("%s = %s", "generateNewUuids", generateNewUuids)
				);
		spoofCheck = spoofingEnabled;
	}
	
	
	public ShibHeaderValues encryptShibHeaderValuesToNew(ShibHeaderValues value) {
		
		
		ShibHeaderValues result = new ShibHeaderValues();
		for(ShibHeader header: ShibHeader.values()) {
			if (!header.getIgnore()) 
				
				result.put(header, stringEncrypterService.encrypt(value.get(header)));
		}
		return result;
		
		
	}
	
	public ShibHeaderValues copyShibHeaderValuesToNew(ShibHeaderValues value) {
		return value.clone();
	}
	
	public void addNewUUID(ShibHeaderValues value) {
		value.put(ShibHeader.RANDOM_UUID_STRING, UUID.randomUUID().toString());
	}
	
	public void addTimestamp(ShibHeaderValues value) {
		value.put(ShibHeader.TIMESTAMP, AuthenticationPackConstants.dtFmt.print(new org.joda.time.DateTime()));
	}
	
	
	
	/* (non-Javadoc)
	 * @see edu.ucdavis.its.authpack.service.impl.UserSpoofingDetilsService#getSpoofableUsersList()
	 */
	public List<ShibHeaderValues> getSpoofableUsersList() {
		if (spoofingEnabled) {
			List<ShibHeaderValues> rawUsers = spoofableUsersService.getSpoofableUsers();
			List<ShibHeaderValues> results = new ArrayList<ShibHeaderValues>();
			
			for (ShibHeaderValues user : rawUsers) {
				ShibHeaderValues cUser = user.clone();
				if (generateNewTimestamps) 
					addTimestamp(cUser);
				if (generateNewUuids) 
					addNewUUID(cUser);
				cUser.put(ShibHeader.APPLICATION_ONLY, cUser.getShibUserId());
				ShibHeaderValues nUser = encryptHeaderValues?encryptShibHeaderValuesToNew(cUser):cUser;
				if (LOG.isDebugEnabled()) {
					LOG.debug(String.format("Adding user to result list with raw value object: %s", user));
					LOG.debug(String.format("After processing user value object is %s", nUser));
				}
				
				results.add(nUser);
				
			}
			return results;
		} else {
			throw new IllegalStateException("Spoofing is not enabled, but getSpoofableUsersList was called.");
		}
	}
	
	/* (non-Javadoc)
	 * @see edu.ucdavis.its.authpack.service.impl.UserSpoofingDetilsService#getSpoofableUsersMap()
	 */
	public Map<String,ShibHeaderValues> getSpoofableUsersMap() {
		Map<String,ShibHeaderValues> result = new HashMap<String,ShibHeaderValues>();
		for (ShibHeaderValues value: getSpoofableUsersList()) {
			result.put(value.getShibUserId(), value);
		}
		return result;
	}

	public void setSpoofableUsersService(SpoofableUsersService spoofableUsersService) {
		this.spoofableUsersService = spoofableUsersService;
	}

	public void setStringEncrypterService(
			StringEncrypterService stringEncrypterService) {
		this.stringEncrypterService = stringEncrypterService;
	}

	public void setSpoofingEnabled(boolean spoofingEnabled) {
		this.spoofingEnabled = spoofingEnabled;
	}


	public void setEncryptHeaderValues(boolean encryptHeaderValues) {
		this.encryptHeaderValues = encryptHeaderValues;
	}


	public void setGenerateNewTimestamps(boolean generateNewTimestamps) {
		this.generateNewTimestamps = generateNewTimestamps;
	}


	public void setGenerateNewUuids(boolean generateNewUuids) {
		this.generateNewUuids = generateNewUuids;
	}

}
