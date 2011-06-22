/*	
 * 	File    : AlbumBean.java
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

package net.dancioi.jcsphotogallery.client;


/**
 * This class .
 *  
 * @version 1.0 
 * @author Daniel Cioi <dan@dancioi.net>
 */

public class AlbumBean {
	
	private String img;			// img is the thumbnail.
	private String folderName;
	private String name;
	private String[] category;
	
	/**
	 * Default constructor.
	 */
	public AlbumBean(){}
	
	
	/**
	 * Constructor.
	 * @param img
	 * @param folderName
	 * @param name
	 * @param thumbnail
	 * @param category
	 */
	public AlbumBean(String img, String folderName, String name, String[] category) {
		super();
		this.img = img;
		this.folderName = folderName;
		this.name = name;
		this.category = category;
	}

	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getFolderName() {
		return folderName;
	}
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getCategory() {
		return category;
	}
	public void setCategory(String[] category) {
		this.category = category;
	}
	
}