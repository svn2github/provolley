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
package org.bamzone.provolleyfr.progtv;

import java.util.List;

import org.bamzone.provolleyfr.ProVolley;
import org.bamzone.provolleyfr.R;
import org.bamzone.provolleyfr.data.TVEmission;
import org.bamzone.provolleyfr.data.TVProgramme;
import org.bamzone.provolleyfr.provider.JSONProvider;
import org.bamzone.provolleyfr.provider.JSONProviderFactory;
import org.bamzone.provolleyfr.utils.Sharable;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.analytics.tracking.android.EasyTracker;

public class ProgTVActivity extends ListActivity {

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

	TVProgramme programmeTV;
	JSONProvider dataProvider;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progtv_activity);

		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		dataProvider = JSONProviderFactory.getDataProvider(prefs);

		GetProgTV task = new GetProgTV();
		task.execute();
	}

	private void displayResults(TVProgramme result) {
		if (result != null) {
			programmeTV = result;
			final List<TVEmission> emissions = programmeTV.getEmissions();

			if (emissions.size() == 0) {
				// FIXME : hardcoded
				final ArrayAdapter<String> adapter = new ArrayAdapter<String>(ProgTVActivity.this, R.layout.progtv_none,
						new String[] { "Pas de programme trouvé..." });
				setListAdapter(adapter);
			} else {
				final ProgTVArrayAdapter adapter = new ProgTVArrayAdapter(emissions);
				setListAdapter(adapter);

				ProgTVActivity.this.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						Sharable progtv = new Sharable(adapter.getItem(position));

						Intent progTVDetailIntent = new Intent(ProgTVActivity.this, ProgTVDetailActivity.class);
						progTVDetailIntent.putExtra(ProVolley.INTENT_EXTRA_PROGSTVITEM, progtv);
						startActivity(progTVDetailIntent);
					}
				});

			}
		}
	}

	private class GetProgTV extends AsyncTask<Void, Void, TVProgramme> {

		@Override
		protected TVProgramme doInBackground(Void... args) {
			//Log.d(this.getClass().getName(), "Downloading ProgrammeTV data");
			return (ProgTVHelper.getProgrammeTVFromCache());
		}

		protected void onPostExecute(TVProgramme result) {
			displayResults(result);

			DownloadProgTV task = new DownloadProgTV();
			task.execute();
		}
	}

	private class DownloadProgTV extends AsyncTask<Void, Void, TVProgramme> {

		@Override
		protected TVProgramme doInBackground(Void... args) {
			//Log.d(this.getClass().getName(), "Downloading ProgrammeTV data");
			return (ProgTVHelper.getProgrammeTVFromServer(dataProvider));
		}

		protected void onPostExecute(TVProgramme result) {
			displayResults(result);

			// Now if main variable is still null, nothing has been displayed.
			// Finish activity
			if (programmeTV == null) {
				// FIXME : hardcoded
				Toast.makeText(ProgTVActivity.this, "Les informations ne sont pas disponibles pour le moment. Veuillez réessayer dans un instant.",
						Toast.LENGTH_LONG).show();
				ProgTVActivity.this.finish();
			}
		}
	}
}