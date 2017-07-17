package org.wwu.bpm.wfm.weplacm.processJobInquiry.services;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import org.wwu.bpm.wfm.weplacm.processJobInquiry.mail.Mailer;

public class AddCVToCollectionDB implements JavaDelegate{

	private final static Logger LOGGER = Logger.getLogger("JOB-INQUIRY");

	public void execute(DelegateExecution execution) throws Exception {
	  LOGGER.info("Processing request by '" + execution.getVariable("customerId") + "'...");
	 
	}	
}
