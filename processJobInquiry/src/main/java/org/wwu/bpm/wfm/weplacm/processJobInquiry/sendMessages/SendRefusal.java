package org.wwu.bpm.wfm.weplacm.processJobInquiry.sendMessages;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.entity.CV;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.entity.JobInquiry;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.mail.Mailer;

public class SendRefusal implements JavaDelegate{

	private final static Logger LOGGER = Logger.getLogger("JOB-INQUIRY");

	public void execute(DelegateExecution execution) throws Exception {
		LOGGER.info("Processing request by '" + execution.getVariable("customerId") + "'...");
		@SuppressWarnings("unchecked")
		ArrayList<CV> rejectedCVs = (ArrayList<CV>) execution.getVariable("rejectedCVs");
		
		JobInquiry jobInquiry = (JobInquiry) execution.getVariable("jobInquiry");
		
		rejectedCVs.forEach(cv -> {
							if (!(cv.getEmail() == null) && (!(cv.getName() == null))) {
			 				Mailer.send("recruiting.weplacm@gmail.com", "DieAntwortIst42", cv.getEmail(), "Your application as "+ jobInquiry.getTitle(), cv.getName(), jobInquiry.getTitle(),1,"");;
							}});
	}	

}
