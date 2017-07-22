package org.wwu.bpm.wfm.weplacm.processJobInquiry.entity;

import java.util.ArrayList;

public class CVCollectionEnvelope {
	private ArrayList<CV> cvCollection;
	private String processInstanceId;
	
	public CVCollectionEnvelope() {}
	public CVCollectionEnvelope(ArrayList<CV> cvCollection, String processInstanceId) {
		this.cvCollection = cvCollection;
		this.processInstanceId = processInstanceId;
	}

	public ArrayList<CV> getCvCollection() {
		return cvCollection;
	}

	public void setCvCollection(ArrayList<CV> cvs) {
		this.cvCollection = cvs;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
}
