package edu.ucdavis.ss.lmsreports.domain;

public class Status {
	
	private int statusId;
	private String statusDesc;
	private int statusCd;
	private int reportId;
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public int getStatusCd() {
		return statusCd;
	}
	public void setStatusCd(int statusCd) {
		this.statusCd = statusCd;
	}
	public int getReportId() {
		return reportId;
	}
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	
	

}
