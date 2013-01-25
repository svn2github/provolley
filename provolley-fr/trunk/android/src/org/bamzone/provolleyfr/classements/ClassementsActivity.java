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
package org.bamzone.provolleyfr.classements;

import org.bamzone.provolleyfr.ProVolley;
import org.bamzone.provolleyfr.R;
import org.bamzone.provolleyfr.data.ClassementCompetition;
import org.bamzone.provolleyfr.data.ClassementEquipe;
import org.bamzone.provolleyfr.provider.JSONProvider;
import org.bamzone.provolleyfr.provider.JSONProviderFactory;

import com.google.analytics.tracking.android.EasyTracker;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
 
public class ClassementsActivity extends Activity {
	
	LayoutInflater inflater;
	TableLayout tableLayout;
	JSONProvider dataProvider;
	ClassementCompetition classement;
	
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

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classements_activity);
        
        String competition = getIntent().getExtras().getString(ProVolley.INTENT_EXTRA_COMPETITION);
        Resources resources = getApplicationContext().getResources();
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(this);
        
        dataProvider = JSONProviderFactory.getDataProvider(resources, prefs);
        tableLayout = (TableLayout)findViewById(R.id.TableLayout);
        inflater = (LayoutInflater) getApplicationContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
        TableRow loading = (TableRow)inflater.inflate(R.layout.classements_load, tableLayout, false);
        tableLayout.addView(loading);
        
        DownloadClassement task  = new DownloadClassement();
        task.execute(new String[] {competition});
    }

	private TableRow newSeparator() {
    	TableRow sep = (TableRow)inflater.inflate(R.layout.classements_sep, tableLayout, false);
		return sep;
    }
    
	private TableRow populateNewRow(String rang, String nom, String points, String mj, 
			String m30, String m32, String m23, String m03) {
		TableRow row = (TableRow)inflater.inflate(R.layout.classements_row, tableLayout, false);
		
		TextView positionTV = (TextView)(row.findViewById(R.id.position));
		if (positionTV!=null) positionTV.setText(rang);
		
		TextView equipeTV = (TextView)(row.findViewById(R.id.equipe));
		if (equipeTV!=null) equipeTV.setText(nom);
		
		TextView pointsTV = (TextView)(row.findViewById(R.id.points));
		if (pointsTV!=null) pointsTV.setText(points);
		
		TextView mjTV = (TextView)(row.findViewById(R.id.mj));
		if (mjTV!=null) mjTV.setText(mj);
		
		TextView m30TV = (TextView)(row.findViewById(R.id.m30));
		if (m30TV!=null) m30TV.setText(m30);
		
		TextView m32TV = (TextView)(row.findViewById(R.id.m32));
		if (m32TV!=null) m32TV.setText(m32);
		
		TextView m23TV = (TextView)(row.findViewById(R.id.m23));
		if (m23TV!=null) m23TV.setText(m23);
		
		TextView m03TV = (TextView)(row.findViewById(R.id.m03));
		if (m03TV!=null) m03TV.setText(m03);
		
		return row;
	}
	
	private class DownloadClassement extends AsyncTask<String, Void, ClassementCompetition> {

		@Override
		protected ClassementCompetition doInBackground(String... competitions) {
			 return(ClassementsHelper.getClassementCompetition(dataProvider, competitions[0]));
		}
		
		protected void onPostExecute(ClassementCompetition classement) {
	        if (classement != null) {
	        	tableLayout.removeAllViews();
	        	
		        // FIXME : hardcoded header should be a specific row layout with string values 
		        tableLayout.addView(populateNewRow("#","","Pts","MJ","+3","+2","+1","+0"));
		
		    	// Equipes
		    	String etatPrec = "";
		        for (ClassementEquipe equipe: classement.getEquipes()) {
		
		        	// Separator
		        	if (!etatPrec.equals(equipe.getEtat())) {
		        		tableLayout.addView(newSeparator());
		        		etatPrec = equipe.getEtat();
		        	}
		
		        	tableLayout.addView(populateNewRow(Integer.toString(equipe.getRang())+".",
		        			equipe.getEquipe(),Integer.toString(equipe.getPoint()),
		        			Integer.toString(equipe.getMj()),Integer.toString(equipe.getM30()+equipe.getM31()),
		        			Integer.toString(equipe.getM32()),Integer.toString(equipe.getM23()),
		        			Integer.toString(equipe.getM13()+equipe.getM03())));
		        }
	        }
			else {
				// FIXME : hardcoded
				Toast.makeText(ClassementsActivity.this, "Les informations ne sont pas disponibles pour le moment. Veuillez réessayer dans un instant.", Toast.LENGTH_LONG).show();
				ClassementsActivity.this.finish();
			}
		}
		
	}
	
}