package org.bamzone.provolleyfr.cache;

import android.database.SQLException;

public class NoopCacheDataSource implements CacheDataSource {
	
	public NoopCacheDataSource() {
	}
	
	public void open() throws SQLException {
	}
	
	public void close() {
	}
	
	public String getCachedItem(String key) {
		return null;
	}
	
	public void deleteCachedItem(String key) {
	}

	public void insertCachedItem(String key, String content) {
	}

	public void deleteAllCachedItems() {
	}

	public int countAllCachedItems() {
		return 0;
	}
}
