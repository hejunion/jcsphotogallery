package net.dancioi.jcsphotogallery.client;

import java.util.List;

public abstract class ReadXMLGeneric {

	public abstract void getXML(String file, String imgPath);
	
	public abstract void readAlbums(String xmlText);
	
	public abstract void readAlbum(String xmlText);
	
	public List getGalleryAlbums(){
		return null;
	}
	
	public PictureBean[] getGalleryAlbumPictures(){
		return null;
	}
	
}
