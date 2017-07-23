package org.wwu.bpm.wfm.weplacm.processJobInquiry;

public class Util {
	public static final String WBIG_BASE_URL = "http://10.67.39.50:8080/hiring-process-0.0.1-SNAPSHOT";
	
	//first interface: send approval or request for new job inquiry
	public static final String WBIG_REQUEST_ADDITIONAL_URI = "RequestReceiver";
	public static final String WBIG_APPROVE_JOB_URI = "ApprovalReceiver";
	
	//second interface: send error message or cvs to deliver
	public static final String WBIG_NO_CV_AVAILABLE = "NotificationReceiver";
	public static final String WBIG_SEND_RATED_CVS = "CVReceiver";
		
}
