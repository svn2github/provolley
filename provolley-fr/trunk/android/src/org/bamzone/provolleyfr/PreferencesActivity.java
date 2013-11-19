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

import org.bamzone.provolleyfr.cache.ProVolleyCacheManager;
import org.bamzone.provolleyfr.resultats.ResultatsActivity;
import org.bamzone.provolleyfr.utils.SimpleHasher;

import com.google.analytics.tracking.android.EasyTracker;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;


public class PreferencesActivity extends PreferenceActivity implements
		OnSharedPreferenceChangeListener {

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
	protected void onCreate(Bundle savedInstanceState) {
		
		//requestWindowFeature(Window.FEATURE_NO_TITLE);

		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(R.layout.preferences);
		PreferenceManager.setDefaultValues(this, R.layout.preferences, false);

		for (int i = 0; i < getPreferenceScreen().getPreferenceCount(); i++) {
			initSummary(getPreferenceScreen().getPreference(i));
		}

		// Enable local + test server on selected devices
		Preference server = getPreferenceScreen().findPreference(ProVolley.PREF_KEY_SERVER);
		server.setEnabled(isTestDevice());
		server.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				ProVolleyCacheManager.getInstance().getCacheDataSource().deleteAllCachedItems();
				return true;
			}
		});
		
		// Action for emptying the cache
		Preference cleanCache = getPreferenceScreen().findPreference(ProVolley.PREF_KEY_CLEAN_CACHE);
		cleanCache.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(Preference preference) {
				ProVolleyCacheManager.getInstance().getCacheDataSource().deleteAllCachedItems();
				Toast.makeText(getApplicationContext(), "Le cache a été vidé.", Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		
		getPreferenceScreen().getSharedPreferences()
				.registerOnSharedPreferenceChangeListener(this);
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		// Set up a listener whenever a key changes
		getPreferenceScreen().getSharedPreferences()
				.registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		// Unregister the listener whenever a key changes
		getPreferenceScreen().getSharedPreferences()
				.unregisterOnSharedPreferenceChangeListener(this);
	}

	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		updatePrefSummary(findPreference(key));
	}

	private void initSummary(Preference p) {
		if (p instanceof PreferenceCategory) {
			PreferenceCategory pCat = (PreferenceCategory) p;
			for (int i = 0; i < pCat.getPreferenceCount(); i++) {
				initSummary(pCat.getPreference(i));
			}
		} else {
			updatePrefSummary(p);
		}

	}

	private void updatePrefSummary(Preference p) {
		if (p instanceof ListPreference) {
			ListPreference listPref = (ListPreference) p;
			listPref.setSummary(listPref.getEntry());
		}
		if (p instanceof EditTextPreference) {
			EditTextPreference editTextPref = (EditTextPreference) p;
			editTextPref.setSummary(editTextPref.getText());
		}

	}
	
	@SuppressLint("NewApi")
	private boolean isTestDevice() {
		 String serial = "";
		 try {
			 serial = android.os.Build.SERIAL;
			 Log.d(ProVolley.class.getName(),"SERIAL="+serial);
			 //return(ProVolley.SERIAL_HTC_DESIRE_CHRIS.equals(serial) || ProVolley.SERIAL_ASUS_NEXUS7_CHRIS.equals(serial) || ProVolley.SERIAL_SAMSUNG_GALAXYTAB_FRED.equals(serial) || "unknown".equals(serial));
		 }
		 catch (NoSuchFieldError nsfe) {
			 serial = Settings.System.getString(this.getContentResolver(),Settings.System.ANDROID_ID);
			 Log.d(ProVolley.class.getName(),"DeviceId="+serial);
			 //return (ProVolley.DEVICEID_HTC_MAGIC_CHRIS.equals(deviceId)||ProVolley.DEVICEID_EMULATOR.equals(deviceId));
		 }
		 
		 return (ProVolley.TEST_DEVICEID_SHA1S.contains(SimpleHasher.hash(SimpleHasher.SHA1, serial)));
	 }

}