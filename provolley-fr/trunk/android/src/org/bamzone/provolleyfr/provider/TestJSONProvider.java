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
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.bamzone.provolleyfr.ProVolley;
import org.bamzone.provolleyfr.R;

import android.util.Log;

public class TestJSONProvider implements JSONProvider {
	
	private static int CONNECT_TIMEOUT=5000; 
	private static int READ_TIMEOUT=20000; 
	
	protected String server;
	protected String json;

	TestJSONProvider () {
		server = ProVolley.SERVER_URL_TEST;
		json = ProVolley.JSON_DIR_TEST;
	}
	
	public String getResultatsLigue(String competition) {
		String file = "LAM_resultats.json";
		if (org.bamzone.provolleyfr.ProVolley.CODE_COMPETITION_LAF
				.equals(competition))
			file = "LAF_resultats.json";
		if (org.bamzone.provolleyfr.ProVolley.CODE_COMPETITION_LBM
				.equals(competition))
			file = "LBM_resultats.json";

		return loadUrl(server + json + file);
	}

	public String getResultatsCoupe(String competition) {
		String file = "CNM_resultats.json";
		if (org.bamzone.provolleyfr.ProVolley.CODE_COMPETITION_CNF
				.equals(competition))
			file = "CNF_resultats.json";

		return loadUrl(server + json + file);
	}

	@Override
	public String getClassement(String competition) {
		String file = "LAM_classement.json";
		if (org.bamzone.provolleyfr.ProVolley.CODE_COMPETITION_LAF
				.equals(competition))
			file = "LAF_classement.json";
		if (org.bamzone.provolleyfr.ProVolley.CODE_COMPETITION_LBM
				.equals(competition))
			file = "LBM_classement.json";

		return loadUrl(server + json + file);
	}

	@Override
	public String getLive() {
		String file = "live2.json";
		return loadUrl(server + json + file);
	}

	@Override
	public String getProgrammeTV() {
		String file = "tv.json";
		return loadUrl(server + json + file);
	}
	
	@Override
	public String getNews() {
		String file = "news.json";
		return loadUrl(server + json + file);
	}

	@Override
	public String getSaison() {
		String file = "saison.json";
		return loadUrl(server + json + file);
	}


	private String loadUrl(String s) {
		Log.d(this.getClass().getName(), "Loading "+s);
		
		StringBuilder builder = new StringBuilder();
		try {
			//Thread.sleep(5000);
		
			URL url = new URL(s);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setConnectTimeout(CONNECT_TIMEOUT);
			con.setReadTimeout(READ_TIMEOUT);
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new InputStreamReader(
						con.getInputStream()),8192);
				String line = "";
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return builder.toString();
	}


}
