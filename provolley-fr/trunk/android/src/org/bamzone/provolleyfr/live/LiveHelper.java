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

import org.bamzone.provolleyfr.ProVolley;
import org.bamzone.provolleyfr.cache.ProVolleyCacheManager;
import org.bamzone.provolleyfr.data.LiveMatch;
import org.bamzone.provolleyfr.data.LiveResultats;
import org.bamzone.provolleyfr.provider.JSONProvider;
import org.bamzone.provolleyfr.resultats.ResultatsActivity;
import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

public class LiveHelper {
	
	private final static String CACHE_KEY = "LIVE"; 
	
	public static LiveResultats getResultatsLiveFromJSON(String json) {
    	try {
			JSONObject live = new JSONObject(json).getJSONObject("live");

			String heureMaj = live.getString("maj");
			int nblive = live.getInt("nblive");
					
			LiveResultats resultatsLive = new LiveResultats(heureMaj, nblive);
			
			JSONArray matchs = live.getJSONArray("matchs");
			for (int j = 0; j < matchs.length(); j++) {
					JSONObject match = matchs.getJSONObject(j);
			        String equipeDomicile = match.getString("equipeDomicile");
			        String equipeExterieur = match.getString("equipeExterieur");
			        String resultat = match.getString("resultat");
			        String score = match.getString("score");
			        String service = match.getString("service");
			        String saison = match.getString("saison");
			        String competition = match.getString("competition");
			        String etat = match.getString("etat");
			        String classementDomicile = match.getString("rangDomicile");
			        String classementExterieur = match.getString("rangExterieur");
			        String victoire = match.getString("victoire");
			        String codeDomicile = match.getString("codeDomicile");
			        String codeExterieur = match.getString("codeExterieur");
			        String codeMatch = match.getString("code");
			        //Log.d(LiveHelper.class.getName(), equipeDomicile+"/"+equipeExterieur+"/"+resultat+"/"+score+"/"+service+"/"+competition+"/"+etat+"/"+victoire);

			        LiveMatch liveMatch = new LiveMatch(saison,competition,codeMatch,
			        		codeDomicile,equipeDomicile,classementDomicile,
			        		codeExterieur,equipeExterieur,classementExterieur,
			        		resultat,score,service,etat,victoire);
			        resultatsLive.getMatchs().add(liveMatch);
				}
			return resultatsLive;
		} catch (Exception e) {
			Log.e(ResultatsActivity.class.getName(), "Can't read parse json file" , e);
		}
    	return null;
    }
    
	public static boolean isMatchEnCours(LiveMatch match) {
		if(ProVolley.LIVE_MATCH_ENCOURS.equals(match.getEtat())) return true;
		return false;
	}
	public static boolean isMatchTermine(LiveMatch match) {
		if(ProVolley.LIVE_MATCH_TERMINE.equals(match.getEtat())) return true;
		return false;
	}
	public static boolean isMatchValide(LiveMatch match) {
		if(ProVolley.LIVE_MATCH_VALIDE.equals(match.getEtat())) return true;
		return false;
	}
	public static boolean isMatchAVenir(LiveMatch match) {
		if(ProVolley.LIVE_MATCH_AVENIR.equals(match.getEtat())) return true;
		return false;
	}
	
	public static boolean isServiceDomicile(LiveMatch match) {
	    if(ProVolley.LIVE_SERVICE_DOMICILE.equals(match.getService())) return true;
		return false;
	}
	public static boolean isServiceExterieur(LiveMatch match) {
		if(ProVolley.LIVE_SERVICE_EXTERIEUR.equals(match.getService())) return true;
		return false;
	}
	public static boolean isVictoireDomicile(LiveMatch match) {
	    if(ProVolley.LIVE_VICTOIRE_DOMICILE.equals(match.getVictoire())) return true;
		return false;
	}
	public static boolean isVictoireExterieur(LiveMatch match) {
		if(ProVolley.LIVE_VICTOIRE_EXTERIEUR.equals(match.getVictoire())) return true;
		return false;
	}
	
	public static LiveResultats getResultatsLiveFromServer(JSONProvider dp) {
		String json = dp.getLive();
		if(json==null) return null;

		LiveResultats liveResults = getResultatsLiveFromJSON(json);
		ProVolleyCacheManager.getInstance().getChosenCache().insertCachedItem(CACHE_KEY, json);
		return liveResults;
    }
    
	public static LiveResultats getResultatsLiveFromCache() {
		String json = ProVolleyCacheManager.getInstance().getChosenCache().getCachedItem(CACHE_KEY);
		if(json==null) return null;

		return getResultatsLiveFromJSON(json);
    }
}
