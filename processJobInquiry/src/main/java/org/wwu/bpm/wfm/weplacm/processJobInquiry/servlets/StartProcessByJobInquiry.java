package org.wwu.bpm.wfm.weplacm.processJobInquiry.servlets;

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
		String contentType = request.getContentType();
		
		if (!"application/json".equals(contentType)) {
			
		}
	}

}
