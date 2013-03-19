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
package org.bamzone.provolleyfr.news;

import org.bamzone.provolleyfr.data.NewsItem;
import org.bamzone.provolleyfr.data.NewsProVolley;
import org.bamzone.provolleyfr.data.TVEmission;
import org.bamzone.provolleyfr.data.LiveMatch;
import org.bamzone.provolleyfr.data.TVProgramme;
import org.bamzone.provolleyfr.data.LiveResultats;
import org.bamzone.provolleyfr.provider.JSONProvider;
import org.bamzone.provolleyfr.resultats.ResultatsActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class NewsHelper {
	
	
	public static NewsProVolley getNewsProVolley(JSONProvider dp) {
    	try {
    		NewsProVolley news = new NewsProVolley();
    		
			JSONArray programme = new JSONObject(dp.getNews()).getJSONArray("news");
			for (int j = 0; j < programme.length(); j++) {
					JSONObject emission = programme.getJSONObject(j);
			        String date = emission.getString("date");
			        String heure = emission.getString("heure");
			        String categorie = emission.getString("categorie");
			        String logo = emission.getString("logo");
			        String titre = emission.getString("titre");
			        String description = emission.getString("description");

			        //Log.d(NewsHelper.class.getName(), date+"/"+heure+"/"+categorie+"/"+logo+"/"+titre+"/"+description);

			        NewsItem newsItem = new NewsItem(date, heure, categorie, logo, titre, description);
			        news.getNewsItems().add(newsItem);
				}
			return news;
		} catch (Exception e) {
			Log.e(ResultatsActivity.class.getName(), "Can't read parse json file" , e);
		}
    	return null;
    }
    

}
