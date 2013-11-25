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
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.text.util.Linkify;
import android.widget.TextView;

public class GreetingsDialog extends Dialog{
	private static Context mContext = null;
	public GreetingsDialog(Context context) {
		super(context);
		mContext = context;
	}


	/**
	 * Standard Android on create method that gets called when the activity initialized.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.greetings);

		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
		final String version = mContext.getResources().getString(R.string.application_version);
		TextView tv = (TextView)findViewById(R.id.greetings_text);
		String s = "";

		final String version_1_1_0 = mContext.getResources().getString(R.string.application_version_1_1_0);
		final String version_1_2_0 = mContext.getResources().getString(R.string.application_version_1_2_0);
		
		// Application Never Launched
		// TODO use reflection to dynamiccaly create text
		if ("0".equals(prefs.getString(ProVolley.PREF_KEY_LAST_LAUNCHED_VERSION, "0"))) {
			s = mContext.getResources().getString(R.string.application_description) +
					mContext.getResources().getString(R.string.application_historique) +
					mContext.getResources().getString(R.string.application_histo_v1_3_0)+
					mContext.getResources().getString(R.string.application_histo_v1_2_0)+
					mContext.getResources().getString(R.string.application_histo_v1_1_0)+
					mContext.getResources().getString(R.string.application_histo_v1_0_0);
			
		}
		else if (version_1_2_0.equals(prefs.getString(ProVolley.PREF_KEY_LAST_LAUNCHED_VERSION, "0"))) {
			// Upgrade from version 1.2.0
			s = mContext.getResources().getString(R.string.application_maj) +
					mContext.getResources().getString(R.string.application_histo_v1_3_0);
		}
		else if (version_1_1_0.equals(prefs.getString(ProVolley.PREF_KEY_LAST_LAUNCHED_VERSION, "0"))) {
			// Upgrade from version 1.1.0
			s = mContext.getResources().getString(R.string.application_maj) +
					mContext.getResources().getString(R.string.application_histo_v1_3_0) +
					mContext.getResources().getString(R.string.application_histo_v1_2_0);
		}
		else {
			// Upgrade from version 1.0.1 / 1.0.0
			s = mContext.getResources().getString(R.string.application_maj) +
					mContext.getResources().getString(R.string.application_histo_v1_3_0) +
					mContext.getResources().getString(R.string.application_histo_v1_2_0) +
					mContext.getResources().getString(R.string.application_histo_v1_1_0);
		}
		
		tv.setText(Html.fromHtml(s,null,new ListTagHandler()));
		tv.setLinkTextColor(Color.WHITE);
		Linkify.addLinks(tv, Linkify.ALL);
		
		// store last lauched version
		Editor prefedit = prefs.edit();
		prefedit.putString(ProVolley.PREF_KEY_LAST_LAUNCHED_VERSION, version);
		prefedit.commit();
	}

	
}