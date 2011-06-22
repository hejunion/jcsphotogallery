/*	
 * 	File    : ReadXML.java
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

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;
import com.google.gwt.xml.client.impl.DOMParseException;


/**
 * The class to read the XML files from the web server.
 *  
 * @version 1.0.2 
 * @author Daniel Cioi <dan@dancioi.net>
 */

public class ReadXML extends ReadXMLGeneric{

	Jcsphotogallery pg;
	boolean albums = true;		// flag to read the albums xml file just once.
	String curentImgPath;
	String currentXMLFile;

	public ReadXML(Jcsphotogallery pg){
		this.pg = pg;
		// read the xml file where the albums parameters are.
		getXML("gallery/albums.xml", "gallery/");
	}

	/**
	 * Method to get the XML file from http server.
	 */
	public void getXML(String file, String imgPath){
		curentImgPath = imgPath;
		setCurrentXMLFile(file);
		RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET,
				file);
		try {
			requestBuilder.sendRequest(null, new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					new ReadException("Error sending request");
				}
				public void onResponseReceived(Request request, Response response) {
					if (200 == response.getStatusCode()) {
						if(albums)readAlbums(response.getText());
						else readAlbum(response.getText());	
					} 
					else if(404 == response.getStatusCode()){
						new ReadException("File "+getCurrentXMLFile()+" not found on server. Wrong name or missing.");
					}
					else{
						new ReadException("Other exception on GET the "+getCurrentXMLFile() +" file");
					}

				}
			});
		} catch (RequestException ex) {
			new ReadException("Error sending request");
		}
	}

	/**
	 * Method that sets the current XML file.  
	 * @param currentFile
	 */
	private void setCurrentXMLFile(String currentFile){
		currentXMLFile = currentFile;
	}

	/**
	 * Method to get the current XML file.
	 * @return String
	 */
	private String getCurrentXMLFile(){
		return currentXMLFile;
	}


	/**
	 * Method to get the albums' items' list.
	 * Parse the XML string.
	 * @param xmlText String.
	 */
	public void readAlbums(String xmlText){
		Document document = null;
		try{
			document = XMLParser.parse(xmlText);

			Element element = document.getDocumentElement();
			XMLParser.removeWhitespace(element);

			String galleryName = element.getElementsByTagName("galleryName").item(0).getFirstChild().getNodeValue();
			String nameHomePage = element.getElementsByTagName("homePage").item(0).getFirstChild().getNodeValue();
			pg.setGalleryName(galleryName, nameHomePage);

			albums = false;
//TODO add a file version and an element with categories number;
			String[] categories = new String[2];
			
			NodeList albums = element.getElementsByTagName("album");

			pg.initializeAlbums();

			for (int i = 0; i < albums.getLength(); i++) {
				Element elAlbum = (Element) albums.item(i);
				
				categories[0] = elAlbum.getAttribute("cat1");
				categories[1] = elAlbum.getAttribute("cat2");
				pg.albums.addAlbum(new AlbumBean(elAlbum.getAttribute("img"), elAlbum.getAttribute("folderName"), 
						elAlbum.getAttribute("name"), categories));
			}
			pg.albums.showAll();		// at the beginning shows all albums.
			pg.center.prepareImg("gallery/", pg.albums.getNrAlbums(), pg.albums.getAlbumsVisible(), pg.albums.getAlbumsNameVisible());
			pg.sA.sortAlbums(pg.albums.getAlbumsCategories());
		}
		catch(DOMParseException de){
			new ReadException("File "+getCurrentXMLFile()+" parse exception. Use a XML editor to avoid syntax errors in xml file.");
		}
	}

	/**
	 * Method to get the album's images list.
	 */
	public void readAlbum(String xmlText){
		Document document = null;
		try{
			document = XMLParser.parse(xmlText);

			pg.albumsFlag = false;
			Element element = document.getDocumentElement();
			XMLParser.removeWhitespace(element);

			NodeList images = element.getElementsByTagName("i");
			int imgCount = images.getLength();

			String []img = new String[imgCount];
			String []imgT = new String[imgCount];
			String []imgName = new String[imgCount];
			String []imgComment = new String[imgCount];

			for (int i = 0; i < images.getLength(); i++) {
				Element elAlbum = (Element) images.item(i);
				imgT[i]= elAlbum.getAttribute("imgt");
				img[i]= elAlbum.getAttribute("img");
				imgName[i] = elAlbum.getAttribute("name");
				imgComment[i] = elAlbum.getAttribute("comment");
			}

			pg.center.prepareImg(curentImgPath, imgCount, imgT, imgName, img, imgComment);
		}
		catch(DOMParseException de){
			new ReadException("File "+getCurrentXMLFile()+" parse exception. Use a XML editor to avoid syntax errors in xml file.");
		}
	}

}
