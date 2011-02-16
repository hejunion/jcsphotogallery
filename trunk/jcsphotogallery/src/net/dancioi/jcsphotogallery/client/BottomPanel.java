/*	
 * 	File    : BottomPanel.java
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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;


/**
 * This class create the Bottom Panel. 
 * 
 * The application contains 3 panels (top, center, bottom).
 *  
 * @version 1.1 
 * @author Daniel Cioi <dan@dancioi.net>
 */
public class BottomPanel extends AbsolutePanel{

	String jcsPhotoGalleryLinkString;
	Jcsphotogallery pg;
	Label pageNr;		// page number label.
	Label albumLabel;	// album label.

	Image bLeft;
	Image bRight;
	Image bUp;
	
	String adminLink = "<div> <a href=\"JcsphotogalleryAdmin.html\"><font size=\"1\">Admin</font></a> </div>";
	
	public BottomPanel(Jcsphotogallery pg){
		this.pg = pg;
		addVersionNr();
		initialize();
	}

	/**
	 * Show the project name and version number.
	 */
	public void addVersionNr(){
		jcsPhotoGalleryLinkString = 
			"<div> <a href=\"http://www.dancioi.net/projects/jcsphotogallery/\"><font size=\"1\">jcsPhotoGallery "
			+pg.galleryVersion+"</font></a> </div>";
	}

	/**
	 * Initialize.
	 */
	public void initialize(){
		setSize("800px", "70px");
		bLeft = new Image("ext/previous.gif");		// PREVIOUS button
		bLeft.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				bLeftClicked();}} );
		add(bLeft, 610, 5);
		
		bRight = new Image("ext/next.gif");	// NEXT button
		bRight.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				bRightClicked();}} );
		add(bRight, 660, 5);
		
		bUp = new Image("ext/albums.gif");		// BACK to albums
		bUp.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				bUpClicked();}} );
		add(bUp, 767, 5);

		pageNr = new Label();
		add(pageNr, 370, 10);

		albumLabel = new Label("");
		albumLabel.setStyleName("bottomAlbumLabel");
		add(albumLabel, 1,10);

		HTML jcsPhotoGalleryLink = new HTML(jcsPhotoGalleryLinkString);
		add(jcsPhotoGalleryLink, 350, 40);

		//HTML adminHtmlLink = new HTML(adminLink);
		//add(adminHtmlLink, 375, 60);
		
		allOff();
	}
	
	
	/**
	 * Method to handle PREVIOUS button.
	 */
	public void bLeftClicked(){
		pg.center.previousPage();
	}

	/**
	 * Method to handle NEXT button.
	 */
	public void bRightClicked(){
		pg.center.nextPage();
	}

	/**
	 * Method to handle BACK to Albums button.
	 */
	public void bUpClicked(){
		pg.backToAlbums();
	}

	/**
	 * Method to set enable/disable the PREVIOUS button.
	 * @param v boolean
	 */
	public void setBleftVisible(boolean v){
		bLeft.setVisible(v);
	}

	/**
	 * Method to set enable/disable the NEXT button.
	 * @param v boolean
	 */
	public void setBrightVisible(boolean v){
		bRight.setVisible(v);
	}

	/**
	 * Method to set enable/disable the BACK to Albums button.
	 * @param v boolean
	 */
	public void setBupVisible(boolean v){
		bUp.setVisible(v);
	}

	/**
	 * Method to set the page number.
	 * @param pn page number string
	 */
	public void setPageNr(String pn){
		pageNr.setText(pn);
	}

	/**
	 * Method to set the album name 
	 * (shown on the bottom left corner)
	 * @param album album name
	 */
	public void setAlbumLabel(String album){
		albumLabel.setText(album);
	}

	/**
	 * Method to disable all buttons on the bottom panel.
	 */
	public void allOff(){
		setBleftVisible(false);
		setBrightVisible(false);
		setBupVisible(false);
		setPageNr("");
		setAlbumLabel("");
	}

}
