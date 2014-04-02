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

import org.bamzone.provolleyfr.classements.ClassementsTabActivity;
import org.bamzone.provolleyfr.coupe.CoupeTabActivity;
import org.bamzone.provolleyfr.live.LiveActivity;
import org.bamzone.provolleyfr.news.NewsActivity;
import org.bamzone.provolleyfr.progtv.ProgTVActivity;
import org.bamzone.provolleyfr.resultats.ResultatsTabActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.google.analytics.tracking.android.EasyTracker;

public class AccueilActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.accueil_activity);

		// Check if first launch of this version
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		final String version = getResources().getString(R.string.application_version);
		// FIXME : Hardcoded
		if (!version.equals(prefs.getString(ProVolley.PREF_KEY_LAST_LAUNCHED_VERSION, "0"))) {
			// Display Greetings text
			GreetingsDialog greets = new GreetingsDialog(this);
			greets.requestWindowFeature(Window.FEATURE_NO_TITLE);
			greets.show();
		}
		
	}

	@Override
	public void onStart() {
		super.onStart();

		EasyTracker.getInstance().activityStart(this);
	}

	@Override
	public void onStop() {
		super.onStop();

		EasyTracker.getInstance().activityStop(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	public void onNewsButtonClick(View view) {
		Intent newsIntent = new Intent(this, NewsActivity.class);
		startActivity(newsIntent);
	}

	public void onEquipesButtonClick(View view) {
		if (!isNetworkAvailable())
			Toast.makeText(this, R.string.alertIfNoNetwork, Toast.LENGTH_SHORT).show();
		else
			Toast.makeText(this, "Equipes...", Toast.LENGTH_SHORT).show();
	}

	public void onResultatsButtonClick(View view) {
		Intent resultatsTabIntent = new Intent(this, ResultatsTabActivity.class);
		startActivity(resultatsTabIntent);
	}

	public void onCoupeButtonClick(View view) {
		Intent coupeTabIntent = new Intent(this, CoupeTabActivity.class);
		startActivity(coupeTabIntent);
	}

	public void onClassementsButtonClick(View view) {
		Intent classementsTabIntent = new Intent(this, ClassementsTabActivity.class);
		startActivity(classementsTabIntent);
	}

	public void onLiveButtonClick(View view) {
		Intent liveIntent = new Intent(this, LiveActivity.class);
		startActivity(liveIntent);
	}

	public void onProgTVButtonClick(View view) {
		Intent progTVIntent = new Intent(this, ProgTVActivity.class);
		startActivity(progTVIntent);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.license:
			LicenseDialog license = new LicenseDialog(this);
			license.requestWindowFeature(Window.FEATURE_NO_TITLE);
			license.show();
			return true;
		case R.id.help:
			HelpDialog help = new HelpDialog(this);
			help.requestWindowFeature(Window.FEATURE_NO_TITLE);
			help.show();
			return true;
		case R.id.about:
			AboutDialog about = new AboutDialog(this);
			about.requestWindowFeature(Window.FEATURE_NO_TITLE);
			about.show();
			return true;
		case R.id.parameters:
			Intent settingsActivity = new Intent(getBaseContext(), PreferencesActivity.class);
			startActivity(settingsActivity);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public boolean isNetworkAvailable() {
		ConnectivityManager connectivity = (ConnectivityManager) this.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			return false;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
