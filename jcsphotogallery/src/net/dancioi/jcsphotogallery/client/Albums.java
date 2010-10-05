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


/**
 * The class to keep the albums data.
 *  
 * @version 1.0 
 * @author Daniel Cioi <dan@dancioi.net>
 */
public class Albums {

	int nrAlbums;					// how many albums there are to know the array size.
	String []album;					// array with albums strings.
	String []albumFolderName;		// folder name for each album.
	String []albumName;				// album name.
	String []albumCat1;				// album category; first sort criteria.
	String []albumCat2;				// album category; second sort criteria.


	int nrAlbumsV;					
	String []albumV;				// albums strings (visible).
	String []albumFolderNameV;		// folder name for each album visible).
	String []albumNameV;			// album name visible). 


	public Albums(int albumsCount){
		nrAlbums = albumsCount;
		album = new String[albumsCount];
		albumFolderName = new String[albumsCount];
		albumName = new String[albumsCount];
		albumCat1 = new String[albumsCount];
		albumCat2 = new String[albumsCount];
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
				albumS[in] = album[i];
				albumFolderNameS[in] = albumFolderName[i];
				albumNameS[in] = albumName[i];
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
		nrAlbumsV = nrAlbums;
		albumV = album;
		albumFolderNameV = albumFolderName;
		albumNameV = albumName;
	}

	public void addAlbum(int i, String val){
		album[i]= val;
	}

	public void addAlbumFolderName(int i, String val){
		albumFolderName[i]= val;
	}

	public void addAlbumName(int i, String val){
		albumName[i]= val;
	}

	public void addAlbumCat1(int i, String val){
		albumCat1[i]= val;
	}

	public void addAlbumCat2(int i, String val){
		albumCat2[i]= val;
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
		return albumCat1;
	}

	public String[] getAlbumCat2(){
		return albumCat2;
	}

	public int getNrAlbums(){
		return nrAlbumsV;
	}

}
