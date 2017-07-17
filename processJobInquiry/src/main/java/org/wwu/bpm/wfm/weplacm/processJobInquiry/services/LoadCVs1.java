package org.wwu.bpm.wfm.weplacm.processJobInquiry.services;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.entity.CV;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.entity.CVCollection;

public class LoadCVs1 implements JavaDelegate{

	private final static Logger LOGGER = Logger.getLogger("JOB-INQUIRY");

	public void execute(DelegateExecution execution) throws Exception {
		LOGGER.info("Processing request by '" + execution.getVariable("customerId") + "'...");
	 	
		
		CV cv1 = new CV();
		CV cv2 = new CV();
		cv2.setApplicantName("Hans");
		
		ObjectValue typedJobInquiry1 = Variables.objectValue(cv1).serializationDataFormat("application/json").create();
		ObjectValue typedJobInquiry2 = Variables.objectValue(cv2).serializationDataFormat("application/json").create();
		
		CVCollection cvcol = new CVCollection();
		ArrayList<CV> cvsarray = new ArrayList<CV>();
		cvsarray.add(cv1);
		cvsarray.add(cv2);
		cvcol.setCvs(cvsarray);
		
		ObjectValue typedJobInquiry3 = Variables.objectValue(cvsarray).serializationDataFormat("application/json").create();
		
//		execution.setVariable("CVCollection", typedJobInquiry3);
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		RuntimeService runtimeService = processEngine.getRuntimeService();
		runtimeService.setVariable(execution.getId(), "CVCollection", typedJobInquiry3);
	}	
	
}

