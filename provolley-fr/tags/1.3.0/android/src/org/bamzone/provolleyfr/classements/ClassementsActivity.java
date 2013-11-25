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
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.view.LayoutInflater;
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
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(this);
        
        dataProvider = JSONProviderFactory.getDataProvider(prefs);
        tableLayout = (TableLayout)findViewById(R.id.TableLayout);
        inflater = (LayoutInflater) getApplicationContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
        TableRow loading = (TableRow)inflater.inflate(R.layout.classements_load, tableLayout, false);
        tableLayout.addView(loading);
        
        GetClassement task  = new GetClassement();
        task.execute(new String[] {competition});
    }

	private TableRow addNB(String texte, int couleur) {
    	TableRow nb = (TableRow)inflater.inflate(R.layout.classements_nb, tableLayout, false);
    	TextView nbTV = (TextView)(nb.findViewById(R.id.nb));
    	nbTV.setText(texte);
    	nbTV.setTextColor(couleur);
    	return nb;
    }
    
	private TableRow newSeparator() {
    	TableRow sep = (TableRow)inflater.inflate(R.layout.classements_sep, tableLayout, false);
		return sep;
    }
    
	private TableRow populateNewRow(String rang, String nom, String points, String mj, 
			String m30, String m32, String m23, String m03, int penalite, String etat) {
		TableRow row = (TableRow)inflater.inflate(R.layout.classements_row, tableLayout, false);
		
		TextView positionTV = (TextView)(row.findViewById(R.id.position));
		if (positionTV!=null) positionTV.setText(rang);
		
		if ("".equals(etat))etat=ProVolley.CLASSEMENT_AUTRES;
		int couleur = ProVolley.COULEURS_CLASSEMENT.get(etat); 
		TextView equipeTV = (TextView)(row.findViewById(R.id.equipe));
		if (equipeTV!=null) {
			if (penalite!=0) { // Ils font chier à la DNACG avec leur pénalités...
				nom = nom+" <small>(-"+penalite+")</small>";
				equipeTV.setText(Html.fromHtml(nom));
			}
			else {
				equipeTV.setText(nom);
			}
			equipeTV.setTextColor(couleur);
		}
		
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
	
	private void displayResults(ClassementCompetition result) {
        if (result != null) {
        	classement = result;
        	
        	tableLayout.removeAllViews();
        	
	        // FIXME : hardcoded header should be a specific row layout with string values 
	        tableLayout.addView(populateNewRow("#","","Pts","MJ","+3","+2","+1","+0",0,""));
	
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
	        			Integer.toString(equipe.getM13()+equipe.getM03()),
	        			equipe.getPen(),equipe.getEtat2()));
	        }
	        
	        boolean penalite=false;
	        boolean montee=false, po=false, po1=false, po2=false, po3=false, releg=false, vainq=false;
	        boolean monteeass=false, poass=false, po1ass=false, po2ass=false, po3ass=false, relegass=false, maintass=false;
	        for (ClassementEquipe equipe: classement.getEquipes()) {
	        	if (equipe.getPen()!=0) penalite=true;
	        	if (ProVolley.CLASSEMENT_MONTEE.equals(equipe.getEtat2())) montee=true;
	        	if (ProVolley.CLASSEMENT_QUALPO.equals(equipe.getEtat2())) po=true;
	        	if (ProVolley.CLASSEMENT_QUALPO1.equals(equipe.getEtat2())) po1=true;
	        	if (ProVolley.CLASSEMENT_QUALPO2.equals(equipe.getEtat2())) po2=true;
	        	if (ProVolley.CLASSEMENT_QUALPO3.equals(equipe.getEtat2())) po3=true;
	        	if (ProVolley.CLASSEMENT_RELEG.equals(equipe.getEtat2())) releg=true;
	        	if (ProVolley.CLASSEMENT_VAINQUEUR.equals(equipe.getEtat2())) vainq=true;

	        	if (ProVolley.CLASSEMENT_MONTEEASS.equals(equipe.getEtat2())) monteeass=true;
	        	if (ProVolley.CLASSEMENT_QUALPOASS.equals(equipe.getEtat2())) poass=true;
	        	if (ProVolley.CLASSEMENT_QUALPO1ASS.equals(equipe.getEtat2())) po1ass=true;
	        	if (ProVolley.CLASSEMENT_QUALPO2ASS.equals(equipe.getEtat2())) po2ass=true;
	        	if (ProVolley.CLASSEMENT_QUALPO3ASS.equals(equipe.getEtat2())) po3ass=true;
	        	if (ProVolley.CLASSEMENT_RELEGASS.equals(equipe.getEtat2())) relegass=true;
	        	if (ProVolley.CLASSEMENT_MAINTASS.equals(equipe.getEtat2())) maintass=true;
	        }
	        if (penalite || montee || po || po1 || po2 || po3 || releg || vainq || monteeass || poass || po1ass || po2ass || po3ass || relegass || maintass)
	        	tableLayout.addView(newSeparator());
	        if (penalite) tableLayout.addView(addNB("(-x) Pénalité sur décision DNACG",ProVolley.COULEUR_CLASSEMENT_NB));

	        if (monteeass) tableLayout.addView(addNB("Promotion en LAM assurée",ProVolley.COULEURS_CLASSEMENT.get(ProVolley.CLASSEMENT_VAINQUEUR)));
	        if (poass) tableLayout.addView(addNB("Qualification play-offs assurée",ProVolley.COULEURS_CLASSEMENT.get(ProVolley.CLASSEMENT_QUALPOASS)));
	        if (po1ass) tableLayout.addView(addNB("Qualification play-offs assurée",ProVolley.COULEURS_CLASSEMENT.get(ProVolley.CLASSEMENT_QUALPO1ASS)));
	        if (po2ass) tableLayout.addView(addNB("Qualification play-offs de classement assurée",ProVolley.COULEURS_CLASSEMENT.get(ProVolley.CLASSEMENT_QUALPO2ASS)));
	        if (po3ass) tableLayout.addView(addNB("Qualification play-offs assurée",ProVolley.COULEURS_CLASSEMENT.get(ProVolley.CLASSEMENT_QUALPO3ASS)));
	        if (maintass) tableLayout.addView(addNB("Maintien assuré",ProVolley.COULEURS_CLASSEMENT.get(ProVolley.CLASSEMENT_MAINTASS)));
	        if (relegass) tableLayout.addView(addNB("Relégation assurée",ProVolley.COULEURS_CLASSEMENT.get(ProVolley.CLASSEMENT_RELEGASS)));

	        if (montee) tableLayout.addView(addNB("Promu en LAM",ProVolley.COULEURS_CLASSEMENT.get(ProVolley.CLASSEMENT_MONTEE)));
	        if (po) tableLayout.addView(addNB("Qualifié pour les play-offs",ProVolley.COULEURS_CLASSEMENT.get(ProVolley.CLASSEMENT_QUALPO)));
	        if (po1) tableLayout.addView(addNB("Qualifié pour les play-offs",ProVolley.COULEURS_CLASSEMENT.get(ProVolley.CLASSEMENT_QUALPO1)));
	        if (po2) tableLayout.addView(addNB("Qualifié pour les play-offs de classement",ProVolley.COULEURS_CLASSEMENT.get(ProVolley.CLASSEMENT_QUALPO2)));
	        if (po3) tableLayout.addView(addNB("Qualifié pour les play-offs",ProVolley.COULEURS_CLASSEMENT.get(ProVolley.CLASSEMENT_QUALPO3)));
	        if (releg) tableLayout.addView(addNB("Relégué",ProVolley.COULEURS_CLASSEMENT.get(ProVolley.CLASSEMENT_RELEG)));
	        if (vainq) tableLayout.addView(addNB("Champion",ProVolley.COULEURS_CLASSEMENT.get(ProVolley.CLASSEMENT_VAINQUEUR)));
        }
	}

	private class GetClassement extends AsyncTask<String, Void, ClassementCompetition> {
		
		String competition;
		
		@Override
		protected ClassementCompetition doInBackground(String... competitions) {
			competition=competitions[0];
			return (ClassementsHelper.getClassementFromCache(competition));
		}

		protected void onPostExecute(ClassementCompetition result) {
	        displayResults(result);
	        
	        DownloadClassement task  = new DownloadClassement();
	        task.execute(new String[] {competition});

		}
	}

	private class DownloadClassement extends AsyncTask<String, Void, ClassementCompetition> {

		@Override
		protected ClassementCompetition doInBackground(String... competitions) {
			 return(ClassementsHelper.getClassementFromServer(dataProvider, competitions[0]));
		}
		
		protected void onPostExecute(ClassementCompetition result) {
			displayResults(result);

			if (classement==null) {
				// FIXME : hardcoded
				Toast.makeText(ClassementsActivity.this, "Les informations ne sont pas disponibles pour le moment. Veuillez réessayer dans un instant.", Toast.LENGTH_LONG).show();
				ClassementsActivity.this.finish();
			}
		}
		
	}
	
}