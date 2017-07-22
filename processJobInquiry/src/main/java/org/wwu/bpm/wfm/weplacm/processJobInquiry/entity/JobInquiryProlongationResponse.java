package org.wwu.bpm.wfm.weplacm.processJobInquiry.entity;

import java.util.Date;

public class JobInquiryProlongationResponse {
	private Date deadline;
	private String processInstanceId;
	
	public JobInquiryProlongationResponse() {}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
}
