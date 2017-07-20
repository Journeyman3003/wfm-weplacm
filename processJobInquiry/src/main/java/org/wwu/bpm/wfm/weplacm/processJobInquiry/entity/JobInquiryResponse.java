package org.wwu.bpm.wfm.weplacm.processJobInquiry.entity;

public class JobInquiryResponse {
	private Boolean accepted;
	private String processInstanceID;
	
	public JobInquiryResponse() {}
	public JobInquiryResponse(Boolean accepted, String processInstanceID) {
		this.accepted = accepted;
		this.processInstanceID = processInstanceID;
	}

	public Boolean getAccepted() {
		return accepted;
	}

	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}

	public String getProcessInstanceID() {
		return processInstanceID;
	}

	public void setProcessInstanceID(String processInstanceID) {
		this.processInstanceID = processInstanceID;
	};
	
}
