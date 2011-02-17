/*	
 * 	File    : CenterPanel.java
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
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;

/**
 * The 3x3 grid where the image thumbnails are shown.
 * CENTER panel. 
 * 
 * The application contains 3 panels (top, center, bottom).
 *  
 * @version 1.0 
 * @author Daniel Cioi <dan@dancioi.net>
 */

public class CenterPanel extends Grid{

	Jcsphotogallery pg;
	protected int imgId = 0;
	protected int cellID = 0;
	protected Image []cell = new Image[9];	// 3x3 matrix.
	protected boolean []cellOn = new boolean[9];	// flag to know which cells have images attached
	protected int imgCountLimit = 9;
	protected int imgCount;					// how many albums there are to know the size of  the array.
	protected String []img;					// array with albums strings.
	protected String []imgName;				// album name.
	protected String []imgP;					// image full size, show on popup.
	protected String []imgComment;			// image comment.
	protected String imgPath;
	int page = 1;					// current page.
	int pages;						// numbers of pages.

	public CenterPanel(Jcsphotogallery pg){
		super(3, 3);
		this.pg = pg;
		initialize();
	}

	/**
	 * Initialize.
	 */
	private void initialize(){
		setSize("800px","630px");
		setBorderWidth(0);
		setCellSpacing(5);
		setTheCells();
	}

	/**
	 * Table 3x3 where the pictures are shown.
	 * Format the cells.
	 */
	private void setTheCells(){
		for(int h=0;h<3;h++){
			for(int w=0;w<3;w++){

				getCellFormatter().setHorizontalAlignment(h, w, HasHorizontalAlignment.ALIGN_CENTER);
				getCellFormatter().setStyleName(h, w, "gridCell-format");
				getCellFormatter().setHeight(h, w, "210px");
				getCellFormatter().setWidth(h, w, "240px");
			}
		}
	}


	/**
	 * Method to prepare the album images (thumbnails and whole pictures).  
	 * 
	 * @param imagesPath the images path
	 * @param imgagesCount how many images are in the album
	 * @param image image thumbnails
	 * @param imageName name for each image
	 * @param imageP image full size
	 * @param imageComment image comment
	 */
	public void prepareImg(String imagesPath, int imgagesCount, String[] image, String[] imageName, String[] imageP, String[] imageComment){
		pg.bottomPanel.setBupVisible(true);
		prepareImg(imagesPath, imgagesCount, image, imageName);
		imgP = imageP;
		imgComment = imageComment;
	}

	/**
	 * Method to show the thumbnails on the center panel (table 3x3).
	 * 
	 * @param imagesPath the albums thumbnails path.
	 * @param imagesCount how many images are in the album
	 * @param image image thumbnails
	 * @param imageName image name
	 */
	public void prepareImg(String imagesPath, int imagesCount, String[] image, String[] imageName){
		imgCountLimit = 9;
		if(imagesCount>9){
			pg.bottomPanel.setBrightVisible(true);
			pages = Math.round(imagesCount/9)+((float)imagesCount/9==(float)Math.round(imagesCount/9) ? 0:1);
			showPageNr(1,pages);
		}
		else{
			pg.bottomPanel.setBrightVisible(false);
			pg.bottomPanel.setBleftVisible(false);
			showPageNr(0,0);
		}
		imgCount = imagesCount;
		if(imgCount < imgCountLimit)imgCountLimit = imgCount;
		img = image;
		imgName = imageName;
		page = 1;
		imgId = 0;
		imgPath = imagesPath;
		showImg(imgPath);
	}

	/**
	 * Method to show the next page.
	 */
	public void nextPage(){	
		imgId=page*9;
		imgCountLimit = imgCount-page*9;
		page++;
		showImg(imgPath);
		pg.bottomPanel.setBleftVisible(true);
		if(pages==page)pg.bottomPanel.setBrightVisible(false);
		showPageNr(page,pages);
	}

	/**
	 * Method to show the previous page.
	 */
	public void previousPage(){
		page--;
		imgId=page*9-9;
		imgCountLimit = 9;
		showImg(imgPath);
		pg.bottomPanel.setBrightVisible(true);
		if(page==1)pg.bottomPanel.setBleftVisible(false);
		showPageNr(page,pages);
	}

	/**
	 * Method to show the the actual/total pages 
	 * @param page actual page
	 * @param pages number of pages
	 */
	private void showPageNr(int page, int pages){
		if(pages==0)pg.bottomPanel.setPageNr("");
		else pg.bottomPanel.setPageNr("page "+page+"/"+pages);
	}

	/**
	 * add the image to the table.
	 */
	protected void showImg(String imagesPath){
		cellID = 0;
		for(int h=0;h<3;h++){
			for(int w=0;w<3;w++){
				if(cellID<imgCountLimit){
					cell[cellID] = new Image(imagesPath+img[imgId]);
					cell[cellID].setTitle(imgName[imgId]);

					setWidget(h, w, cell[cellID]);
					cellOn[cellID] = true;

					cellID++;
					imgId++;
				}
				else{
					cell[cellID] = new Image("ext/cellBackground.gif");
					setWidget(h, w, cell[cellID]);
					cellOn[cellID] = false;
					cellID++;
				}
			}
		}
		addClickEvent();
	}


	/**
	 * add click event to each cell of the table.
	 */
	public void addClickEvent(){
		cell[0].addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(cellOn[0])popupImg(1);}} );
		if(cellOn[0])cell[0].addStyleName("handOver");
		cell[1].addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(cellOn[1])popupImg(2);}} );
		if(cellOn[1])cell[1].addStyleName("handOver");
		cell[2].addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(cellOn[2])popupImg(3);}} );
		if(cellOn[2])cell[2].addStyleName("handOver");
		cell[3].addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(cellOn[3])popupImg(4);}} );
		if(cellOn[3])cell[3].addStyleName("handOver");
		cell[4].addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(cellOn[4])popupImg(5);}} );
		if(cellOn[4])cell[4].addStyleName("handOver");
		cell[5].addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(cellOn[5])popupImg(6);}} );
		if(cellOn[5])cell[5].addStyleName("handOver");
		cell[6].addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(cellOn[6])popupImg(7);}} );
		if(cellOn[6])cell[6].addStyleName("handOver");
		cell[7].addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(cellOn[7])popupImg(8);}} );
		if(cellOn[7])cell[7].addStyleName("handOver");
		cell[8].addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(cellOn[8])popupImg(9);}} );
		if(cellOn[8])cell[8].addStyleName("handOver");
	}


	/**
	 * Method to pop up the PopUP panel to show image one by one.
	 * @param id the image id selected
	 */
	public void popupImg(int id){
		if(pg.albumsFlag){
			resetCount();
			pg.getAlbumNr(getID(id)-1);
			pg.bottomPanel.setAlbumLabel(pg.albums.getAlbumName(getID(id)-1));
		}
		else{
			showPopUpImg(getID(id));
		}
	}
	
	/**
	 * Method that shows the popUp with the selected image.
	 * @param id
	 */
	protected void showPopUpImg(int id){
		new PopUpImgShow(id-1, imgPath, imgP, imgName, imgComment).show();
	}

	/**
	 * Method to the image id.
	 */
	private int getID(int id){
		return id+page*9-9;
	}

	private void resetCount(){
		imgCountLimit = 9;
	}

}
