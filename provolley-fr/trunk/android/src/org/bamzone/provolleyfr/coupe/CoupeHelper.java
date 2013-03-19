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
import java.util.List;

import org.bamzone.provolleyfr.ProVolley;
import org.bamzone.provolleyfr.data.ResultatsJournee;
import org.bamzone.provolleyfr.data.ResultatsMatch;
import org.bamzone.provolleyfr.data.ResultatsSaison;
import org.bamzone.provolleyfr.provider.JSONProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.widget.TextView;
import java.io.BufferedReader;

public class CoupeHelper {
	
	private static Resources resources;
	
	public static ResultatsSaison getResultatsSaison(JSONProvider dp, String competition) {
    	try {
			JSONObject resultats = new JSONObject(dp.getResultatsCoupe(competition)).getJSONObject("resultats");
			String saison = resultats.getString("saison");
			int minJournee = resultats.getInt("minJournee");
			int maxJournee = resultats.getInt("maxJournee");
			int currentJournee = resultats.getInt("currentJournee");

			Log.d(CoupeActivity.class.getName(), saison +"/"+ minJournee +"/"+ maxJournee +"/"+ currentJournee);
			ResultatsSaison resultatsSaison = new ResultatsSaison(saison,minJournee,maxJournee,currentJournee);
			
			JSONArray journees = resultats.getJSONArray("journees");
			for (int i = 0; i < journees.length(); i++) {
		        JSONObject journee = journees.getJSONObject(i);

		        ResultatsJournee resultatsJournee = new ResultatsJournee(journee.getInt("numero"),journee.getString("titre"));
				resultatsSaison.getJournees().add(resultatsJournee);
				
				JSONArray matchs = journee.getJSONArray("matchs");
				for (int j = 0; j < matchs.length(); j++) {
					JSONObject match = matchs.getJSONObject(j);
			        String equipeDomicile = match.getString("equipeDomicile");
			        String equipeExterieur = match.getString("equipeExterieur");
			        String resultat = match.getString("resultat");
			        String score = match.getString("score");
			        String classementDomicile = match.getString("rangDomicile");
			        String classementExterieur = match.getString("rangExterieur");
			        String victoire = match.getString("victoire");
			        
			        ResultatsMatch resultatsMatch = new ResultatsMatch(saison, competition, equipeDomicile, equipeExterieur, resultat, score, classementDomicile, classementExterieur, victoire);
			        resultatsJournee.getMatchs().add(resultatsMatch);
			        //Log.d(CoupeActivity.class.getName(),resultatsJournee.getNumJournee()+"/"+equipeDomicile+"/"+equipeExterieur+"/"+resultat+"/"+score+"/"+victoire);
				}
		      }
			return resultatsSaison;
		} catch (Exception e) {
			Log.e(CoupeActivity.class.getName(), "Can't read parse json file" , e);
		}
    	return null;
    }
    
	public static boolean isVictoireDomicile(ResultatsMatch match) {
	    if(ProVolley.RESULTAT_VICTOIRE_DOMICILE.equals(match.getVictoire())) return true;
		return false;
	}
	public static boolean isVictoireExterieur(ResultatsMatch match) {
		if(ProVolley.RESULTAT_VICTOIRE_EXTERIEUR.equals(match.getVictoire())) return true;
		return false;
	}
	public static boolean isMatchTermine(ResultatsMatch match) {
		if(ProVolley.RESULTAT_VICTOIRE_EXTERIEUR.equals(match.getVictoire())) return true;
	    if(ProVolley.RESULTAT_VICTOIRE_DOMICILE.equals(match.getVictoire())) return true;
		return false;
	}

}
