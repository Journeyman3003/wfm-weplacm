package org.wwu.bpm.wfm.weplacm.processJobInquiry.services;

import java.util.ArrayList;
import java.util.logging.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.dbConnector.Candidate;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.dbConnector.MySQLConnector;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.dbConnector.Skill;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.entity.CV;

public class AddCandidateToDatabase implements JavaDelegate{

	private final static Logger LOGGER = Logger.getLogger("LOAN-REQUESTS");

	public void execute(DelegateExecution execution) throws Exception {
	  LOGGER.info("Processing request by '" + execution.getVariable("customerId") + "'...");
	  LOGGER.info("Loading temporary CV into environment...");
	  CV cv = (CV) execution.getVariable("tempCV");
	  if (!(cv == null) && !(cv.getEmail() == null) && !(cv.getName() == null) && !(cv.getSkills() == null)) {
		  Candidate candidate = new Candidate();
		  candidate.setEmail(cv.getEmail());
		  candidate.setName(cv.getName());
		  ArrayList<Skill> skills = new ArrayList<Skill>();
		  //don't even ask about that regex -> replace any common delimiter ("comma","period","semicolon") by "comma" and remove whitespace after commas
		  for (String stringSkill : cv.getSkills().replaceAll(",\\s*",",").replaceAll("\\.\\s*",",").replaceAll(";\\s*",",").split(",")) {
			  skills.add(new Skill(stringSkill));
		  }
		  candidate.setSkills(skills);
		  MySQLConnector.addCandidateToDatabase(candidate);
		  LOGGER.info("candidate added");
	  } else {
		  LOGGER.info("CV could not be loaded successfully...skipped"); 
	  }

	  
	}	
}
