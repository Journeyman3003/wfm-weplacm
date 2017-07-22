package org.wwu.bpm.wfm.weplacm.processJobInquiry.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.entity.JobInquiry;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.entity.JobInquiryProlongationResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ProlongationRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProlongationRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //Problem: Prozessinstanz-ID muss gefunden werden...
    //Query -> processDefinition -> runningInstances -> vorerst get(0) oder so...
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1>please rly don't GET me :-(</h1>");
		//out.println("<p>please dude</p>");
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String contentType = request.getContentType();
		response.setContentType("application/json");
		if (!"application/json".equals(contentType)) {
			response.getWriter().append("{\"error\":\"invalidRequest\",\"status\":\"wrong content type\"}");
		}
		BufferedReader reader = request.getReader();
		
		JobInquiryProlongationResponse newjob;
		Gson gson = new GsonBuilder()
			    .setDateFormat("yyyy-MM-dd")
			    .create();
		try {
			newjob = gson.fromJson(reader, JobInquiryProlongationResponse.class);
		} catch (Exception e) {
			response.getWriter().append("{\"error\":\"invalidRequest\", \"status\":\"failed to creade GSON\"}");
			return; //break
		}
		
		
		if (null == newjob) {
			response.getWriter().append("{\"error\":\"invalidRequest\", \"status\":\"GSON not correctly created\"}");
			return; //break
		} else {
			ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
			RuntimeService runtimeService = processEngine.getRuntimeService();
			try {
				
				JobInquiry jobInquiry = (JobInquiry) runtimeService.getVariable(newjob.getProcessInstanceId(), "jobInquiry");
				jobInquiry.setDeadline(newjob.getDeadline());
				ObjectValue typedJobInquiry = Variables.objectValue(jobInquiry).serializationDataFormat("application/json").create();
				runtimeService.setVariable(newjob.getProcessInstanceId(), "jobInquiry", typedJobInquiry);
				
				runtimeService.createMessageCorrelation("NewJobInformation")
				.processInstanceId(newjob.getProcessInstanceId()).correlate();
				response.getWriter().append("{\"error\":\"none\", \"status\":\"everything went as expected!\"}");
			} catch (ProcessEngineException e) {
				response.getWriter().append("{\"error\":\"ProcessEngineException\", \"status\":\"instance " + newjob.getProcessInstanceId() + " not found or not waiting for continuation!\"}");
			} catch (Exception e) {
				response.getWriter().append("{\"error\":\"unknown\", \"status\":\"instance " + newjob.getProcessInstanceId() + " not correlated\"}");				
			}			
		}
	}

}

