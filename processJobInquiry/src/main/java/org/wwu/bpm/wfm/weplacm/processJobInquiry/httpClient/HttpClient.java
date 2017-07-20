package org.wwu.bpm.wfm.weplacm.processJobInquiry.httpClient;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

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
	public static void postJobInquiryResponse(String url, Boolean accepted, String processInstanceId) throws IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
		    HttpPost httppost = new HttpPost(url);
		    
		    
		    
		    StringEntity jobAccepted = new StringEntity(new Gson().toJson(accepted));
		    // It may be more appropriate to use FileEntity class in this particular
		    // instance but we are using a more generic InputStreamEntity to demonstrate
		    // the capability to stream out data from any arbitrary source
		    //
		    // FileEntity entity = new FileEntity(file, "binary/octet-stream");
		
		    httppost.setEntity(jobAccepted);
		
		    System.out.println("Executing request: " + httppost.getRequestLine());
		    CloseableHttpResponse response = httpclient.execute(httppost);
		} catch (Exception e) {
			System.out.println("you fucked up! most likely the host " + url + " could not be reached");
		} finally {
		    httpclient.close();
		}
	}
}

