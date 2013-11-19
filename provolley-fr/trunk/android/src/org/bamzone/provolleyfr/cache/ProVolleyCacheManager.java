package org.bamzone.provolleyfr.cache;

import android.content.Context;

public class ProVolleyCacheManager {
	
	private static ProVolleyCacheManager instance;
	private static CacheDataSource cacheDataSource;
	
	public static synchronized void init(Context context) {
		if (instance==null) {
			instance = new ProVolleyCacheManager();
			cacheDataSource = new CacheDataSource(context);
			cacheDataSource.open();
		}
	}
	
	public static synchronized ProVolleyCacheManager getInstance() {
		if (instance == null) {
			throw new IllegalStateException("Not initialized");
		}
		return instance;
	}
	
	public CacheDataSource getCacheDataSource() {
		return cacheDataSource;
	}
}
