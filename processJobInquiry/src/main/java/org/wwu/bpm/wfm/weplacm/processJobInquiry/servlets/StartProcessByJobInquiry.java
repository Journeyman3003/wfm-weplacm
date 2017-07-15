package org.wwu.bpm.wfm.weplacm.processJobInquiry.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.entity.JobInquiry;

import com.google.gson.Gson;

/**
 * Servlet implementation class StartProcessByJobInquiry
 */
public class StartProcessByJobInquiry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartProcessByJobInquiry() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		RuntimeService runtimeService = processEngine.getRuntimeService();
		PrintWriter out = response.getWriter();
		String info = request.getParameter("info");
		ProcessInstance processInstance;
		if (null == info) {
		processInstance = runtimeService.startProcessInstanceByMessage("JobInformation");
		}
		else {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("info", info);
		processInstance = runtimeService.startProcessInstanceByMessage("JobInformation", map);
		}
		out.println("<html><body>");
		out.println("<h1>Process Instance Started</h1>");
		out.println("<p>ID: " + processInstance.getId() + "</p>");
		out.println("<p>Info: " + info + "</p>");
		out.println("</body></html>");
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//Content type validation
		request.setCharacterEncoding("UTF-8");
		String contentType = request.getContentType();
		response.setContentType("application/json");
		if (!"application/json".equals(contentType)) {
			response.getWriter().append("{\"error\":\"invalidRequest\",\"status\":\"wrong content type\"}");
		}
		BufferedReader reader = request.getReader();
		JobInquiry job;	
		
		
		try {
			job = new Gson().fromJson(reader, JobInquiry.class);
		} catch (Exception e) {
			response.getWriter().append("{\"error\":\"invalidRequest\", \"status\":\"failed to creade GSON\"}");
			return; //break
		}
		
		
		if (null == job) {
			response.getWriter().append("{\"error\":\"invalidRequest\", \"status\":\"GSON not correctly created\"}");
			return; //break
		} else {
			ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
			RuntimeService runtimeService = processEngine.getRuntimeService();
			//Map<String,Object> map = new HashMap<String,Object>();
			//map.put("jobInquiry", job);
			ProcessInstance processInstance = runtimeService.startProcessInstanceByMessage("JobInformation"/*, map*/);
			
			ObjectValue typedJobInquiry = Variables.objectValue(job).serializationDataFormat("application/json").create();

			runtimeService.setVariable(processInstance.getId(), "jobInquiry", typedJobInquiry);
			response.getWriter().append(new Gson().toJson(job, JobInquiry.class));
		}
	}

}
