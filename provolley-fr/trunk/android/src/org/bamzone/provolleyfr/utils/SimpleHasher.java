/*
	ProVolley-fr
	Copyright (C) 2012-2013 Christophe Bothamy
	
	This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    You should have received a copy of the GNU General Public License
    along with this program.  If not, see http://www.gnu.org/licenses.
*/  	
package org.bamzone.provolleyfr.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.util.Log;

public class SimpleHasher {
	public final static String SHA1 = "SHA-1";
	public final static String SHA512 = "SHA-512";
	
	public static String hash(String algorithm, String toHash)
	{
		String hashed = null;
		try {
			byte []source = toHash.getBytes("UTF-8");
			MessageDigest digest = MessageDigest.getInstance(algorithm);
			byte []result = digest.digest(source);
			StringBuilder sb = new StringBuilder();
			for (byte b : result) {
				sb.append(String.format("%02x", b));
			}
			hashed = sb.toString();
		}
		catch (UnsupportedEncodingException uee) {
			Log.d(SimpleHasher.class.getName(),"Unsupported encoding exception :"+toHash);
		}
		catch (NoSuchAlgorithmException nsae) {
			Log.d(SimpleHasher.class.getName(),"No such algorithm :"+algorithm);
		}
		Log.d(SimpleHasher.class.getName(),toHash+"/"+hashed);
		return(hashed);
	}
}
