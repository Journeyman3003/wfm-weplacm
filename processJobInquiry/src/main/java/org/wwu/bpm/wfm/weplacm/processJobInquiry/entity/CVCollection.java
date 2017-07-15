package org.wwu.bpm.wfm.weplacm.processJobInquiry.entity;

import java.util.ArrayList;

public class CVCollection {
	private ArrayList<CV> cvs;
	
	public CVCollection() {
		this.cvs = new ArrayList<CV>();
	}

	public ArrayList<CV> getCvs() {
		return cvs;
	}

	public void setCvs(ArrayList<CV> cvs) {
		this.cvs = cvs;
	}
}
