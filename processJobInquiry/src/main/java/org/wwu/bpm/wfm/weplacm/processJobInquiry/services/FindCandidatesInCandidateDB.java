package org.wwu.bpm.wfm.weplacm.processJobInquiry.services;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.dbConnector.Candidate;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.dbConnector.MySQLConnector;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.dbConnector.Skill;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.entity.JobInquiry;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.mail.Mailer;

public class FindCandidatesInCandidateDB implements JavaDelegate{

	private final static Logger LOGGER = Logger.getLogger("LOAN-REQUESTS");

	public void execute(DelegateExecution execution) throws Exception {
	  LOGGER.info("Processing request by '" + execution.getVariable("customerId") + "'...");
	  LOGGER.info("Looking for skill requirements in variable jobInquiry...");
	  JobInquiry jobInquiry = (JobInquiry) execution.getVariable("jobInquiry");
	  
	  if(!(jobInquiry == null) && !(jobInquiry.getCandidateProfile() == null)) {
		  ArrayList<Skill> skills = new ArrayList<Skill>();
		  for (String task : jobInquiry.getCandidateProfile()) {
			  skills.add(new Skill(task));
		  }
		  ArrayList<Candidate> candidateList = MySQLConnector.getCandidatesWithSkill(skills);
		  
		  ObjectValue typedCandidateList = Variables.objectValue(candidateList).serializationDataFormat("application/json").create();
		  execution.setVariable("adequateCandidates", typedCandidateList);
	  } 
	  


	  
	  
	}	
}
