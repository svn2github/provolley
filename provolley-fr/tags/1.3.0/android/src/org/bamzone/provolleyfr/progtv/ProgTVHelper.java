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

import org.bamzone.provolleyfr.cache.ProVolleyCacheManager;
import org.bamzone.provolleyfr.data.TVEmission;
import org.bamzone.provolleyfr.data.TVProgramme;
import org.bamzone.provolleyfr.provider.JSONProvider;
import org.bamzone.provolleyfr.resultats.ResultatsActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class ProgTVHelper {

	private final static String CACHE_KEY = "PROGTV";

	private static TVProgramme getProgrammeTVFromJson(String json) {

		try {
			TVProgramme progTV = new TVProgramme();

			JSONArray programme = new JSONObject(json).getJSONArray("progstv");
			for (int j = 0; j < programme.length(); j++) {
				JSONObject emission = programme.getJSONObject(j);
				String date = emission.getString("date");
				String heure = emission.getString("heure");
				String type = emission.getString("type");
				String idchaine = emission.getString("idchaine");
				String nomchaine = emission.getString("nomchaine");
				String url = emission.getString("url");
				String logo = emission.getString("logo");
				String duree = emission.getString("duree");
				String diffusion = emission.getString("diffusion");
				String titre = emission.getString("titre");
				String description = emission.getString("description");

				// Log.d(ProgTVHelper.class.getName(),
				// date+"/"+heure+"/"+type+"/"+idchaine+"/"+nomchaine+"/"+url+"/"+logo+"/"+duree+"/"+diffusion+"/"+titre+"/"+description);

				TVEmission emissionTV = new TVEmission(date, heure, type,
						idchaine, nomchaine, url, logo, duree, diffusion,
						titre, description);
				progTV.getEmissions().add(emissionTV);
			}
			return progTV;
		} catch (JSONException je) {
			Log.e(ResultatsActivity.class.getName(), "Can't parse json file : "
					+ json, je);
		}
		return null;
	}

	public static TVProgramme getProgrammeTVFromServer(JSONProvider dp) {
		String json = dp.getProgrammeTV();
		if (json == null)
			return null;
		// Log.d(ProgTVHelper.class.getName(), "JSON: "+json);

		TVProgramme progTV = getProgrammeTVFromJson(json);
		ProVolleyCacheManager.getInstance().getChosenCache()
				.insertCachedItem(CACHE_KEY, json);
		return progTV;
	}

	public static TVProgramme getProgrammeTVFromCache() {
		String json = ProVolleyCacheManager.getInstance().getChosenCache()
				.getCachedItem(CACHE_KEY);
		if (json == null)
			return null;

		return getProgrammeTVFromJson(json);
	}

}
