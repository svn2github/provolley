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

import java.util.List;

import org.bamzone.provolleyfr.ProVolley;
import org.bamzone.provolleyfr.R;
import org.bamzone.provolleyfr.data.ResultatsJournee;
import org.bamzone.provolleyfr.data.ResultatsMatch;
import org.bamzone.provolleyfr.data.ResultatsSaison;
import org.bamzone.provolleyfr.provider.JSONProvider;
import org.bamzone.provolleyfr.provider.JSONProviderFactory;
import org.bamzone.provolleyfr.utils.ListTagHandler;
import org.bamzone.provolleyfr.utils.OnSwipeTouchListener;

import android.app.ListActivity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
	
	ResultatsSaison resultatsSaison;
	JSONProvider dataProvider;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coupe_activity);
        ((TextView) findViewById(R.id.JourneeTextView)).setText("");
        
        String competition = getIntent().getExtras().getString(ProVolley.INTENT_EXTRA_COMPETITION);
        
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(this);
        dataProvider = JSONProviderFactory.getDataProvider(prefs);

        GetResultatsSaison task  = new GetResultatsSaison();
        task.execute(new String[] {competition});

        ListView listView=getListView();
        listView.setOnTouchListener(new OnSwipeTouchListener() {
            @Override
            public void onSwipeLeft() {
            	onNextButtonClick(null);
            }
            @Override
            public void onSwipeRight() {
            	onPrevButtonClick(null);
            }
        });
	}
    
    public void onPrevButtonClick(View view) {
    	if(numJournee>minJournee) {
    		numJournee--;
        	enableButtons();
        	displayJourneeSelectionnee();
    	}
    }
    
    public void onNextButtonClick(View view) {
    	if(numJournee<maxJournee) {
    		numJournee++;
        	enableButtons();
        	displayJourneeSelectionnee();
    	}
    }
    
    private void enableButtons() {
		Button prev = ((Button) findViewById(R.id.PrevButton));
		if (prev!=null) prev.setEnabled(numJournee>minJournee);
		Button next = ((Button) findViewById(R.id.NextButton));
		if (next!=null) next.setEnabled(numJournee<maxJournee);
    }
    
	private void displayJourneeSelectionnee() {
        
		if (resultatsSaison!=null) {
			ResultatsJournee resultatJournee = resultatsSaison.getResultatsJournee(numJournee);
			if (resultatJournee!=null) {
        		TextView journee = (TextView) findViewById(R.id.JourneeTextView);
                if (journee!=null) journee.setText(Html.fromHtml(resultatJournee.getTitre(),null,new ListTagHandler()));
                
        		List<ResultatsMatch> matchs = resultatJournee.getMatchs();
        		CoupeArrayAdapter adapter = new CoupeArrayAdapter(matchs);
                setListAdapter(adapter);
	        }
        }
    }
    
	private void displayResultats(ResultatsSaison result) {
		if (result!=null) {
			// Save results
			resultatsSaison=result;
			
	        // recup journee a afficher
	        minJournee = resultatsSaison.getMinJournee();
	        maxJournee = resultatsSaison.getMaxJournee();
	        if(numJournee==0) numJournee=resultatsSaison.getCurrentJournee();
	        if(numJournee>maxJournee)numJournee=maxJournee;
        
	        // Afficher ou griser bouton de nav
	        enableButtons();
	        
	        // Display 
	        displayJourneeSelectionnee();
        }
	}

	private class GetResultatsSaison extends AsyncTask<String, Void, ResultatsSaison> {

		String competition;
		
		@Override
		protected ResultatsSaison doInBackground(String... competitions) {
			competition=competitions[0];
	        return CoupeHelper.getResultatsSaisonFromCache(competition);

		}
		
		protected void onPostExecute(ResultatsSaison result) {
			displayResultats(result);

	        DownloadResultatsSaison task  = new DownloadResultatsSaison();
	        task.execute(new String[] {competition});
		}
	}

	private class DownloadResultatsSaison extends AsyncTask<String, Void, ResultatsSaison> {

		@Override
		protected ResultatsSaison doInBackground(String... competitions) {
			 return(CoupeHelper.getResultatsSaisonFromServer(dataProvider, competitions[0]));
		}
		
		protected void onPostExecute(ResultatsSaison result) {
			displayResultats(result);

			// Now if resultatsSaison is still null, nothing has been displayed. Finish activity
			if(resultatsSaison==null) {
				// FIXME : hardcoded
				Toast.makeText(CoupeActivity.this, "Les informations ne sont pas disponibles pour le moment. Veuillez réessayer dans un instant.", Toast.LENGTH_LONG).show();
				CoupeActivity.this.finish();
			}
		}
		
	}

}