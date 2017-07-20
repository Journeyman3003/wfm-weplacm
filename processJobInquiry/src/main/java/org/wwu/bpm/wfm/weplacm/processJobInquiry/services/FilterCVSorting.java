package org.wwu.bpm.wfm.weplacm.processJobInquiry.services;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.entity.CV;

public class FilterCVSorting implements JavaDelegate{
	
	private final static Logger LOGGER = Logger.getLogger("JOB-INQUIRY");

	public void execute(DelegateExecution execution) throws Exception {
	  LOGGER.info("Processing request by '" + execution.getVariable("customerId") + "'...");
	  
	  @SuppressWarnings("unchecked")
	  ArrayList<CV> cvCollection = (ArrayList<CV>) execution.getVariable("CVCollection");
	  
	  cvCollection.removeIf(cv -> cv.getIsAccepted() == false);
	  
	  if (cvCollection.size() > 0) execution.setVariable("cvLeft", true); else execution.setVariable("cvLeft", false);
	  cvCollection.stream().forEach(cv -> cv.setIsAccepted(false));
	  ObjectValue cvCollectionObject = Variables.objectValue(cvCollection).serializationDataFormat("application/json").create();
	  execution.setVariable("CVCollection", cvCollectionObject);
	  
	  
	}	
}
