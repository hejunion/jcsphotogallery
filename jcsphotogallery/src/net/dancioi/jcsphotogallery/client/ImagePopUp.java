/*	
 * 	File    : ImagePopUp.java
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

import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Image;

/**
 * The image class to generate a event when the image is fully loaded.
 *  
 * @version 1.0 
 * @author Daniel Cioi <dan@dancioi.net>
 */

public class ImagePopUp extends Image{

	PopUpImgShow pi;

	public ImagePopUp(String link, PopUpImgShow pi){
		super(link);
		this.pi = pi;
	}	

	/**
	 * When the image is fully loaded a event is fired.
	 */
	public void onBrowserEvent(Event event) {
		if (event.getTypeInt() == Event.ONLOAD) {
			pi.scaleImg(this);
		}
	}

}
