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
	private TableRow populateHeader() {
		//("#","","Pts","MJ","+3","+2","+1","+0",0,"")
		TableRow row = (TableRow)inflater.inflate(R.layout.classements_row, tableLayout, false);

		TextView positionTV = (TextView)(row.findViewById(R.id.position));
		TextView mjTV = (TextView)(row.findViewById(R.id.mj));
		TextView pointsTV = (TextView)(row.findViewById(R.id.points));
		TextView m30TV = (TextView)(row.findViewById(R.id.m30));
		TextView m31TV = (TextView)(row.findViewById(R.id.m31));
		TextView m32TV = (TextView)(row.findViewById(R.id.m32));
		TextView m23TV = (TextView)(row.findViewById(R.id.m23));
		TextView m13TV = (TextView)(row.findViewById(R.id.m13));
		TextView m03TV = (TextView)(row.findViewById(R.id.m03));
		TextView m310TV = (TextView)(row.findViewById(R.id.m310));
		TextView m013TV = (TextView)(row.findViewById(R.id.m013));
		TextView rsTV = (TextView)(row.findViewById(R.id.rs));
		TextView rpTV = (TextView)(row.findViewById(R.id.rp));
		
		if (positionTV!=null) positionTV.setText("#");
		if (pointsTV!=null) pointsTV.setText("Pts");
		if (mjTV!=null) mjTV.setText("MJ");

		if (m30TV!=null) {
			// Si m30TV est non nul, -> format paysage
			if (m30TV!=null) m30TV.setText("3-0");
			if (m31TV!=null) m31TV.setText("3-1");
			if (m32TV!=null) m32TV.setText("3-2");
			if (m23TV!=null) m23TV.setText("2-3");
			if (m13TV!=null) m13TV.setText("1-3");
			if (m03TV!=null) m03TV.setText("0-3");
		}
		else { 
			// format portrait
			if (m310TV!=null) m310TV.setText("+3");
			if (m32TV!=null) m32TV.setText("+2");
			if (m23TV!=null) m23TV.setText("+1");
			if (m013TV!=null) m013TV.setText("+0");
		}
		if (rsTV!=null) rsTV.setText("RatioS");
		if (rpTV!=null) rpTV.setText("RatioP");
		return row;
	}
	
	private String formatRatio(String in) {
		Float inAsFloat = null;
		try { inAsFloat = Float.valueOf(in); } catch (NumberFormatException nfe) {return in;};

		String out = String.format("%.5f",inAsFloat.floatValue());
		return out.substring(0, 5);
	}
	
	private TableRow populateNewRow(int rang, String nom, int points, int mj, 
			int m30, int m31, int m32, int m23, int m13, int m03, 
			int penalite, String rs, String rp, String etat) {
		TableRow row = (TableRow)inflater.inflate(R.layout.classements_row, tableLayout, false);
		
		TextView positionTV = (TextView)(row.findViewById(R.id.position));
		TextView equipeTV = (TextView)(row.findViewById(R.id.equipe));
		TextView mjTV = (TextView)(row.findViewById(R.id.mj));
		TextView pointsTV = (TextView)(row.findViewById(R.id.points));
		TextView m30TV = (TextView)(row.findViewById(R.id.m30));
		TextView m31TV = (TextView)(row.findViewById(R.id.m31));
		TextView m32TV = (TextView)(row.findViewById(R.id.m32));
		TextView m23TV = (TextView)(row.findViewById(R.id.m23));
		TextView m13TV = (TextView)(row.findViewById(R.id.m13));
		TextView m03TV = (TextView)(row.findViewById(R.id.m03));
		TextView m310TV = (TextView)(row.findViewById(R.id.m310));
		TextView m013TV = (TextView)(row.findViewById(R.id.m013));
		TextView rsTV = (TextView)(row.findViewById(R.id.rs));
		TextView rpTV = (TextView)(row.findViewById(R.id.rp));

		if (positionTV!=null) positionTV.setText(Integer.toString(rang)+".");
		
		if ("".equals(etat))etat=ProVolley.CLASSEMENT_AUTRES;
		int couleur = ProVolley.COULEURS_CLASSEMENT.get(etat); 
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
		
		if (pointsTV!=null) pointsTV.setText(Integer.toString(points));
		if (mjTV!=null) mjTV.setText(Integer.toString(mj));
		if (m30TV!=null) m30TV.setText(Integer.toString(m30));
		if (m31TV!=null) m31TV.setText(Integer.toString(m31));
		if (m310TV!=null) m310TV.setText(Integer.toString(m31+m30));
		if (m32TV!=null) m32TV.setText(Integer.toString(m32));
		if (m23TV!=null) m23TV.setText(Integer.toString(m23));
		if (m13TV!=null) m13TV.setText(Integer.toString(m13));
		if (m03TV!=null) m03TV.setText(Integer.toString(m03));
		if (m013TV!=null) m013TV.setText(Integer.toString(m13+m03));
		if (rsTV!=null) {
			rsTV.setText(formatRatio(rs));		}
		if (rpTV!=null) {
			rpTV.setText(formatRatio(rp));
		}
		
		return row;
	}
	
	private void displayResults(ClassementCompetition result) {
        if (result != null) {
        	classement = result;
        	
        	tableLayout.removeAllViews();
        	
	        // FIXME : hardcoded header should be a specific row layout with string values 
	        tableLayout.addView(populateHeader());
	
	    	// Equipes
	    	String etatPrec = "";
	        for (ClassementEquipe equipe: classement.getEquipes()) {
	
	        	// Separator
	        	if (!etatPrec.equals(equipe.getEtat())) {
	        		tableLayout.addView(newSeparator());
	        		etatPrec = equipe.getEtat();
	        	}
	
	        	tableLayout.addView(populateNewRow((equipe.getRang()),
	        			equipe.getEquipe(),(equipe.getPoint()),
	        			(equipe.getMj()),(equipe.getM30()),(equipe.getM31()),
	        			(equipe.getM32()),(equipe.getM23()),
	        			(equipe.getM13()),(equipe.getM03()),
	        			equipe.getPen(),equipe.getRs(), equipe.getRp(),
	        			equipe.getEtat2()));
	        }
	        
	        boolean penalite=false;
	        boolean montee=false, po=false, releg=false, vainq=false, monteevq=false, monteepo=false;
	        boolean monteeass=false, monteepoass=false, poass=false, relegass=false, maintass=false;
	        for (ClassementEquipe equipe: classement.getEquipes()) {
	        	if (equipe.getPen()!=0) penalite=true;
	        	if (ProVolley.CLASSEMENT_MONTEE.equals(equipe.getEtat2())) montee=true;
	        	if (ProVolley.CLASSEMENT_QUALPO.equals(equipe.getEtat2())) po=true;
	        	if (ProVolley.CLASSEMENT_MONTEEPO.equals(equipe.getEtat2())) monteepo=true;
	        	if (ProVolley.CLASSEMENT_MONTEEVQ.equals(equipe.getEtat2())) monteevq=true;
	        	if (ProVolley.CLASSEMENT_RELEG.equals(equipe.getEtat2())) releg=true;
	        	if (ProVolley.CLASSEMENT_VAINQUEUR.equals(equipe.getEtat2())) vainq=true;

	        	if (ProVolley.CLASSEMENT_MONTEEASS.equals(equipe.getEtat2())) monteeass=true;
	        	if (ProVolley.CLASSEMENT_MONTEEPOASS.equals(equipe.getEtat2())) monteepoass=true;
	        	if (ProVolley.CLASSEMENT_QUALPOASS.equals(equipe.getEtat2())) poass=true;
	        	if (ProVolley.CLASSEMENT_RELEGASS.equals(equipe.getEtat2())) relegass=true;
	        	if (ProVolley.CLASSEMENT_MAINTASS.equals(equipe.getEtat2())) maintass=true;
	        }
	        if (penalite || montee || po || monteepo || monteevq || releg || vainq || monteeass || poass || relegass || maintass)
	        	tableLayout.addView(newSeparator());
	        if (penalite) tableLayout.addView(addNB("(-x) Pénalité sur décision DNACG",ProVolley.COULEUR_CLASSEMENT_NB));

	        if (monteeass) tableLayout.addView(addNB("Qualif play-offs & Promotion en LAM assurées",ProVolley.COULEURS_CLASSEMENT.get(ProVolley.CLASSEMENT_MONTEEASS)));
	        if (monteepoass) tableLayout.addView(addNB("Qualif play-offs & Promotion en LAM assurées",ProVolley.COULEURS_CLASSEMENT.get(ProVolley.CLASSEMENT_MONTEEPOASS)));
	        if (poass) tableLayout.addView(addNB("Qualification play-offs assurée",ProVolley.COULEURS_CLASSEMENT.get(ProVolley.CLASSEMENT_QUALPOASS)));
	        if (maintass) tableLayout.addView(addNB("Maintien assuré",ProVolley.COULEURS_CLASSEMENT.get(ProVolley.CLASSEMENT_MAINTASS)));
	        if (relegass) tableLayout.addView(addNB("Relégation assurée",ProVolley.COULEURS_CLASSEMENT.get(ProVolley.CLASSEMENT_RELEGASS)));

	        if (vainq) tableLayout.addView(addNB("Champion",ProVolley.COULEURS_CLASSEMENT.get(ProVolley.CLASSEMENT_VAINQUEUR)));
	        if (monteevq) tableLayout.addView(addNB("Champion",ProVolley.COULEURS_CLASSEMENT.get(ProVolley.CLASSEMENT_MONTEEVQ)));
	        if (montee) tableLayout.addView(addNB("Promu en LAM",ProVolley.COULEURS_CLASSEMENT.get(ProVolley.CLASSEMENT_MONTEE)));
	        if (monteepo) tableLayout.addView(addNB("Promu en LAM & Qualifié pour les play-offs",ProVolley.COULEURS_CLASSEMENT.get(ProVolley.CLASSEMENT_MONTEEPO)));
	        if (po) tableLayout.addView(addNB("Qualifié pour les play-offs",ProVolley.COULEURS_CLASSEMENT.get(ProVolley.CLASSEMENT_QUALPO)));
	        if (releg) tableLayout.addView(addNB("Relégué",ProVolley.COULEURS_CLASSEMENT.get(ProVolley.CLASSEMENT_RELEG)));
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