/*	
 * 	File    : Get.java
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
 * 		Get the files & folders on a specific path.		
 *  
 * @version 1.0 
 * @author Daniel Cioi <dan@dancioi.net>
 */

public class Get extends WdHttpMethod{

	
	private WebdavClient wdClient;
	private boolean succesfull;
	
	
	public Get(WebdavClient wdClient, String url, String username, String password){
		super("GET", url, username, password);
		this.wdClient = wdClient;
		triggerEvent(succesfull);
	}
	

	
	private void triggerEvent(boolean evResult){
		wdClient.isSuccess (evResult);
	}
	
	
	

	@Override
	public void getResults(String[] results) {
		if(results[0]==null){
			succesfull = true;
			Window.alert("Result 1 ="+results[1]);
		}
	}
	

	

}
