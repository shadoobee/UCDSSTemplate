package edu.ucdavis.ss.lmsreports.domain;

public class Domain {
	int domainId;
	String domainName;
	int level;
	String domainNamePadded;



	public String getDomainNamePadded() {
		StringBuffer sb = new StringBuffer();
		for(int i =0; i<level; i++){
			sb.append("--");
		}
		sb.append(domainName);
	
		return sb.toString();
	}
	public void setDomainNamePadded(String domainNamePadded) {
		this.domainNamePadded = domainNamePadded;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getDomainId() {
		return domainId;
	}
	public void setDomainId(int domainId) {
		this.domainId = domainId;
	}
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	

}
