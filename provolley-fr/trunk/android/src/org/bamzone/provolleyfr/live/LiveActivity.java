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
package org.bamzone.provolleyfr.live;

import java.util.List;

import org.bamzone.provolleyfr.ProVolley;
import org.bamzone.provolleyfr.R;
import org.bamzone.provolleyfr.data.LiveMatch;
import org.bamzone.provolleyfr.data.LiveResultats;
import org.bamzone.provolleyfr.provider.JSONProvider;
import org.bamzone.provolleyfr.provider.JSONProviderFactory;

import com.google.analytics.tracking.android.EasyTracker;

import android.app.ListActivity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LiveActivity extends ListActivity {
	
	private boolean isCancelled = false;

	  @Override
	  public void onStart() {
	    super.onStart();
	
	    EasyTracker.getInstance().activityStart(this); 
	  }

	  @Override
	  public void onStop() {
	    super.onStop();

	    EasyTracker.getInstance().activityStop(this);
	    
	    isCancelled = true;
	  }

	JSONProvider dataProvider;
	private final int defaultIntervalInSec = 30;
	private int reloadInterval = defaultIntervalInSec*1000;
	private Handler reloadHandler;

	  public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.live_activity);

		Resources resources = getApplicationContext().getResources();
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(this);
		
		if (prefs.getBoolean(ProVolley.PREF_KEY_DISABLE_SLEEP_IN_LIVE, true)) {
			this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		}

		try {
			reloadInterval = Integer.parseInt(prefs.getString(ProVolley.PREF_KEY_LIVEINTERVAL,
					 Integer.toString(defaultIntervalInSec))) * 1000;
		} catch (NumberFormatException nfe) {
			reloadInterval = defaultIntervalInSec*1000;
		}

		dataProvider = JSONProviderFactory.getDataProvider(resources, prefs);

		reloadHandler = new Handler();
		startRepeatingTask();
	}

	Runnable reloadTask = new Runnable() {
		@Override
		public void run() {
			updateLive(); 
		}
	};

	void startRepeatingTask() {
		reloadTask.run();
	}

	void stopRepeatingTask() {
		reloadHandler.removeCallbacks(reloadTask);
	}

	void updateLive() {
		ProgressBar bar = (ProgressBar) findViewById(R.id.ProgressBar);
		if (bar!=null) bar.setVisibility(View.VISIBLE);

		DownloadLive task  = new DownloadLive();
        task.execute();
	}
	
	private class DownloadLive extends AsyncTask<Void, Void, LiveResultats> {

		@Override
		protected LiveResultats doInBackground(Void... args) {
  			 Log.d(this.getClass().getName(),"Downloading live data");
			 return(LiveHelper.getResultatsLive(dataProvider));
		}
		
		protected void onPostExecute(LiveResultats resultatsLive) {
	        if (resultatsLive != null) {

    			TextView majTextView = (TextView) findViewById(R.id.HeureMajTextView);
    			if (majTextView!=null) majTextView.setText(resultatsLive.getHeureMaj());

    			List<LiveMatch> matchs = resultatsLive.getMatchs();

    			// create adapter or refill : fixes the "back to the top bug" when
    			// reloading
    			if (getListAdapter() == null) {
    				Log.d(this.getClass().getName(),"getListAdapter is null : create");
    				LiveArrayAdapter adapter = new LiveArrayAdapter(LiveActivity.this, matchs);
    				setListAdapter(adapter);
    			} else {
    				Log.d(this.getClass().getName(),"getListAdapter is not null : refill");
    				((LiveArrayAdapter) getListAdapter()).refill(matchs);
    			}
	        }
			else {
				// Something went wrong. Dont'do anything. Wait for next 
			}

			ProgressBar bar = (ProgressBar) findViewById(R.id.ProgressBar);
			if (bar!=null) bar.setVisibility(View.INVISIBLE);
			
			if (!isCancelled) {
				// reload
				Log.i(this.getClass().getName(),"Posting delayed task");
				reloadHandler.postDelayed(reloadTask, reloadInterval);
			}
		}
	}
}