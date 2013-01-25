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
    
    Additional permission under GNU GPL version 3 section 7

	If you modify this Program, or any covered work, by linking or combining 
	it with Google Admob or Goggle Analytics libraries (or a modified version of those libraries), 
	containing parts covered by the terms of the Google licenses, the licensors of this 
	Program grant you additional permission to convey the resulting work.
*/  	
package org.bamzone.provolleyfr;

import java.util.Arrays;
import java.util.HashSet;

import android.app.Activity;
import android.content.Context;
import android.preference.PreferenceManager;
import android.util.AttributeSet;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;

public class AdView extends com.google.ads.AdView {

	public AdView(Activity activity, AdSize adSize, String adUnitId) {
		super(activity, adSize, adUnitId);
		loadAd(activity);
	}

	public AdView(Context context, AttributeSet attrs) {
		super(context, attrs);
		loadAd(context);
	}

	AdView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		loadAd(context);
	}
	
	private void loadAd(Context context) {
		if (PreferenceManager.getDefaultSharedPreferences(context).getBoolean(
				ProVolley.PREF_KEY_SHOWADS, true)) {
			AdRequest adRequest = new AdRequest();
			adRequest.addKeywords(new HashSet<String>(Arrays.asList("lnv", "volleyball", "volley", "live scores", "ligue A", "ligue B", "ma chaine sport")));
			loadAd(adRequest);
		}
	}
}