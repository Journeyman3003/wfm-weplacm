package org.wwu.bpm.wfm.weplacm.processJobInquiry.services;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.dbConnector.MySQLConnector;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.entity.CV;

public class CheckCandidateExistence implements JavaDelegate{

	private final static Logger LOGGER = Logger.getLogger("LOAN-REQUESTS");

	public void execute(DelegateExecution execution) throws Exception {
	  LOGGER.info("Processing request by '" + execution.getVariable("customerId") + "'...");
	  CV cv = (CV) execution.getVariable("tempCV");
	  if (!(cv == null) && !(null == cv.getEmail())) {
		  LOGGER.info("Checking Candidate Existence for CV " + cv.getEmail() +"...");
		  Boolean existing = MySQLConnector.checkCandidateExistence(cv);
		  LOGGER.info("Does the candidate exst already? - " + existing + "...");
		  execution.setVariable("existing", existing);
	  } else {
		  //default fallback: set existing to true if something went wrong
		  LOGGER.info("CV was not correctly loaded, setting existing to default true...");
		  execution.setVariable("existing", true);
	  }
	  
	}	
	
}
