package org.wwu.bpm.wfm.weplacm.processJobInquiry.sendMessages;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import org.wwu.bpm.wfm.weplacm.processJobInquiry.httpClient.HttpClient; 

public class RequestAdditionalInformation implements JavaDelegate{

	private final static Logger LOGGER = Logger.getLogger("LOAN-REQUESTS");

	public void execute(DelegateExecution execution) throws Exception {
	  LOGGER.info("Processing request by '" + execution.getVariable("customerId") + "'...");
	  	HttpClient.postJobInquiryResponse("http://localhost", true, "123");
	}	
	
}