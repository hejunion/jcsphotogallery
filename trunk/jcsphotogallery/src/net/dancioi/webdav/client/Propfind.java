/*	
 * 	File    : Propfind.java
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

import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Window;

/**
 * 		Get properties for a specific path (files and folders).
 *  
 * @version 1.0 
 * @author Daniel Cioi <dan@dancioi.net>
 */

public class Propfind extends WdHttpMethod{

	private boolean succesfull;
	private CommandException commandException;

	/**
	 * Constructor. 
	 * @param url
	 * @param username
	 * @param password
	 */
	public Propfind(String url, String username, String password){
		super("PROPFIND", url, username, password);
	}


	/**
	 * Method retrieves properties defined on the files and folders
	 * at a specific path.
	 * 
	 * @return boolean
	 * @throws CommandException
	 */
	public boolean isSuccesfull() throws CommandException{
		if(succesfull)
			return succesfull;
		else throw commandException;
	}
	


	private void parseResult(String results){
		Window.alert("Result \n"+results);
	}
	
	
	
	
	/**
	 * Override the method to set the "Depth" parameter.
	 */
	protected void logOn(String command, String url, String username, String password){
		RequestBuilderWebdav builder = new RequestBuilderWebdav(command, URL.encode(url));
		builder.setHeader("Depth", "0");
		builder.setUser(username);
		builder.setPassword(password);
		tryRequest(builder);
	}
	
	
	
	
	
	
	/**
	 * Method to get the answer from WebDAV server.
	 */
	@Override
	public void getResults() {
		if(connected){
			if(207 == statusCode){
				succesfull = true;
				parseResult(responseText);
			}
			else{
				commandException = new CommandException(statusCode,responseStatus);
			}
		}
		else{
			commandException = new CommandException("connection error");
		}
	}
	
//  207 Multi-Status	
//	401 Authorization required
//  403 Forbidden
}
