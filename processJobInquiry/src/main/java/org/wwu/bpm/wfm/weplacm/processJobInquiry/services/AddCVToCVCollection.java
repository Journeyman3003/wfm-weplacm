package org.wwu.bpm.wfm.weplacm.processJobInquiry.services;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.entity.CV;

public class AddCVToCVCollection implements JavaDelegate{

	private final static Logger LOGGER = Logger.getLogger("LOAN-REQUESTS");

	public void execute(DelegateExecution execution) throws Exception {
		LOGGER.info("Processing request by '" + execution.getVariable("customerId") + "'...");
		LOGGER.info("attempting to retrieve temp CV...");
		CV tempCV = (CV) execution.getVariable("tempCV");
		if(!(tempCV == null)) {
			LOGGER.info("CV loaded, adding CV to collection...");
			@SuppressWarnings("unchecked")
			ArrayList<CV> cvCollection = (ArrayList<CV>) execution.getVariable("CVCollection");
			if (cvCollection == null) cvCollection = new ArrayList<CV>();
			cvCollection.add(tempCV);
			ObjectValue cvCollectionObject = Variables.objectValue(cvCollection).serializationDataFormat("application/json").create();
			execution.setVariable("CVCollection", cvCollectionObject);
			LOGGER.info("Successfully added the CV to collection");
		} else {
			//should not happen...
			LOGGER.info("Adding Operation Aborted...tempCV was null");
		}
	  
	}
}
