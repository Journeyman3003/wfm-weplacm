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
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.entity.JobInquiryProlongationResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class ReceivePayment
 */
public class ReceivePaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReceivePaymentServlet() {
        super();
    }

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
		out.println("<h1>what are you doing here/ trying to GET ?</h1>");
		out.println("<p></p>");
		out.println("</body></html>");
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String contentType = request.getContentType();
		response.setContentType("application/json");
		if (!"application/json".equals(contentType)) {
			response.getWriter().append("{\"error\":\"invalidRequest\",\"status\":\"wrong content type\"}");
		}
		BufferedReader reader = request.getReader();
		String processInstanceId;
		try {
			processInstanceId = new Gson().fromJson(reader, String.class);
		} catch (Exception e) {
			response.getWriter().append("{\"error\":\"invalidRequest\", \"status\":\"failed to creade GSON\"}");
			return; //break
		}
		
		
		if (null == processInstanceId) {
			response.getWriter().append("{\"error\":\"invalidRequest\", \"status\":\"GSON not correctly created\"}");
			return; //break
		} else {
			ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
			RuntimeService runtimeService = processEngine.getRuntimeService();
			
			try {
				runtimeService.createMessageCorrelation("Payment")
				.processInstanceId(processInstanceId).correlate();
				response.getWriter().append("{\"error\":\"none\", \"status\":\"everything went as expected!\"}");
			} catch (MismatchingMessageCorrelationException e) {
				response.getWriter().append("{\"error\":\"MismatchingMessageCorrelationException\", \"status\":\"instance " +processInstanceId + " not found or not waiting for continuation!\"}");
			} catch (Exception e) {
				response.getWriter().append("{\"error\":\"unknown\", \"status\":\"instance " +processInstanceId + " not correlated\"}");				
			}
		}
	}
}
