/*
	ProVolley-fr
	Copyright (C) 2012 Christophe Bothamy
	
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
package org.bamzone.provolleyfr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.bamzone.provolleyfr.utils.ListTagHandlerNoBullets;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class LicenseDialog extends Dialog{
	private static Context mContext = null;
	public LicenseDialog(Context context) {
		super(context);
		mContext = context;
	}
	


	/**
	 * Standard Android on create method that gets called when the activity initialized.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		/*
		setContentView(R.layout.license);
		WebView wv = (WebView)findViewById(R.id.licenseWebView);
		wv.loadUrl("file:///android_asset/license.html");
		*/

		setContentView(R.layout.license);
		TextView tv = (TextView)findViewById(R.id.licenseView);

		tv.setText(Html.fromHtml(readRawTextFile(R.raw.license),null,new ListTagHandlerNoBullets()));
		//tv.setLinkTextColor(Color.WHITE);
		//Linkify.addLinks(tv, Linkify.ALL);
		
	}

	public static String readRawTextFile(int id) {
		InputStream inputStream = mContext.getResources().openRawResource(id);
//		byte[] reader;
//		try {
//			reader = new byte[inputStream.available()];
//			while (inputStream.read(reader) != -1) {}
//			String result = new String(reader);
//			inputStream.close();
//			return result;
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			return null;
//		}
//		finally {
//			if (inputStream!=null) try {
//				inputStream.close();
//			}
//			catch (IOException  ioe) {
//			}
//		}
		
		InputStreamReader in = new InputStreamReader(inputStream);
		BufferedReader buf = new BufferedReader(in,32*1024);
		StringBuilder text = new StringBuilder();
		String line;
		try {

			while ((line = buf.readLine()) != null) {text.append(line);text.append("\n");}
		} catch (IOException e) {
			return null;
		}
		return text.toString();
	}
}