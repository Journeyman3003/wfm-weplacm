package org.wwu.bpm.wfm.weplacm.processJobInquiry.entity;

public class CVEnvelope {
	private String processInstanceId;
	private CV cv;
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public CV getCv() {
		return cv;
	}
	public void setCv(CV cv) {
		this.cv = cv;
	}
	
	public CVEnvelope() {}
	
}
