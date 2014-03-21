package edu.ucdavis.ss.lmsreports.service;

import java.util.Properties;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
@Component
public class BuildDetailsServiceImpl implements BuildDetailsService {

	private String buildVersionNumber;
	private String buildDateString;
	private String buildProjectName;
    private Properties appProperties;
    
    
	
	public Properties getAppProperties() {
		return appProperties;
	}
	
	@Resource
	public void setAppProperties(Properties appProperties) {
		this.appProperties = appProperties;
	}
	public String getBuildDateString() {
	    return buildDateString;
	}
	public void setBuildDateString(String buildDateString) {
				this.buildDateString = buildDateString;
	}
	public String getBuildProjectName() {
		return buildProjectName;
	}
	public void setBuildProjectName(String buildProjectName) {
		this.buildProjectName = buildProjectName;
	}
	public String getBuildVersionNumber() {
		return buildVersionNumber;
	}
	public void setBuildVersionNumber(String buildVersionNumber) {
		this.buildVersionNumber = buildVersionNumber;
	}

}
