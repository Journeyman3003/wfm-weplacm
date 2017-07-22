package org.wwu.bpm.wfm.weplacm.processJobInquiry.entity;

public class NoCVAvailableResponse {
	private final boolean availability = false;
	private String processID;
	
	public NoCVAvailableResponse(){}
	public NoCVAvailableResponse(String processID){
		this.processID = processID;
	}
	public String getProcessID() {
		return processID;
	}
	public void setProcessID(String processID) {
		this.processID = processID;
	}
}
