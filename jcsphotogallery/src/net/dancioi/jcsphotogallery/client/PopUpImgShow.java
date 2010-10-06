/*	
 * 	File    : PopUpImgShow.java
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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;

/**
 * This class shows the selected image 
 * (from the center panel) on a PopUp panel.
 *  
 * @version 1.0 
 * @author Daniel Cioi <dan@dancioi.net>
 */

public class PopUpImgShow extends PopupPanel{

	AbsolutePanel ap;
	int wW;
	int wH;
	int popUpSize;
	AbsolutePanel imgPanel;
	int imgPanelSize;
	AbsolutePanel bottomPanel;

	String []imgName;
	String []imgFile;
	String []imgComment;
	int imgStart;				// image id from to start.
	String imgPath; 			// images path
	ImagePopUp img; 			// image which will be shown.
	Button next;				// button NEXT
	Button previous;			// button PREVIOUS
	Button close;				// button CLOSE; also the popup disappears by clicking outside of it.

	Label lImgName;				// image name
	Label lImgComment;			// image comment

	int currentImg;

	public PopUpImgShow(int imgStart, String imgPath, String []imgFile, String []imgName,  String []imgComment){
		super(true);
		this.imgStart = imgStart;
		this.imgPath = imgPath;
		this.imgName = imgName;
		this.imgFile = imgFile;
		this.imgComment = imgComment;
		currentImg = imgStart;
		initialize();
	}

	/**
	 * Initialize
	 */
	private void initialize(){
		setGlassStyleName("gwt-PopupPanelGlass");
		setGlassEnabled(true); 

		setPosition();

		ap = new AbsolutePanel();
		ap.setPixelSize(popUpSize, popUpSize);	
		ap.setStyleName("popUpPanel");

		imgPanel = new AbsolutePanel();
		imgPanelSize = popUpSize-40;
		imgPanel.setPixelSize(imgPanelSize, imgPanelSize);
		ap.add(imgPanel, 20,1);

		bottomPanel = new AbsolutePanel();
		bottomPanel.setPixelSize(popUpSize, 50);

		next = new Button("Next");
		next.setWidth("50px");		// if the size is not specified, the IE browser merges two button in one.
		next.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				nextImg();}} );
		bottomPanel.add(next, popUpSize-160, 10);

		previous = new Button("Prev");
		previous.setWidth("50px");
		previous.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				previousImg();}} );
		bottomPanel.add(previous, popUpSize-240, 10);

		close = new Button("Close");
		close.setWidth("60px");
		close.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				closeImg();}} );
		bottomPanel.add(close, popUpSize-60, 10);

		lImgName = new Label();
		bottomPanel.add(lImgName, 20,0);

		lImgComment = new Label();
		bottomPanel.add(lImgComment, 20,20);


		ap.add(bottomPanel, 1, popUpSize-40);

		setWidget(ap);

		addImage(imgPath+imgFile[imgStart]);

		checkStartImg();
	}

	/**
	 * Method to show the next image.
	 */
	public void nextImg(){
		if(currentImg<(imgFile.length-1)){
			currentImg++;
			checkButtons(currentImg);
			addImage(imgPath+imgFile[currentImg]);
		}
	}

	/**
	 * Method to show the previous image.
	 */
	public void previousImg(){
		if(currentImg>0){
			currentImg--;
			checkButtons(currentImg);
			addImage(imgPath+imgFile[currentImg]);
		}
	}

	/**
	 * Method to check if the selected image is first or the last from album;
	 * disable the buttons according.
	 * @param id image id
	 */
	private void checkButtons(int id){
		next.setEnabled(true);
		previous.setEnabled(true);
		if(id==(imgFile.length-1))next.setEnabled(false);
		if(id==0) previous.setEnabled(false);
	}

	/**
	 * check if the image is first or the last.
	 * disable the button according.
	 */
	private void checkStartImg(){
		checkButtons(currentImg);
	}

	/**
	 * Method to close the popup.
	 */
	public void closeImg(){
		this.hide();
	}

	/**
	 * Method to add image to the panel.
	 * Because the image size method from the image class can't return the values
	 * until the image is fully loaded by browser, this method add the image
	 * on the popup panel's bottom right corner (making the image invisible and
	 * creating the background loading effect). When the image is complete downloaded, 
	 * get the image's size and the it's added to the popup panel.
	 */
	private void addImage(String imagePath){
		img = new ImagePopUp(imagePath, this);
		imgPanel.add(img, imgPanelSize, imgPanelSize);
	}

	/**
	 * scale the image to fit the panel
	 */
	public void scaleImg(Image im){
		int iox = im.getWidth();
		int ioy = im.getHeight();
		float scaleX = (float)imgPanelSize/iox;
		float scaleY = (float)imgPanelSize/ioy;
		float scalef = scaleX<=scaleY ? scaleX:scaleY;
		int ix = (int)(iox*scalef);
		int iy = (int)(ioy*scalef);
		img.setPixelSize(ix, iy);

		lImgName.setText(imgName[currentImg]);
		lImgComment.setText(imgComment[currentImg]);
		clearImg();
		imgPanel.add(img, (imgPanelSize-ix)/2, (imgPanelSize-iy)/2);
	}

	/**
	 * Method to set the popup panel position.
	 */
	private void setPosition(){		
		getWindowSize();
		setPopupPosition((wW-popUpSize)/2,(wH-popUpSize)/2); 
	}

	/**
	 * Method to get the visible browser window's size.
	 */
	private void getWindowSize(){
		wW = Window.getClientWidth();
		wH = Window.getClientHeight();
		popUpSize = wW <= wH ? wW:wH;
		popUpSize = popUpSize -40;	// let some margin.
	}

	/**
	 * Method to clear the old image before add the new one.
	 */
	private void clearImg(){
		imgPanel.clear();
	}

}
