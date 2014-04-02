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
import org.bamzone.provolleyfr.ProVolleyApplication;
import org.bamzone.provolleyfr.R;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class LocalResourcesProvider implements ResourcesProvider {
	
	private Resources resources;
	
	public LocalResourcesProvider() {
		resources = ProVolleyApplication.getAppContext().getResources();
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
	
	private static final Map<String , Integer> MAILLOTS = new HashMap<String , Integer>() {
		private static final long serialVersionUID = 1L;

	{
		put("ALCM-dom", R.drawable.maillot_alcm_dom);
		put("ALCM-ext", R.drawable.maillot_alcm_ext);
		put("ANGELS-dom", R.drawable.maillot_angels_dom);
		put("ANGELS-ext", R.drawable.maillot_angels_ext);
		put("ARAGO-dom", R.drawable.maillot_arago_dom);
		put("ARAGO-ext", R.drawable.maillot_arago_ext);
		put("ASC-dom", R.drawable.maillot_asc_dom);
		put("ASC-ext", R.drawable.maillot_asc_ext);
		put("ASON-dom", R.drawable.maillot_ason_dom);
		put("ASON-ext", R.drawable.maillot_ason_ext);
		put("ASPTT-dom", R.drawable.maillot_asptt_dom);
		put("ASPTT-ext", R.drawable.maillot_asptt_ext);
		put("ASUL-dom", R.drawable.maillot_asul_dom);
		put("ASUL-ext", R.drawable.maillot_asul_ext);
		put("AV92-dom", R.drawable.maillot_av92_dom);
		put("AV92-ext", R.drawable.maillot_av92_ext);
		put("AVB-dom", R.drawable.maillot_avb_dom);
		put("AVB-ext", R.drawable.maillot_avb_ext);
		put("BOUC-dom", R.drawable.maillot_bouc_dom);
		put("BOUC-ext", R.drawable.maillot_bouc_ext);
		put("CAC-dom", R.drawable.maillot_cac_dom);
		put("CAC-ext", R.drawable.maillot_cac_ext);
		put("CENT-dom", R.drawable.maillot_cent_dom);
		put("CENT-ext", R.drawable.maillot_cent_ext);
		put("CVB52-dom", R.drawable.maillot_cvb52_dom);
		put("CVB52-ext", R.drawable.maillot_cvb52_ext);
		put("CVEC-dom", R.drawable.maillot_cvec_dom);
		put("CVEC-ext", R.drawable.maillot_cvec_ext);
		put("ESCR-dom", R.drawable.maillot_escr_dom);
		put("ESCR-ext", R.drawable.maillot_escr_ext);
		put("EVB-dom", R.drawable.maillot_evb_dom);
		put("EVB-ext", R.drawable.maillot_evb_ext);
		put("GFCA-dom", R.drawable.maillot_gfca_dom);
		put("GFCA-ext", R.drawable.maillot_gfca_ext);
		put("GOELO-dom", R.drawable.maillot_goelo_dom);
		put("GOELO-ext", R.drawable.maillot_goelo_ext);
		put("HVB-dom", R.drawable.maillot_hvb_dom);
		put("HVB-ext", R.drawable.maillot_hvb_ext);
		put("HV-dom", R.drawable.maillot_hv_dom);
		put("HV-ext", R.drawable.maillot_hv_ext);
		put("IOPV-dom", R.drawable.maillot_iopv_dom);
		put("IOPV-ext", R.drawable.maillot_iopv_ext);
		put("LISSP-dom", R.drawable.maillot_lissp_dom);
		put("LISSP-ext", R.drawable.maillot_lissp_ext);
		put("MAVUC-dom", R.drawable.maillot_mavuc_dom);
		put("MAVUC-ext", R.drawable.maillot_mavuc_ext);
		put("MNVJ-dom", R.drawable.maillot_mnvj_dom);
		put("MNVJ-ext", R.drawable.maillot_mnvj_ext);
		put("MVB-dom", R.drawable.maillot_mvb_dom);
		put("MVB-ext", R.drawable.maillot_mvb_ext);
		put("NRMV-dom", R.drawable.maillot_nrmv_dom);
		put("NRMV-ext", R.drawable.maillot_nrmv_ext);
		put("NVB-dom", R.drawable.maillot_nvb_dom);
		put("NVB-ext", R.drawable.maillot_nvb_ext);
		put("PAVVB-dom", R.drawable.maillot_pavvb_dom);
		put("PAVVB-ext", R.drawable.maillot_pavvb_ext);
		put("PRVB-dom", R.drawable.maillot_prvb_dom);
		put("PRVB-ext", R.drawable.maillot_prvb_ext);
		put("PV-dom", R.drawable.maillot_pv_dom);
		put("PV-ext", R.drawable.maillot_pv_ext);
		put("QV29-dom", R.drawable.maillot_qv29_dom);
		put("QV29-ext", R.drawable.maillot_qv29_ext);
		put("RCC-dom", R.drawable.maillot_rcc_dom);
		put("RCC-ext", R.drawable.maillot_rcc_ext);
		put("RV35-dom", R.drawable.maillot_rv35_dom);
		put("RV35-ext", R.drawable.maillot_rv35_ext);
		put("SCPSF-dom", R.drawable.maillot_scpsf_dom);
		put("SCPSF-ext", R.drawable.maillot_scpsf_ext);
		put("SNVBA-dom", R.drawable.maillot_snvba_dom);
		put("SNVBA-ext", R.drawable.maillot_snvba_ext);
		put("SPACERS-dom", R.drawable.maillot_spacers_dom);
		put("SPACERS-ext", R.drawable.maillot_spacers_ext);
		put("TFOC-dom", R.drawable.maillot_tfoc_dom);
		put("TFOC-ext", R.drawable.maillot_tfoc_ext);
		put("TLM-dom", R.drawable.maillot_tlm_dom);
		put("TLM-ext", R.drawable.maillot_tlm_ext);
		put("TVB-dom", R.drawable.maillot_tvb_dom);
		put("TVB-ext", R.drawable.maillot_tvb_ext);
		put("VBN-dom", R.drawable.maillot_vbn_dom);
		put("VBN-ext", R.drawable.maillot_vbn_ext);
		put("autre", R.drawable.maillot_autre);
	}};

	private static final Map<String , String> COULEUR_MAILLOTS = new HashMap<String , String>() {
		private static final long serialVersionUID = -4637600001877925784L;

	{
		put("ALCM-dom", "rouge");
		put("ALCM-ext", "blanc");
		put("ANGELS-dom", "bleu");
		put("ANGELS-ext", "blanc");
		put("ARAGO-dom", "rose");
		put("ARAGO-ext", "bleu");
		put("ASC-dom","rouge");
		put("ASC-ext", "noir");
		put("ASON-dom", "orange");
		put("ASON-ext", "noir");
		put("ASPTT-dom", "noir");
		put("ASPTT-ext", "blanc");
		put("ASUL-dom", "rouge");
		put("ASUL-ext", "bleu");
		put("AV92-dom", "rouge");
		put("AV92-ext", "bleu");
		put("AVB-dom", "bleu");
		put("AVB-ext", "blanc");
		put("BOUC-dom", "rouge");
		put("BOUC-ext", "blanc");
		put("CAC-dom", "bleu");
		put("CAC-ext", "blanc");
		put("CENT-dom", "noir");
		put("CENT-ext", "blanc");
		put("CVB52-dom", "rouge");
		put("CVB52-ext", "noir");
		put("CVEC-dom", "bleu");
		put("CVEC-ext", "rouge"); 
		put("ESCR-dom", "noir");
		put("ESCR-ext", "blanc");
		put("EVB-dom", "bleu");
		put("EVB-ext", "noir");
		put("GFCA-dom", "rouge");
		put("GFCA-ext", "bleu");
		put("GOELO-dom", "jaune");
		put("GOELO-ext", "bleu");
		put("HVB-dom", "noir");
		put("HVB-ext", "rose");
		put("HV-dom", "rouge");
		put("HV-ext", "blanc");
		put("IOPV-dom", "blanc");
		put("IOPV-ext", "noir");
		put("LISSP-dom", "bleu");
		put("LISSP-ext","blanc");
		put("MAVUC-dom", "blanc");
		put("MAVUC-ext", "bleu");
		put("MNVJ-dom", "bleu");
		put("MNVJ-ext", "blanc");
		put("MVB-dom", "rouge");
		put("MVB-ext", "blanc");
		put("NRMV-dom", "blanc");
		put("NRMV-ext", "bleu");
		put("NVB-dom", "bleu");
		put("NVB-ext", "blanc");
		put("PAVVB-dom", "noir");
		put("PAVVB-ext", "bleu");
		put("PRVB-dom", "noir");
		put("PRVB-ext", "blanc");
		put("PV-dom", "bleu");
		put("PV-ext", "blanc");
		put("QV29-dom", "noir");
		put("QV29-ext", "blanc");
		put("RCC-dom", "noir");
		put("RCC-ext", "jaune");
		put("RV35-dom", "noir");
		put("RV35-ext", "blanc");
		put("SCPSF-dom", "bleu");
		put("SCPSF-ext", "rouge");
		put("SNVBA-dom", "bleu");
		put("SNVBA-ext", "blanc");
		put("SPACERS-dom", "bleu");
		put("SPACERS-ext", "blanc");
		put("TFOC-dom", "blanc");
		put("TFOC-ext", "bleu");
		put("TLM-dom", "blanc");
		put("TLM-ext", "noir");
		put("TVB-dom", "blanc");
		put("TVB-ext", "blanc");
		put("VBN-dom", "noir");
		put("VBN-ext", "jaune");
	}};
	
	public Drawable getMaillot(String codeEquipe, String lieu) {
		
		String key = codeEquipe+"-"+lieu;
		
		//Log.d(this.getClass().getName(),"Looking for : "+key);
		
		if (MAILLOTS.containsKey(key)) {
			return resources.getDrawable(MAILLOTS.get(key));
		}
		
		return resources.getDrawable(R.drawable.maillot_autre);
	}

	public String getMaillotCouleur(String codeEquipe, String lieu) {
		
		String key = codeEquipe+"-"+lieu;
		
		//Log.d(this.getClass().getName(),"Looking for : "+key);
		
		if (COULEUR_MAILLOTS.containsKey(key)) {
			return COULEUR_MAILLOTS.get(key);
		}
		
		return "";
	}

}
