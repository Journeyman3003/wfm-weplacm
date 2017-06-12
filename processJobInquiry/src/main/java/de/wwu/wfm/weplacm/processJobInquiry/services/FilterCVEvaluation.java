package de.wwu.wfm.weplacm.processJobInquiry.services;

import java.util.logging.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class FilterCVEvaluation implements JavaDelegate{
	
	private final static Logger LOGGER = Logger.getLogger("JOB-INQUIRY");

	public void execute(DelegateExecution execution) throws Exception {
	  LOGGER.info("Processing request by '" + execution.getVariable("customerId") + "'...");
	}	
}
