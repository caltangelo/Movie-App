package com.calebsantangelo.nerdery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.widget.TextView;

//AsyncTask<Params, Progress, Result>
public class TomatoQuery extends AsyncTask<Object, Void, Void> {
	
	String URL;
	String limit = "&limit=10";
	MainPage callerActivity;
	String mOut;

	
	
	@Override
	protected void onPreExecute(){
		super.onPreExecute();
	}
	
	
	@Override
	protected Void doInBackground(Object... params) {
		// TODO Auto-generated method stub
		callerActivity = (MainPage) params[0];
		String baseURL = callerActivity.getString(R.string.api_url);
		String key = callerActivity.getString(R.string.apikey);
		URL = baseURL + "lists/movies/box_office.json?apikey="+key+limit;
		postData(key);
		
		return null;
	}
	
	@Override
	protected void onPostExecute(Void result) {
		TextView tv = new TextView(callerActivity);
		tv.setText(mOut);
		callerActivity.setContentView(tv);
	}

	public void postData(String key) {
	    // Create a new HttpClient and Post Header
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpGet httpget = new HttpGet(URL);

	    try {
	        // Execute HTTP Get Request
	        HttpResponse response = httpclient.execute(httpget);
	        
	        mOut = inputStreamToString(response.getEntity().getContent()).toString();
	        
	    } catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	    	mOut = e.getMessage();
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	    	mOut = e.getMessage();
	    }
	}
	
	private StringBuilder inputStreamToString(InputStream is) {
	    String line = "";
	    StringBuilder total = new StringBuilder();
	    
	    // Wrap a BufferedReader around the InputStream
	    BufferedReader rd = new BufferedReader(new InputStreamReader(is));

	    // Read response until the end
	    try {
			while ((line = rd.readLine()) != null) { 
			    total.append(line); 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    // Return full string
	    return total;
	}
	
	
}
