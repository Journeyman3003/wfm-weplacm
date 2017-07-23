package org.wwu.bpm.wfm.weplacm.processJobInquiry.httpClient;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.entity.CV;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.entity.CVCollectionEnvelope;
import org.wwu.bpm.wfm.weplacm.processJobInquiry.entity.JobInquiryApproval;

import com.google.gson.Gson;

public class HttpClient {
//	String       postUrl       = "www.site.com";// put in your url
//	Gson         gson          = new Gson();
//	CloseableHttpClient   httpClient    = HttpClientBuilder.create().build();
//	HttpPost     post          = new HttpPost(postUrl);
//	StringEntity postingString = new StringEntity(gson.toJson(pojo1));//gson.tojson() converts your pojo to json
//	post.setEntity(postingString);
//	post.setHeader("Content-type", "application/json");
//	HttpResponse  response = httpClient.execute(post);
//}
	public static void postJobInquiryRejection(String url, String processInstanceId) throws IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			
			//String urlAPI;
			
		    HttpPost httppost = new HttpPost(url);
		    
		    //JobInquiryResponse jobResponse = new JobInquiryResponse(processInstanceId);
		    
		    StringEntity jobInquiryResponse = new StringEntity(new Gson().toJson(processInstanceId));
		
		    httppost.setEntity(jobInquiryResponse);
		
		    System.out.println("Executing request: " + httppost.getRequestLine());
		    CloseableHttpResponse response = httpclient.execute(httppost);
		} catch (Exception e) {
			System.out.println("you fucked up! most likely the host " + url + " could not be reached");
		} finally {
		    httpclient.close();
		}
	}
	
	public static void postJobInquiryApproval(String url, JobInquiryApproval jobApproval) throws IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {			
		    HttpPost httppost = new HttpPost(url);
		   		    
		    StringEntity jobInquiryResponse = new StringEntity(new Gson().toJson(jobApproval));
		
		    httppost.setEntity(jobInquiryResponse);
		    
		    System.out.println("Executing request: " + httppost.getRequestLine());
		    CloseableHttpResponse response = httpclient.execute(httppost);
		} catch (Exception e) {
			System.out.println("you fucked up! most likely the host " + url + " could not be reached");
		} finally {
		    httpclient.close();
		}
	}
	
	public static void postNoCVsAvailable(String url, String processInstanceId) throws IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try{
			HttpPost httppost = new HttpPost(url);
			
			//NoCVAvailableResponse noCVResponse = new NoCVAvailableResponse(processInstanceId);
			//only submit a plain json string....
			StringEntity response = new StringEntity(new Gson().toJson(processInstanceId));
			
			httppost.setEntity(response);
			
			System.out.println("Executing request: " + httppost.getRequestLine());
		    CloseableHttpResponse resp = httpclient.execute(httppost);
		} catch (Exception e) {
			System.out.println("you fucked up! most likely the host " + url + " could not be reached");
		} finally {
		    httpclient.close();
		}
	}
	
	public static void postRatedCVs(String url,ArrayList<CV> ratedCVs, String processInstanceId) throws IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
						
		    HttpPost httppost = new HttpPost(url);
		    		    
		    StringEntity cvs = new StringEntity(new Gson().toJson(new CVCollectionEnvelope(ratedCVs, processInstanceId)));
		
		    httppost.setEntity(cvs);
		
		    System.out.println("Executing request: " + httppost.getRequestLine());
		    CloseableHttpResponse response = httpclient.execute(httppost);
		} catch (Exception e) {
			System.out.println("you fucked up! most likely the host " + url + " could not be reached");
		} finally {
		    httpclient.close();
		}
	}
	
}

