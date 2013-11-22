package org.bamzone.provolleyfr.cache;

import org.bamzone.provolleyfr.ProVolley;
import org.bamzone.provolleyfr.ProVolleyApplication;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class ProVolleyCacheManager {
	
	private static ProVolleyCacheManager instance;
	private static SharedPreferences prefs;
	private static CacheDataSource dbCacheDataSource;
	private static CacheDataSource noCacheDataSource;
		
	public static synchronized ProVolleyCacheManager getInstance() {
		if (instance == null) {
			instance = new ProVolleyCacheManager();
			dbCacheDataSource = new DatabaseCacheDataSource();
			dbCacheDataSource.open();
			noCacheDataSource = new NoopCacheDataSource();
			noCacheDataSource.open();
			prefs = PreferenceManager.getDefaultSharedPreferences(ProVolleyApplication.getAppContext());
		}
		return instance;
	}
	
	public CacheDataSource getChosenCache() {
		if (prefs.getBoolean(ProVolley.PREF_KEY_CACHE_ACTIVE, true))
			return dbCacheDataSource;
		else 
			return noCacheDataSource;
	}

	public CacheDataSource getRealCache() {
		return dbCacheDataSource;
	}

}
