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
package org.bamzone.provolleyfr.provider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.client.HttpClient;
import org.bamzone.provolleyfr.R;
import org.bamzone.provolleyfr.resultats.ResultatsActivity;

import android.content.res.Resources;
import android.util.Log;

public class LocalJSONProvider implements JSONProvider {
	
	private Resources resources;
	
	public LocalJSONProvider(Resources r) {
		resources = r;
	}
	
    public String getResultatsLigue(String competition) {
    	int id = R.raw.lam_resultats;
    	if(org.bamzone.provolleyfr.ProVolley.CODE_COMPETITION_LAF.equals(competition)) id = R.raw.laf_resultats;
    	if(org.bamzone.provolleyfr.ProVolley.CODE_COMPETITION_LBM.equals(competition)) id = R.raw.lbm_resultats;
   	
    	return readResource(id);
      }
    
    public String getResultatsCoupe(String competition) {
    	int id = R.raw.cnm_resultats;
    	if(org.bamzone.provolleyfr.ProVolley.CODE_COMPETITION_CNF.equals(competition)) id = R.raw.cnf_resultats;
   	
    	return readResource(id);
      }
    
	@Override
	public String getClassement(String competition) {
    	int id = R.raw.lam_classement;
    	if(org.bamzone.provolleyfr.ProVolley.CODE_COMPETITION_LAF.equals(competition)) id = R.raw.laf_classement;
    	if(org.bamzone.provolleyfr.ProVolley.CODE_COMPETITION_LBM.equals(competition)) id = R.raw.lbm_classement;
   	
    	return readResource(id);
	}
	
	@Override
	public String getLive() {
		int id = R.raw.live2;
		return readResource(id);
	}

	@Override
	public String getProgrammeTV() {
		int id = R.raw.tv;
		return readResource(id);
	}

	@Override
	public String getNews() {
		int id = R.raw.news;
		return readResource(id);
	}


	private String readResource(int id) {
		StringBuilder builder = new StringBuilder();

    			
        InputStream inputStream = resources.openRawResource(id);

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        try {
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
		} catch (IOException e) {
			Log.e(ResultatsActivity.class.getName(), "Can't read json file" , e);
		}
        
        return builder.toString();
	}



}
