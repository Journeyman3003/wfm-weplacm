package org.wwu.bpm.wfm.weplacm.processJobInquiry.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.camunda.bpm.engine.MismatchingMessageCorrelationException;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.entity.CV;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.entity.CVEnvelope;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class IncomingCVServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //Problem: Prozessinstanz-ID muss gefunden werden...
    //Query -> processDefinition -> runningInstances -> vorerst get(0) oder so...
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1>it workz</h1>");
		out.println("<p>Please POST me a valid ProcessInstanceID of your Process and a CV :-)</p>");
		out.println("</body></html>");
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String contentType = request.getContentType();
		if (!"application/json".equals(contentType)) {
			response.getWriter().append("{\"error\":\"invalidRequest\",\"status\":\"wrong content type\"}");
		}
		BufferedReader reader = request.getReader();
		CVEnvelope cvEnv;
		Gson gson = new GsonBuilder()
			    .setDateFormat("yyyy-MM-dd")
			    .create();
		try {
			cvEnv = gson.fromJson(reader, CVEnvelope.class);
		} catch (Exception e) {
			response.getWriter().append("{\"error\":\"invalidRequest\", \"status\":\"failed to creade GSON\"}");
			return; //break
		}
		
		
		if (null == cvEnv || "".equals(cvEnv.getProcessInstanceId())) {
			response.getWriter().append("{\"error\":\"invalidRequest\", \"status\":\"GSON not correctly created\"}");
			return; //break
		} else {
			ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
			RuntimeService runtimeService = processEngine.getRuntimeService();
			//Map<String,Object> map = new HashMap<String,Object>();
			//map.put("jobInquiry", job);
			try {
				//temporary CV camunda variable for next step
				ObjectValue typedCV = Variables.objectValue(cvEnv.getCv()).serializationDataFormat("application/json").create();
				runtimeService.setVariable(cvEnv.getProcessInstanceId(), "tempCV", typedCV);
				runtimeService.createMessageCorrelation("ApplicationInformation")
				.processInstanceId(cvEnv.getProcessInstanceId()).correlate();
			} catch (ProcessEngineException e) {
				response.getWriter().append("{\"error\":\"ProcessEngineException\", \"status\":\"instance " +cvEnv.getProcessInstanceId() + " not found\"}");
			} catch (Exception e) {
				response.getWriter().append("{\"error\":\"unknown\", \"status\":\"instance " +cvEnv.getProcessInstanceId() + " not correlated\"}");				
			}
			
		}
		

	}


}
