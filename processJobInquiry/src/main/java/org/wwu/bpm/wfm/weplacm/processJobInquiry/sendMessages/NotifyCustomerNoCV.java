package org.wwu.bpm.wfm.weplacm.processJobInquiry.sendMessages;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.Util;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.entity.JobInquiry;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.httpClient.HttpClient;

public class NotifyCustomerNoCV implements JavaDelegate{

	private final static Logger LOGGER = Logger.getLogger("LOAN-REQUESTS");

	public void execute(DelegateExecution execution) throws Exception {
		LOGGER.info("Processing request by '" + execution.getVariable("customerId") + "'...");
		LOGGER.info("Obtaining ProcessInstanceId of Supplicant...");
		JobInquiry jobInquiry = (JobInquiry) execution.getVariable("jobInquiry");
		LOGGER.info("Obtaining ProcessInstanceId of Supplicant:" + jobInquiry.getProcessId() +" ...");
		HttpClient.postNoCVsAvailable(Util.WBIG_BASE_URL+ "/" + Util.WBIG_NO_CV_AVAILABLE, jobInquiry.getProcessId());
		LOGGER.info("Sent HTTP message for no CVs available to "+ Util.WBIG_BASE_URL+ "/" + Util.WBIG_NO_CV_AVAILABLE+ " ...");
	}	
}

