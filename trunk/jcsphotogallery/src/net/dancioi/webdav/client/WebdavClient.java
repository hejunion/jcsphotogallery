/*	
 * 	File    : WebdavClient.java
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

import net.dancioi.jcsphotogallery.admin.LoginPanel;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;



/**
 * 		WebDAV library
 *  
 *  Library client side to write (modify) the files on web server.  
 *  
 * @version 1.0 
 * @author Daniel Cioi <dan@dancioi.net>
 */

public class WebdavClient implements EntryPoint{

	private String url;
	private String username;
	private String password;
	LoginPanel lp;
	
	public WebdavClient(){
		
	}
	
	
	public WebdavClient(LoginPanel lp, String url, String username, String password){
		this.lp = lp;
		this.url = url;
		this.username = username;
		this.password = password;

	}
	
	public void checkLogin(){
		new Get(this, url, username, password);
	}

	
	public void isSuccess(boolean resultCommand){
		lp.returnRessult(resultCommand);
		writeFolder("testtest");
	}
	
	
	public void writeFolder(String folder){
		String urlMk = url+folder+"/";
		boolean folderCreated = new Mkdir(urlMk, username, password).isSuccesfull();
		if(folderCreated){
			//Window.alert("Folder created");
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
}
