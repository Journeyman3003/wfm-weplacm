package org.wwu.bpm.wfm.weplacm.processJobInquiry.entity;

public class JobInquiryApproval {
	private String peerProcessInstanceId;
	private String ownProcessInstanceId;
	
	public JobInquiryApproval() {}
	public JobInquiryApproval(String peer, String own) {
		this.ownProcessInstanceId = own;
		this.peerProcessInstanceId = peer;
	}

	public String getOwnProcessInstanceId() {
		return ownProcessInstanceId;
	}

	public void setOwnProcessInstanceId(String ownProcessInstanceId) {
		this.ownProcessInstanceId = ownProcessInstanceId;
	}

	public String getPeerProcessInstanceId() {
		return peerProcessInstanceId;
	}

	public void setPeerProcessInstanceId(String peerProcessInstanceId) {
		this.peerProcessInstanceId = peerProcessInstanceId;
	}
	
	
}
