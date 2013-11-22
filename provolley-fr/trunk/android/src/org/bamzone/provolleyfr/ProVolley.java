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
package org.bamzone.provolleyfr;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import android.graphics.Color;

public class ProVolley {
	
	public static final String SERVER_URL_TEST = "http://home.bamzone.org/volley/";
	public static final String SERVER_URL_PROD = "http://fr.provolley.info/";
	public static final String JSON_DIR_TEST = "";
	public static final String JSON_DIR_PROD = "json/";
	public static final String RESOURCES_DIR_TEST = "";
	public static final String RESOURCES_DIR_PROD = "resources/";
	
	public static final String CODE_CHAINE_MCS="MCS";
	public static final String CODE_CHAINE_DAILYMOTION="DAILYMOTION";
	public static final String CODE_CHAINE_TVRENNES35="TVRENNES35";
	public static final String CODE_CHAINE_CORSPORT="CORSPORT";
	public static final String CODE_CHAINE_LAOLATV="LAOLATV";
	public static final String CODE_CHAINE_BWIN="BWIN";
	public static final String CODE_CHAINE_LEQUIPE21="LEQUIPE21";

	public static final String CODE_NEWS_PROVOLLEYFR="ProVolley-fr";
	public static final String CODE_NEWS_LAM="LAM";
	public static final String CODE_NEWS_LAF="LAF";
	public static final String CODE_NEWS_LBM="LBM";
	public static final String CODE_NEWS_CEV="CEV";
	public static final String CODE_NEWS_CLM="CLM";
	public static final String CODE_NEWS_CLW="CLW";
	public static final String CODE_NEWS_CDF="CDF";
	public static final String CODE_NEWS_CNM="CNM";
	public static final String CODE_NEWS_CNF="CNF";
	public static final String CODE_NEWS_LNV="LNV";
	
	public static final String LIBELLE_MAILLOT_DOMICILE="dom";
	public static final String LIBELLE_MAILLOT_EXTERIEUR="ext";
	
	public static final String CODE_COMPETITION_LAM="LAM";
	public static final String CODE_COMPETITION_LAF="LAF";
	public static final String CODE_COMPETITION_LBM="LBM";
	public static final String CODE_COMPETITION_CNM="CNM";
	public static final String CODE_COMPETITION_CNF="CNF";
	
	public static final String INTENT_EXTRA_COMPETITION="competition";
	public static final String INTENT_EXTRA_NEWSITEM="newsitem";
	public static final String INTENT_EXTRA_PROGSTVITEM="progtvitem";

	public static final String PREF_KEY_LAST_LAUNCHED_VERSION = "lastLaunchedVersion";
	
	 public static final String PREF_KEY_SERVER = "server";
	 public static final String PREF_VALUE_LOCAL = "Local";
	 public static final String PREF_VALUE_TEST  = "Test";
	 public static final String PREF_VALUE_PROD  = "Production";
	 
	 public static final String PREF_KEY_CLEAN_CACHE  = "cleanCache";
	 public static final String PREF_KEY_CACHE_ACTIVE  = "useCache";
	 
	 public static final String PREF_KEY_SHOWADS = "showAds";
	 public static final String PREF_KEY_LIVEINTERVAL = "liveInterval";
	 public static final String PREF_KEY_DISABLE_SLEEP_IN_LIVE = "disableSleepInLive";
	 
	 public static final String LIVE_SERVICE_DOMICILE = "Domicile";
	 public static final String LIVE_SERVICE_EXTERIEUR = "Exterieur";
	 public static final String LIVE_VICTOIRE_DOMICILE = "Domicile";
	 public static final String LIVE_VICTOIRE_EXTERIEUR = "Exterieur";
	
	 public static final String LIVE_MATCH_AVENIR = "AVenir";
	 public static final String LIVE_MATCH_ENCOURS = "EnCours";
	 public static final String LIVE_MATCH_TERMINE = "Termine";
	 public static final String LIVE_MATCH_VALIDE = "Valide";

	 public static final String RESULTAT_VICTOIRE_DOMICILE = "Domicile";
	 public static final String RESULTAT_VICTOIRE_EXTERIEUR = "Exterieur";

	 public static final String PROGTV_DIRECT = "Direct";
	 
	 public static final int  COULEUR_FOND_LIVE_LAF = Color.parseColor("#60004f");//0x45,0x03,0x39);
	 public static final int  COULEUR_FOND_LIVE_LAM = Color.parseColor("#0d005c");//0x08,0x02,0x29);
	 public static final int  COULEUR_FOND_LIVE_LBM = Color.parseColor("#0c5200");//0x09,0x33,0x02);
	 public static final int  COULEUR_SPACE_LIVE_LAF = Color.parseColor("#e70fd0");//0x45,0x03,0x39);
	 public static final int  COULEUR_SPACE_LIVE_LAM = Color.parseColor("#140fe7");//0x08,0x02,0x29);
	 public static final int  COULEUR_SPACE_LIVE_LBM = Color.parseColor("#37e347");//0x09,0x33,0x02);

	 public static final int  COULEUR_FOND_LIVE_CNF = Color.parseColor("#60004f");//0x45,0x03,0x39);
	 public static final int  COULEUR_FOND_LIVE_CNM = Color.parseColor("#0d005c");//0x08,0x02,0x29);
	 public static final int  COULEUR_SPACE_LIVE_CNF = Color.parseColor("#e70fd0");//0x45,0x03,0x39);
	 public static final int  COULEUR_SPACE_LIVE_CNM = Color.parseColor("#140fe7");//0x08,0x02,0x29);

	 public static final int  COULEUR_TEXTE_MATCH_TERMINE = Color.rgb(0xff, 0x00, 0x00);
	 
	 public static final String CLASSEMENT_VAINQUEUR = "VAINQUEUR";
	 public static final String CLASSEMENT_QUALPO = "QUALPO";
	 public static final String CLASSEMENT_MAINT = "MAINT";
	 public static final String CLASSEMENT_QUALPO1 = "QUALPO1";
	 public static final String CLASSEMENT_QUALPO2 = "QUALPO2";
	 public static final String CLASSEMENT_QUALPO3 = "QUALPO3";
	 public static final String CLASSEMENT_RELEG = "RELEG";
	 public static final String CLASSEMENT_MONTEE = "MONTEE";
	 public static final String CLASSEMENT_MONTEE2 = "MONTEE2";
	 public static final String CLASSEMENT_DISQ = "DISQ";
	 public static final String CLASSEMENT_AUTRES = "AUTRES";
	 
	 public static final String CLASSEMENT_QUALPOASS = "QUALPOASS";
	 public static final String CLASSEMENT_MAINTASS = "MAINTASS";
	 public static final String CLASSEMENT_QUALPO1ASS = "QUALPO1ASS";
	 public static final String CLASSEMENT_QUALPO2ASS = "QUALPO2ASS";
	 public static final String CLASSEMENT_QUALPO3ASS = "QUALPO3ASS";
	 public static final String CLASSEMENT_RELEGASS = "RELEGASS";
	 public static final String CLASSEMENT_MONTEEASS = "MONTEEASS";
	 public static final String CLASSEMENT_DISQASS = "DISQASS";

	 public static final int COULEUR_CLASSEMENT_BLANC = Color.parseColor("#ffffff");
	 public static final int COULEUR_CLASSEMENT_VERT = Color.parseColor("#12ff00");
	 public static final int COULEUR_CLASSEMENT_BLEU =  Color.parseColor("#00bfff");
	 public static final int COULEUR_CLASSEMENT_ROUGE =  Color.parseColor("#ff0000");
	 public static final int COULEUR_CLASSEMENT_JAUNE =  Color.parseColor("#ffe400");
	 public static final int COULEUR_CLASSEMENT_ORANGE =  Color.parseColor("#ffe400");
	 public static final int COULEUR_CLASSEMENT_NB =  Color.parseColor("#c6c6c6");
	 
	 public static final Map<String , Integer> COULEURS_CLASSEMENT = new HashMap<String , Integer>() {{
			put(CLASSEMENT_QUALPO, COULEUR_CLASSEMENT_VERT);
			put(CLASSEMENT_MAINT, COULEUR_CLASSEMENT_BLANC); // Couleur standard
			put(CLASSEMENT_QUALPO1, COULEUR_CLASSEMENT_VERT);
			put(CLASSEMENT_QUALPO2, COULEUR_CLASSEMENT_BLEU);
			put(CLASSEMENT_QUALPO3, COULEUR_CLASSEMENT_BLEU);
			put(CLASSEMENT_RELEG, COULEUR_CLASSEMENT_ROUGE);
			put(CLASSEMENT_MONTEE, COULEUR_CLASSEMENT_VERT);
			put(CLASSEMENT_AUTRES, COULEUR_CLASSEMENT_BLANC);
			put(CLASSEMENT_QUALPOASS, COULEUR_CLASSEMENT_VERT);
			put(CLASSEMENT_MAINTASS, COULEUR_CLASSEMENT_JAUNE);
			put(CLASSEMENT_QUALPO1ASS, COULEUR_CLASSEMENT_VERT);
			put(CLASSEMENT_QUALPO2ASS, COULEUR_CLASSEMENT_BLEU);
			put(CLASSEMENT_QUALPO3ASS, COULEUR_CLASSEMENT_BLEU);
			put(CLASSEMENT_RELEGASS, COULEUR_CLASSEMENT_ROUGE);
			put(CLASSEMENT_MONTEEASS, COULEUR_CLASSEMENT_VERT);
			put(CLASSEMENT_VAINQUEUR, COULEUR_CLASSEMENT_VERT);
			put(CLASSEMENT_DISQASS, COULEUR_CLASSEMENT_ORANGE);
			put(CLASSEMENT_DISQ, COULEUR_CLASSEMENT_ORANGE);
	 }};


	 public static final Set<String> TEST_DEVICEID_SHA1S = new HashSet<String>() {{
			add("16c9ce74e72d9a0e90ce6a1b941257682851bab4"); // SERIAL_HTC_DESIRE_CHRIS
			add("41e29575c7361b2924f701502ca6d932b45b9e51"); // DEVICEID_EMULATOR
			add("981f9bad50e42440999382f0e6e6543b51a00a36"); // SERIAL_ASUS_NEXUS7_CHRIS
			add("338503009e0759e9400358f42a96e873a857025d"); // SERIAL_SAMSUNG_GALAXYTAB_FRED
			add("5a58653670c8596b3290228c115a030bef1c0963"); // DEVICEID_HTC_MAGIC_CHRIS
			add("cbc2d0af7785ff3e76602fa206fe43380d45fee2"); // SERIAL_NEXUS_ONE_CHRIS
	 }};
	 
}
