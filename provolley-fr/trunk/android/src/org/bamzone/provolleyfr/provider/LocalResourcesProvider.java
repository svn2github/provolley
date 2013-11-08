/*
	ProVolley-fr
	Copyright (C) 2012-2013 Christophe Bothamy
	
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

import java.util.HashMap;
import java.util.Map;

import org.bamzone.provolleyfr.ProVolley;
import org.bamzone.provolleyfr.R;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;

public class LocalResourcesProvider implements ResourcesProvider {
	
	private Resources resources;
	
	public LocalResourcesProvider(Resources r) {
		resources = r;
	}
	
	@Override
	public Drawable getLogoChaine(String CodeChaine) {
		
		if(ProVolley.CODE_CHAINE_BWIN.equals(CodeChaine)) {
			return resources.getDrawable(R.drawable.chaine_bwin);
		}
		if(ProVolley.CODE_CHAINE_TVRENNES35.equals(CodeChaine)) {
			return resources.getDrawable(R.drawable.chaine_tvrennes35);
		}
		if(ProVolley.CODE_CHAINE_CORSPORT.equals(CodeChaine)) {
			return resources.getDrawable(R.drawable.chaine_corsport);
		}
		if(ProVolley.CODE_CHAINE_LAOLATV.equals(CodeChaine)) {
			return resources.getDrawable(R.drawable.chaine_laolatv);
		}
		if(ProVolley.CODE_CHAINE_MCS.equals(CodeChaine)) {
			return resources.getDrawable(R.drawable.chaine_mcs);
		}
		if(ProVolley.CODE_CHAINE_DAILYMOTION.equals(CodeChaine)) {
			return resources.getDrawable(R.drawable.chaine_dailymotion);
		}
		
		// default
		return resources.getDrawable(R.drawable.chaine_provolley);
	}

	public Drawable getLogoNews(String CodeNews) {
		
		if (ProVolley.CODE_NEWS_CDF.equals(CodeNews)) {
			return resources.getDrawable(R.drawable.news_cdf);
		}
		if (ProVolley.CODE_NEWS_CNM.equals(CodeNews)) {
			return resources.getDrawable(R.drawable.news_cnm);
		}
		if (ProVolley.CODE_NEWS_CNF.equals(CodeNews)) {
			return resources.getDrawable(R.drawable.news_cnf);
		}
		if (ProVolley.CODE_NEWS_CEV.equals(CodeNews)) {
			return resources.getDrawable(R.drawable.news_cev);
		}
		if (ProVolley.CODE_NEWS_CLM.equals(CodeNews)) {
			return resources.getDrawable(R.drawable.news_clm);
		}
		if (ProVolley.CODE_NEWS_CLW.equals(CodeNews)) {
			return resources.getDrawable(R.drawable.news_clw);
		}
		if (ProVolley.CODE_NEWS_LAF.equals(CodeNews)) {
			return resources.getDrawable(R.drawable.news_laf);
		}
		if (ProVolley.CODE_NEWS_LAM.equals(CodeNews)) {
			return resources.getDrawable(R.drawable.news_lam);
		}
		if (ProVolley.CODE_NEWS_LBM.equals(CodeNews)) {
			return resources.getDrawable(R.drawable.news_lbm);
		}
		if (ProVolley.CODE_NEWS_PROVOLLEYFR.equals(CodeNews)) {
			return resources.getDrawable(R.drawable.news_provolley);
		}
		if (ProVolley.CODE_NEWS_LNV.equals(CodeNews)) {
			return resources.getDrawable(R.drawable.news_lnv);
		}
		return resources.getDrawable(R.drawable.news_provolley);
	}
	
	private static final Map<String , Integer> MAILLOTS = new HashMap<String , Integer>() {{
		put("PV", null);
	}};

	public Drawable getMaillot(String codeEquipe, String lieu) {
		
		String key = codeEquipe+lieu;
		
		//Log.d(this.getClass().getName(),"Looking for : "+key);
		
		if (MAILLOTS.containsKey(key)) {
			return resources.getDrawable(MAILLOTS.get(key));
		}
		
		return resources.getDrawable(R.drawable.maillot_autre);
	}
}
