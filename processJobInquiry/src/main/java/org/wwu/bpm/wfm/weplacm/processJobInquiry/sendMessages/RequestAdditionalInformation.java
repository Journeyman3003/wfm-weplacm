package org.wwu.bpm.wfm.weplacm.processJobInquiry.sendMessages;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.Util;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.entity.JobInquiry;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.httpClient.HttpClient; 

public class RequestAdditionalInformation implements JavaDelegate{

	private final static Logger LOGGER = Logger.getLogger("LOAN-REQUESTS");

	public void execute(DelegateExecution execution) throws Exception {
	  LOGGER.info("Processing request by '" + execution.getVariable("customerId") + "'...");
	  LOGGER.info("Obtaining ProcessInstanceId of Supplicant...");
	  JobInquiry jobInquiry = (JobInquiry) execution.getVariable("jobInquiry");
	  LOGGER.info("Obtaining ProcessInstanceId of Supplicant:" + jobInquiry.getProcessId() +" ...");
	  	HttpClient.postJobInquiryResponse(Util.WBIG_BASE_URL + "/" + Util.WBIG_REQUEST_ADDITIONAL_URI, jobInquiry.getProcessId());
	  LOGGER.info("Sent HTTP requeist for additional information to "+ Util.WBIG_BASE_URL + "/" + Util.WBIG_REQUEST_ADDITIONAL_URI+ " ...");
	  
	  	
	}	
	
}