/*	
 * 	File    : Mkdir.java
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

import com.google.gwt.user.client.Window;



/**
 * 		Create a folder.
 *  
 * @version 1.0 
 * @author Daniel Cioi <dan@dancioi.net>
 */

public class Mkdir extends WdHttpMethod{

	private boolean succesfull;

	/**
	 * Constructor. 
	 * @param url
	 * @param username
	 * @param password
	 */
	public Mkdir(String url, String username, String password){
		super("MKCOL", url, username, password);
	}


	/**
	 * Method that return if the folder was created.
	 * @return
	 * @throws CommandException
	 */
	public boolean isSuccesfull() throws CommandException{
		if(succesfull)
			return succesfull;
		else throw new CommandException(statusCode,responseStatus);
	}


	/**
	 * Method to get the answer from WebDAV server.
	 */	
	@Override
	public void getResults() {
		if(connected){	
			//Window.alert("get here "+statusCode+"\n"+"response status = "+responseStatus+"\n responseText = "+responseText+"\n successfull="+succesfull);
			if(201 == statusCode){
				succesfull = true;
			}
			else{
			//	throw new CommandException(statusCode,responseStatus);
			}
		}
		else{
		//	throw new CommandException(0,"connection error");
		}
	}
	
	
	

	/* http://www.ietf.org/rfc/rfc2518.txt
	 * Status Codes

   201 (Created) - The collection or structured resource was created in
   its entirety.

   403 (Forbidden) - This indicates at least one of two conditions: 1)
   the server does not allow the creation of collections at the given
   location in its namespace, or 2) the parent collection of the
   Request-URI exists but cannot accept members.

   405 (Method Not Allowed) - MKCOL can only be executed on a
   deleted/non-existent resource.

   409 (Conflict) - A collection cannot be made at the Request-URI until
   one or more intermediate collections have been created.

   415 (Unsupported Media Type)- The server does not support the request
   type of the body.

   507 (Insufficient Storage) - The resource does not have sufficient
   space to record the state of the resource after the execution of this
   method.

	 */

}
