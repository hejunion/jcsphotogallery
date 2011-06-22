/*	
 * 	File    : Albums.java
 * 
 * 	Copyright (C) 2010-2011 Daniel Cioi <dan@dancioi.net>
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

import java.util.ArrayList;


/**
 * The class to keep the albums data.
 *  
 * @version 1.2 
 * @author Daniel Cioi <dan@dancioi.net>
 */
public class Albums {
	
	private ArrayList<AlbumBean> albums = new ArrayList<AlbumBean>();

	int nrAlbumsV;					
	String []albumV;				// albums strings (visible).
	String []albumFolderNameV;		// folder name for each album (visible).
	String []albumNameV;			// album name (visible). 
	
	public Albums(){
		
	}
	
	public void addAlbum(AlbumBean ab){
		albums.add(ab);
	}

	/**
	 * Show sorted albums.
	 * @param visible flag with which album will be shown.  
	 */
	public void showSorted(boolean[] visible){
		int count = 0;
		for(int i=0;i<visible.length;i++)
			if(visible[i])count++;


		nrAlbumsV = count;
		String[] albumS = new String[count];
		String[] albumFolderNameS = new String[count];
		String[] albumNameS = new String[count];
		int in=0;
		for(int i=0;i<visible.length;i++){
			if(visible[i]){
				albumS[in] = albums.get(i).getImg();
				albumFolderNameS[in] = albums.get(i).getFolderName();
				albumNameS[in] = albums.get(i).getName();
				in++;
			}
		}
		albumV = albumS;
		albumFolderNameV = albumFolderNameS;
		albumNameV = albumNameS;

	}

	/**
	 * Show all albums.
	 */
	public void showAll(){
		setVisibleAlbums();
	}

	protected void setVisibleAlbums(){
		nrAlbumsV = albums.size();
		albumV = new String[nrAlbumsV];
		albumFolderNameV = new String[nrAlbumsV];
		albumNameV = new String[nrAlbumsV];
		
		for(int i=0;i<nrAlbumsV;i++){
			albumV[i] = albums.get(i).getImg();
			albumFolderNameV[i]= albums.get(i).getFolderName();
			albumNameV[i] = albums.get(i).getName();
		}
	}
	

	public int getNrAlbums(){
		return nrAlbumsV;
	}
	
	/**
	 * Method to get the number of all albums in gallery.
	 * @return number of all albums.
	 */
	public int getAllAlbumsNr(){
		return albums.size();
	}
	
	
	public String[] getAlbumsVisible(){
		return albumV;
	}

	public String[] getAlbumsNameVisible(){
		return albumNameV;
	}
	
	public String getAlbumFolderName(int a){
		return albumFolderNameV[a];
	}
	
	public String getAlbumName(int a){
		return albumNameV[a];
	}
	
	/**
	 * Method to get the albums categories.
	 * format: [cat 0...nrMaxCategories][album 0...length]
	 * @return
	 */
	public String[][] getAlbumsCategories(){
		String[][] catResult = null;
		int catLength = 0;
		if(albums.size()>0)catLength = albums.get(0).getCategory().length;
		catResult = new String[catLength][];
		String[] cat = null;
		for(int a=0; a<albums.size(); a++){
			cat = albums.get(a).getCategory();
			for(int c=0;c<cat.length;c++)
				catResult[c][a] = cat[c];
		}
		return catResult;
	}
	
}
