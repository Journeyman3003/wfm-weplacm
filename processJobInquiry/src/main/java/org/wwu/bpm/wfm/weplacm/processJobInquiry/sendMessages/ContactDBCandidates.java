package org.wwu.bpm.wfm.weplacm.processJobInquiry.sendMessages;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.dbConnector.Candidate;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.entity.JobInquiry;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.mail.Mailer;

public class ContactDBCandidates implements JavaDelegate{

	private final static Logger LOGGER = Logger.getLogger("LOAN-REQUESTS");

	public void execute(DelegateExecution execution) throws Exception {
	  LOGGER.info("Processing request by '" + execution.getVariable("customerId") + "'...");
	  LOGGER.info("Sending invivations to all DB candidates");
	  
	  @SuppressWarnings("unchecked")
	  ArrayList<Candidate> adequateCandidates = (ArrayList<Candidate>) execution.getVariable("adequateCandidates");
	  
	  JobInquiry jobInquiry = (JobInquiry) execution.getVariable("jobInquiry");
	  
	  //crash backup
	  if (adequateCandidates == null) adequateCandidates = new ArrayList<Candidate>();
	  for (Candidate candidate : adequateCandidates) {
		  Mailer.send("recruiting.weplacm@gmail.com", "DieAntwortIst42", candidate.getEmail(), "We Want You!", candidate.getName(), jobInquiry.getTitle(),0);
	  }
	}	
	
}

