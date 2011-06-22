/*	
 * 	File    : Jcsphotogallery.java
 * 
 * 	Copyright (C) 2010 Daniel Cioi <dan@dancioi.net>
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

package net.dancioi.jcsphotogallery.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;


/**
 * 		JcsPhotoGallery
 * 
 * The Main class of this project :p
 * 
 * For details about this project see the following web pages:
 * http://www.dancioi.net/projects/jcsphotogallery/
 * http://code.google.com/p/jcsphotogallery/
 * 
 * For a demo of this project see the following web page:
 * http://www.dancioi.net/projects/jcsphotogallery/demo/
 * 
 *  
 * @version 1.0 
 * @author Daniel Cioi <dan@dancioi.net>
 */

public class Jcsphotogallery implements EntryPoint {

	
	public String galleryVersion = "1.0.5";

	Label header;

	String galleryName;
	String nameHomePage;

	protected Albums albums;

	protected CenterPanel center;

	TopPanel topPanel;
	String homeLink = "";
	protected BottomPanel bottomPanel;

	protected ReadXML readXml;
	boolean albumsFlag = true;

	SortAlbums sA;

	public void onModuleLoad() {
		initialize();

	}

	/**
	 * Initialize
	 */
	public void initialize(){
		addHeader();
		addCenterPanel();
		addBottomPanel();
		addTopPanel();
		sA = new SortAlbums(this);
		getAlbums();
	}

	/**
	 * Method to add the header where a text 
	 * with the owner gallery will be shown. 
	 */
	private void addHeader(){
		header = new Label();
		header.setStyleName("h1");
		RootPanel.get("header").add(header);
	}

	/**
	 * Method to add the center panel.
	 */
	protected void addCenterPanel(){
		center = new CenterPanel(this);
		RootPanel.get("images").add(center); 
	}

	/**
	 * Method to add the top panel.
	 */
	private void addTopPanel(){
		topPanel = new TopPanel(this,homeLink);
		RootPanel.get("topPanel").add(topPanel);
	}


	/**
	 * Method to add the bottom panel.
	 */
	protected void addBottomPanel(){
		bottomPanel = new BottomPanel(this);
		RootPanel.get("bottomPanel").add(bottomPanel);
	}


	/**
	 * Method to get the albums parameters.
	 */
	public void getAlbums(){
		readXml = new ReadXML(this);
	}

	/**
	 * Method to get the selected album parameters.
	 * @param nr
	 */
	public void getAlbumNr(int nr){
		String albumPath = "gallery/"+albums.getAlbumFolderName(nr)+"/album.xml";
		String imagesPath = "gallery/"+albums.getAlbumFolderName(nr)+"/";
		readXml.getXML(albumPath, imagesPath);
	}

	/**
	 * Method to return to the albums list when the albums button is clicked.
	 */
	public void backToAlbums(){
		bottomPanel.allOff();
		albumsFlag = true;
		center.prepareImg("gallery/", albums.getNrAlbums(), albums.getAlbumsVisible(), albums.getAlbumsNameVisible());

	}

	/**
	 * Method to set the owner gallery name 
	 * and the owner's link to the home web page.  
	 * @param name owner's name
	 * @param homePage link to the home web page 
	 */
	public void setGalleryName(String name, String homePage){
		galleryName = name;
		nameHomePage = homePage;
		setHeader(name);
		topPanel.setHomePage(name, homePage);
	}

	/**
	 * Method to set the header owner's gallery.
	 * @param name owners' name
	 */
	public void setHeader(String name){
		header.setText(name+"'s gallery");
	}

	/**
	 * Method to show the the albums sorted by one of the categories. 
	 * @param selected selected id from sorting ListBox on the top panel.
	 */
	public void showSelectedAlbums(int selected){
		if(sA.setVisibleAlbums(selected))
			backToAlbums();
	}

	
	/**
	 * Method to initialize the Albums.
	 */
	public void initializeAlbums(){
		albums = new Albums();
	}
	
}
