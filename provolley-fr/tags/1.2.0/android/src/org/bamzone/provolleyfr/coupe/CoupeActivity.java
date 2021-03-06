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
package org.bamzone.provolleyfr.coupe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.ListResourceBundle;

import android.app.ListActivity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.impl.client.DefaultHttpClient;
import org.bamzone.provolleyfr.ProVolley;
import org.bamzone.provolleyfr.R;
import org.bamzone.provolleyfr.R.id;
import org.bamzone.provolleyfr.R.layout;
import org.bamzone.provolleyfr.data.*;
import org.bamzone.provolleyfr.provider.JSONProvider;
import org.bamzone.provolleyfr.provider.JSONProviderFactory;
import org.bamzone.provolleyfr.utils.ListTagHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.analytics.tracking.android.EasyTracker;
 
public class CoupeActivity extends ListActivity {
	
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

	int numJournee=0;
	int minJournee=0;
	int maxJournee=0;
	String competition;
	
	ResultatsSaison resultatsSaison;
	JSONProvider dataProvider;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coupe_activity);
        ((TextView) findViewById(R.id.JourneeTextView)).setText("");
        
        competition = getIntent().getExtras().getString(ProVolley.INTENT_EXTRA_COMPETITION);
        Resources resources = getApplicationContext().getResources();
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(this);
        
        dataProvider = JSONProviderFactory.getDataProvider(resources, prefs);
        
        DownloadResultatsSaison task  = new DownloadResultatsSaison();
        task.execute(new String[] {competition});
    }
    
    public void onPrevButtonClick(View view) {
    	if(numJournee>minJournee) {
    		numJournee--;
        	enableButtons();
        	displayJournee();
    	}
    }
    
    public void onNextButtonClick(View view) {
    	if(numJournee<maxJournee) {
    		numJournee++;
        	enableButtons();
        	displayJournee();
    	}
    }
    
    private void enableButtons() {
		Button prev = ((Button) findViewById(R.id.PrevButton));
		if (prev!=null) prev.setEnabled(numJournee>minJournee);
		Button next = ((Button) findViewById(R.id.NextButton));
		if (next!=null) next.setEnabled(numJournee<maxJournee);
    }
    
	private void displayJournee() {
        
		if (resultatsSaison!=null) {
	        for (ResultatsJournee resultatJournee: resultatsSaison.getJournees()) {
	        	if (resultatJournee.getNumJournee()==numJournee) {
	        		TextView journee = (TextView) findViewById(R.id.JourneeTextView);
	                if (journee!=null) journee.setText(Html.fromHtml(resultatJournee.getTitre(),null,new ListTagHandler()));
	                
	        		List<ResultatsMatch> matchs = resultatJournee.getMatchs();
	        		CoupeArrayAdapter adapter = new CoupeArrayAdapter(this, matchs);
	                setListAdapter(adapter);
	        	}
	        }
        }
    }
    
	private class DownloadResultatsSaison extends AsyncTask<String, Void, ResultatsSaison> {

		@Override
		protected ResultatsSaison doInBackground(String... competitions) {
			 return(CoupeHelper.getResultatsSaison(dataProvider, competitions[0]));
		}
		
		protected void onPostExecute(ResultatsSaison result) {
			resultatsSaison=result;
			if (resultatsSaison!=null) {
		        // recup journee a afficher
		        minJournee = resultatsSaison.getMinJournee();
		        maxJournee = resultatsSaison.getMaxJournee();
		        numJournee = resultatsSaison.getCurrentJournee();
	        
		        // Afficher ou griser bouton de nav
		        enableButtons();
		        displayJournee();
	        }
			else {
				// FIXME : hardcoded
				Toast.makeText(CoupeActivity.this, "Les informations ne sont pas disponibles pour le moment. Veuillez réessayer dans un instant.", Toast.LENGTH_LONG).show();
				CoupeActivity.this.finish();
			}
		}
		
	}

}