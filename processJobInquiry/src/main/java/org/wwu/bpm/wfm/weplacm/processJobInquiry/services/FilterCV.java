package org.wwu.bpm.wfm.weplacm.processJobInquiry.services;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.entity.CV;

public class FilterCV implements JavaDelegate{
	
	private final static Logger LOGGER = Logger.getLogger("JOB-INQUIRY");

	public void execute(DelegateExecution execution) throws Exception {
	  LOGGER.info("Processing request by '" + execution.getVariable("customerId") + "'...");
	  //should work alright
	  @SuppressWarnings("unchecked")
	  ArrayList<CV> cvCollection = (ArrayList<CV>) execution.getVariable("CVCollection");
	  ArrayList<CV> rejectedCVs = new ArrayList<CV>();
	  cvCollection.forEach(cv -> { if (!cv.getIsAccepted()) rejectedCVs.add(cv); });
	  //rejectedCVs.addAll((ArrayList<CV>) cvCollection.stream().filter(cv -> cv.getIsAccepted() == false));
	  cvCollection.removeIf(cv -> cv.getIsAccepted() == false);
	  
	  if (cvCollection.size() > 0) execution.setVariable("cvLeft", true); else execution.setVariable("cvLeft", false);
	  cvCollection.stream().forEach(cv -> cv.setIsAccepted(false));
	  //write rejected back
	  ObjectValue rejectedCVsObject = Variables.objectValue(rejectedCVs).serializationDataFormat("application/json").create();
	  execution.setVariable("rejectedCVs", rejectedCVsObject);
	  //write accepted back
	  ObjectValue cvCollectionObject = Variables.objectValue(cvCollection).serializationDataFormat("application/json").create();
	  execution.setVariable("CVCollection", cvCollectionObject);
	  
	  
	}	
}
