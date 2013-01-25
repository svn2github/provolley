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

import org.bamzone.provolleyfr.utils.ListTagHandler;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.util.Linkify;
import android.widget.TextView;
public class AboutDialog extends Dialog{
	Context mContext;
	
	public AboutDialog(Context context) {
		super(context);
		mContext=context;
	}
	/**
	 * Standard Android on create method that gets called when the activity initialized.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.about);

		TextView tv;
		tv = (TextView)findViewById(R.id.application_email_text);
		tv.setLinkTextColor(Color.WHITE);
		Linkify.addLinks(tv, Linkify.ALL);
		tv = (TextView)findViewById(R.id.application_website_text);
		tv.setLinkTextColor(Color.WHITE);
		Linkify.addLinks(tv, Linkify.ALL);
		
		tv = (TextView)findViewById(R.id.legal_text);
		tv.setText(Html.fromHtml(mContext.getResources().getString(R.string.legal_text),null,new ListTagHandler()));
		tv.setLinkTextColor(Color.WHITE);
		Linkify.addLinks(tv, Linkify.ALL);
		
	}

}