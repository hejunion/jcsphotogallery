/*	
 * 	File    : Albums.java
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

import java.util.ArrayList;


/**
 * The class to keep the albums data.
 *  
 * @version 1.1 
 * @author Daniel Cioi <dan@dancioi.net>
 */
public class Albums {
	
	ArrayList<String> album = new ArrayList<String>();					// array with albums strings.
	ArrayList<String> albumFolderName = new ArrayList<String>();		// folder name for each album.
	ArrayList<String> albumName = new ArrayList<String>();				// album name.
	ArrayList<String> albumCat1 = new ArrayList<String>();				// album category; first sort criteria.
	ArrayList<String> albumCat2 = new ArrayList<String>();				// album category; second sort criteria.
	
	

	int nrAlbumsV;					
	String []albumV;				// albums strings (visible).
	String []albumFolderNameV;		// folder name for each album visible).
	String []albumNameV;			// album name visible). 
	
	public Albums(){
		
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
				albumS[in] = album.get(i);
				albumFolderNameS[in] = albumFolderName.get(i);
				albumNameS[in] = albumName.get(i);
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
		nrAlbumsV = album.size();
		albumV = new String[nrAlbumsV];
		album.toArray(albumV);
		albumFolderNameV = new String[nrAlbumsV];
		albumFolderName.toArray(albumFolderNameV);
		albumNameV = new String[nrAlbumsV];
		albumName.toArray(albumNameV);
	}
	
	
	//public void addAlbum
	
	public void addAlbum(int i, String val){
		album.add(val);
	}

	public void addAlbumFolderName(int i, String val){
		albumFolderName.add(val);
	}

	public void addAlbumName(int i, String val){
		albumName.add(val);
	}

	public void addAlbumCat1(int i, String val){
		albumCat1.add(val);
	}

	public void addAlbumCat2(int i, String val){
		albumCat2.add(val);
	}

	public String[] getAlbum(){
		return albumV;
	}

	public String[] getAlbumFolderName(){
		return albumFolderNameV;
	}

	public String getAlbumFolderName(int a){
		return albumFolderNameV[a];
	}

	public String[] getAlbumName(){
		return albumNameV;
	}

	public String getAlbumName(int a){
		return albumNameV[a];
	}

	public String[] getAlbumCat1(){
		return (String[]) albumCat1.toArray();
	}

	public String[] getAlbumCat2(){
		return (String[]) albumCat2.toArray();
	}

	public int getNrAlbums(){
		return nrAlbumsV;
	}
	
	/**
	 * Method to get the number of all albums in gallery.
	 * @return number of all albums.
	 */
	public int getAllAlbumsNr(){
		return album.size();
	}
	

}
