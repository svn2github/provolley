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
		put("1213_LAF_Albi_domicile", R.drawable.maillot_1213_laf_albi_domicile);
		put("1213_LAF_Albi_exterieur", R.drawable.maillot_1213_laf_albi_exterieur);
		put("1213_LAF_Beziers_domicile", R.drawable.maillot_1213_laf_beziers_domicile);
		put("1213_LAF_Beziers_exterieur", R.drawable.maillot_1213_laf_beziers_exterieur);
		put("1213_LAF_Calais_domicile", R.drawable.maillot_1213_laf_calais_domicile);
		put("1213_LAF_Calais_exterieur", R.drawable.maillot_1213_laf_calais_exterieur);
		put("1213_LAF_Cannes_domicile", R.drawable.maillot_1213_laf_cannes_domicile);
		put("1213_LAF_Cannes_exterieur", R.drawable.maillot_1213_laf_cannes_exterieur);
		put("1213_LAF_Evreux_domicile", R.drawable.maillot_1213_laf_evreux_domicile);
		put("1213_LAF_Evreux_exterieur", R.drawable.maillot_1213_laf_evreux_exterieur);
		put("1213_LAF_Hainaut_domicile", R.drawable.maillot_1213_laf_hainaut_domicile);
		put("1213_LAF_Hainaut_exterieur", R.drawable.maillot_1213_laf_hainaut_exterieur);
		put("1213_LAF_Istres_domicile", R.drawable.maillot_1213_laf_istres_domicile);
		put("1213_LAF_Istres_exterieur", R.drawable.maillot_1213_laf_istres_exterieur);
		put("1213_LAF_LeCannet_domicile", R.drawable.maillot_1213_laf_lecannet_domicile);
		put("1213_LAF_LeCannet_exterieur", R.drawable.maillot_1213_laf_lecannet_exterieur);
		put("1213_LAF_Mulhouse_domicile", R.drawable.maillot_1213_laf_mulhouse_domicile);
		put("1213_LAF_Mulhouse_exterieur", R.drawable.maillot_1213_laf_mulhouse_exterieur);
		put("1213_LAF_Nantes_domicile", R.drawable.maillot_1213_laf_nantes_domicile);
		put("1213_LAF_Nantes_exterieur", R.drawable.maillot_1213_laf_nantes_exterieur);
		put("1213_LAF_Paris_domicile", R.drawable.maillot_1213_laf_paris_domicile);
		put("1213_LAF_Paris_exterieur", R.drawable.maillot_1213_laf_paris_exterieur);
		put("1213_LAF_Venelles_domicile", R.drawable.maillot_1213_laf_venelles_domicile);
		put("1213_LAF_Venelles_exterieur", R.drawable.maillot_1213_laf_venelles_exterieur);
		put("1213_LAM_Ajaccio_domicile", R.drawable.maillot_1213_lam_ajaccio_domicile);
		put("1213_LAM_Ajaccio_exterieur", R.drawable.maillot_1213_lam_ajaccio_exterieur);
		put("1213_LAM_Avignon_domicile", R.drawable.maillot_1213_lam_avignon_domicile);
		put("1213_LAM_Avignon_exterieur", R.drawable.maillot_1213_lam_avignon_exterieur);
		put("1213_LAM_Beauvais_domicile", R.drawable.maillot_1213_lam_beauvais_domicile);
		put("1213_LAM_Beauvais_exterieur", R.drawable.maillot_1213_lam_beauvais_exterieur);
		put("1213_LAM_Cannes_domicile", R.drawable.maillot_1213_lam_cannes_domicile);
		put("1213_LAM_Cannes_exterieur", R.drawable.maillot_1213_lam_cannes_exterieur);
		put("1213_LAM_Chaumont_domicile", R.drawable.maillot_1213_lam_chaumont_domicile);
		put("1213_LAM_Chaumont_exterieur", R.drawable.maillot_1213_lam_chaumont_exterieur);
		put("1213_LAM_Montpellier_domicile", R.drawable.maillot_1213_lam_montpellier_domicile);
		put("1213_LAM_Montpellier_exterieur", R.drawable.maillot_1213_lam_montpellier_exterieur);
		put("1213_LAM_NantesReze_domicile", R.drawable.maillot_1213_lam_nantesreze_domicile);
		put("1213_LAM_NantesReze_exterieur", R.drawable.maillot_1213_lam_nantesreze_exterieur);
		put("1213_LAM_Narbonne_domicile", R.drawable.maillot_1213_lam_narbonne_domicile);
		put("1213_LAM_Narbonne_exterieur", R.drawable.maillot_1213_lam_narbonne_exterieur);
		put("1213_LAM_Paris_domicile", R.drawable.maillot_1213_lam_paris_domicile);
		put("1213_LAM_Paris_exterieur", R.drawable.maillot_1213_lam_paris_exterieur);
		put("1213_LAM_Rennes_domicile", R.drawable.maillot_1213_lam_rennes_domicile);
		put("1213_LAM_Rennes_exterieur", R.drawable.maillot_1213_lam_rennes_exterieur);
		put("1213_LAM_Sete_domicile", R.drawable.maillot_1213_lam_sete_domicile);
		put("1213_LAM_Sete_exterieur", R.drawable.maillot_1213_lam_sete_exterieur);
		put("1213_LAM_Toulouse_domicile", R.drawable.maillot_1213_lam_toulouse_domicile);
		put("1213_LAM_Toulouse_exterieur", R.drawable.maillot_1213_lam_toulouse_exterieur);
		put("1213_LAM_Tourcoing_domicile", R.drawable.maillot_1213_lam_tourcoing_domicile);
		put("1213_LAM_Tourcoing_exterieur", R.drawable.maillot_1213_lam_tourcoing_exterieur);
		put("1213_LAM_Tours_domicile", R.drawable.maillot_1213_lam_tours_domicile);
		put("1213_LAM_Tours_exterieur", R.drawable.maillot_1213_lam_tours_exterieur);
		put("1213_LBM_Ales_domicile", R.drawable.maillot_1213_lbm_ales_domicile);
		put("1213_LBM_Ales_exterieur", R.drawable.maillot_1213_lbm_ales_exterieur);
		put("1213_LBM_Asnieres_domicile", R.drawable.maillot_1213_lbm_asnieres_domicile);
		put("1213_LBM_Asnieres_exterieur", R.drawable.maillot_1213_lbm_asnieres_exterieur);
		put("1213_LBM_Calais_domicile", R.drawable.maillot_1213_lbm_calais_domicile);
		put("1213_LBM_Calais_exterieur", R.drawable.maillot_1213_lbm_calais_exterieur);
		put("1213_LBM_Cambrai_domicile", R.drawable.maillot_1213_lbm_cambrai_domicile);
		put("1213_LBM_Cambrai_exterieur", R.drawable.maillot_1213_lbm_cambrai_exterieur);
		put("1213_LBM_Canteleu_domicile", R.drawable.maillot_1213_lbm_canteleu_domicile);
		put("1213_LBM_Canteleu_exterieur", R.drawable.maillot_1213_lbm_canteleu_exterieur);
		put("1213_LBM_Harnes_domicile", R.drawable.maillot_1213_lbm_harnes_domicile);
		put("1213_LBM_Harnes_exterieur", R.drawable.maillot_1213_lbm_harnes_exterieur);
		put("1213_LBM_Lyon_domicile", R.drawable.maillot_1213_lbm_lyon_domicile);
		put("1213_LBM_Lyon_exterieur", R.drawable.maillot_1213_lbm_lyon_exterieur);
		put("1213_LBM_Martigues_domicile", R.drawable.maillot_1213_lbm_martigues_domicile);
		put("1213_LBM_Martigues_exterieur", R.drawable.maillot_1213_lbm_martigues_exterieur);
		put("1213_LBM_Nancy_domicile", R.drawable.maillot_1213_lbm_nancy_domicile);
		put("1213_LBM_Nancy_exterieur", R.drawable.maillot_1213_lbm_nancy_exterieur);
		put("1213_LBM_Nice_domicile", R.drawable.maillot_1213_lbm_nice_domicile);
		put("1213_LBM_Nice_exterieur", R.drawable.maillot_1213_lbm_nice_exterieur);
		put("1213_LBM_Orange_domicile", R.drawable.maillot_1213_lbm_orange_domicile);
		put("1213_LBM_Orange_exterieur", R.drawable.maillot_1213_lbm_orange_exterieur);
		put("1213_LBM_Pl-Robinson_domicile", R.drawable.maillot_1213_lbm_pl_robinson_domicile);
		put("1213_LBM_Pl-Robinson_exterieur", R.drawable.maillot_1213_lbm_pl_robinson_exterieur);
		put("1213_LBM_St-Brieuc_domicile", R.drawable.maillot_1213_lbm_st_brieuc_domicile);
		put("1213_LBM_St-Brieuc_exterieur", R.drawable.maillot_1213_lbm_st_brieuc_exterieur);
		put("1213_LBM_St-Nazaire_domicile", R.drawable.maillot_1213_lbm_st_nazaire_domicile);
		put("1213_LBM_St-Nazaire_exterieur", R.drawable.maillot_1213_lbm_st_nazaire_exterieur);
	}};

	public Drawable getMaillot(String saison, String competition, String equipe, String lieu) {
		
		String key = saison+"_"+competition+"_"+equipe.replace(" ","").replace("é", "e").replace("è", "e")+"_"+lieu;
		
		//Log.d(this.getClass().getName(),"Looking for : "+key);
		
		if (MAILLOTS.containsKey(key)) {
			return resources.getDrawable(MAILLOTS.get(key));
		}
		
		return resources.getDrawable(R.drawable.maillot_autre);
	}
}
