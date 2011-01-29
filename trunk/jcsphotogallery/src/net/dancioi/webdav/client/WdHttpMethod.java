/*	
 * 	File    : WdHttpMethod.java
 * 
 * 	Copyright (C) 2011 Daniel Cioi <dan@dancioi.net>
 *                              
 *	www.dancioi.net/projects/Jcsphotogallery
 *
 *	This file is part of Jcsphotogallery.
 *
 *  Jcsphotogallery is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Jcsphotogallery is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Jcsphotogallery.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package net.dancioi.webdav.client;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;


/**
 * 		Abstract class. 
 * Connect and execute a command on WebDAV server.
 *  
 * @version 1.0 
 * @author Daniel Cioi <dan@dancioi.net>
 */

public abstract class WdHttpMethod {

	private String[] resultArray;
	
	public WdHttpMethod(String command, String url, String username, String password){
		resultArray = new String[4];
		logOn(command, url, username, password);
	}
	
	
	public abstract void getResults(String[] results);

	
	public void logOn(String command, String url, String username, String password){
		RequestBuilderWebdav builder = new RequestBuilderWebdav(command, URL.encode(url));
		
		builder.setUser(username);
		builder.setPassword(password);
		
		tryRequest(builder);
	}
	
	private void tryRequest(RequestBuilderWebdav builder){
		
		try {
			builder.sendRequest(null, new RequestCallback() {
		    public void onError(Request request, Throwable exception) {
		    	resultArray[0] = "error connection";
		    }

		    public void onResponseReceived(Request request, Response response) {
		    	resultArray[1] = Integer.toString(response.getStatusCode());
		    	resultArray[2] = response.getText();
		    	resultArray[3] = response.getStatusText();
		    }
		  });
		} catch (RequestException e) {
		  // do something with this.
		}
		getResults(resultArray);
	}

}
