package org.wwu.bpm.wfm.weplacm.processJobInquiry.entity;

import java.util.Date;

public class JobInquiry  {
	private String processId;
	private String title;
	private String requiredGraduation;
	private String location;
	private Date deadline;
	//no add/remove necessary
	private String[] candidateProfile;
	private String[] taskList; //lel
	
	//required for GSON?
	public JobInquiry() {
		
		processId = "";
		title = "";
		requiredGraduation = "";
		location = "";
		deadline = null;
		candidateProfile = null;
		taskList = null;
	}
	
	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRequiredGraduation() {
		return requiredGraduation;
	}
	public void setRequiredGraduation(String requiredGraduation) {
		this.requiredGraduation = requiredGraduation;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public String[] getCandidateProfile() {
		return candidateProfile;
	}
	public void setCandidateProfile(String[] candidateProfile) {
		this.candidateProfile = candidateProfile;
	}
	public String[] getTaskList() {
		return taskList;
	}
	public void setTaskList(String[] taskList) {
		this.taskList = taskList;
	}
}
