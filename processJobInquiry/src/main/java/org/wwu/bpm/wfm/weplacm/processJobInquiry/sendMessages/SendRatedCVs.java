package org.wwu.bpm.wfm.weplacm.processJobInquiry.sendMessages;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.Util;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.entity.CV;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.httpClient.HttpClient;

public class SendRatedCVs implements JavaDelegate{

	private final static Logger LOGGER = Logger.getLogger("JOB-INQUIRY");

	public void execute(DelegateExecution execution) throws Exception {
		LOGGER.info("Processing request by '" + execution.getVariable("customerId") + "'...");
		LOGGER.info("Loading CVs...");
		@SuppressWarnings("unchecked")
		ArrayList<CV> cvCollection = (ArrayList<CV>) execution.getVariable("CVCollection");
		
		HttpClient.postRatedCVs(Util.WBIG_BASE_URL + "/" + Util.WBIG_SEND_RATED_CVS, cvCollection);
		LOGGER.info("Sent HTTP post with rated cvs to " +Util.WBIG_BASE_URL + "/" + Util.WBIG_SEND_RATED_CVS+ " ...");		

	 	  
	}	
	
}