package org.wwu.bpm.wfm.weplacm.processJobInquiry.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ProcessInstance;

/**
 * Servlet implementation class ReceivePayment
 */
public class ReceivePayment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReceivePayment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //Problem: Prozessinstanz-ID muss gefunden werden...
    //Query -> processDefinition -> runningInstances -> vorerst get(0) oder so...
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		RuntimeService runtimeService = processEngine.getRuntimeService();
	/*
		
		RepositoryService repositoryService = processEngine.getRepositoryService();

		ProcessDefinition myProcessDefinition =
		        repositoryService.createProcessDefinitionQuery()
		            .processDefinitionKey("weplacmProcessJobInquiry")
		            .latestVersion()
		            .singleResult();
		
		
		String id = myProcessDefinition.getId();
	    // query for latest process definition with given name	
		response.getWriter().append(id);
		runtimeService.createMessageCorrelation("Payment")
				.processInstanceId(getIdByProcessName(processEngine)).correlate();
		response.getWriter().append("Served at:3 ").append(request.getContextPath()); */
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1>it workz</h1>");
		out.println("<p>Info: Batman</p>");
		out.println("</body></html>");
	}
	protected String getIdByProcessName(ProcessEngine processEngine) {
		RepositoryService repositoryService = processEngine.getRepositoryService();

		ProcessDefinition myProcessDefinition =
	        repositoryService.createProcessDefinitionQuery()
	            .processDefinitionKey("weplacmProcessJobInquiry")
	            .latestVersion()
	            .singleResult();
	    return myProcessDefinition.getId();
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
