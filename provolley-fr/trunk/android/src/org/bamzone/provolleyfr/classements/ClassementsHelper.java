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

import org.bamzone.provolleyfr.cache.ProVolleyCacheManager;
import org.bamzone.provolleyfr.data.ClassementCompetition;
import org.bamzone.provolleyfr.data.ClassementEquipe;
import org.bamzone.provolleyfr.provider.JSONProvider;
import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

public class ClassementsHelper {
	
	private static final String CACHE_KEY="CLASSEMENT_";
	
	private static ClassementCompetition getClassementFromJson(String competition, String json) {
		
    	try {
			JSONObject classement = new JSONObject(json).getJSONObject("classement");
			String saison = classement.getString("saison");
			
			Log.d(ClassementsActivity.class.getName(), saison );
			ClassementCompetition classementCompetition  = new ClassementCompetition(saison);
			
			JSONArray equipes = classement.getJSONArray("equipes");
			for (int i = 0; i < equipes.length(); i++) {
		        JSONObject equipe = equipes.getJSONObject(i);

		        int rang = equipe.getInt("rang");
		        String code = equipe.getString("code");
		        String nom = equipe.getString("equipe");
		        int points = equipe.getInt("points");
		        int mj = equipe.getInt("mj");
		        int mg = equipe.getInt("mg");
		        int mp = equipe.getInt("mp");
		        int m30 = equipe.getInt("m30");
		        int m31 = equipe.getInt("m31");
		        int m32 = equipe.getInt("m32");
		        int m23 = equipe.getInt("m23");
		        int m13 = equipe.getInt("m13");
		        int m03 = equipe.getInt("m03");
		        int sp = equipe.getInt("sp");
		        int sc = equipe.getInt("sc");
		        String rs = equipe.getString("rs");
		        int pp = equipe.getInt("pp");
		        int pc = equipe.getInt("pc");
		        String rp = equipe.getString("rp");
		        String etat = equipe.getString("etat");
		        String etat2 = equipe.getString("etat2");
		        int pen = equipe.getInt("pen");
		        
		        ClassementEquipe classementEquipe = new ClassementEquipe(rang,code,nom,points,mj,mg,mp,m30,m31,m32,m23,m13,m03,sp,sc,rs,pp,pc,rp,etat,etat2,pen);
		        classementCompetition.getEquipes().add(classementEquipe);
				
		        //Log.d(ClassementsActivity.class.getName(),rang+"/"+nom+"/"+pen+"/"+assure);
		      }
			return classementCompetition;
		} catch (Exception e) {
			Log.e(ClassementsActivity.class.getName(), "Can't read parse json file" , e);
		}
    	return null;
    }

	public static ClassementCompetition getClassementFromServer(JSONProvider dp, String competition) {
		String json = dp.getClassement(competition);
		if(json==null) return null;

		ClassementCompetition classement = getClassementFromJson(competition, json);
		ProVolleyCacheManager.getInstance().getCacheDataSource().insertCachedItem(CACHE_KEY+competition, json);
		return classement;
    }
    
	public static ClassementCompetition getClassementFromCache(String competition) {
		String json = ProVolleyCacheManager.getInstance().getCacheDataSource().getCachedItem(CACHE_KEY+competition);
		if(json==null) return null;

		return getClassementFromJson(competition, json);
    }
}
