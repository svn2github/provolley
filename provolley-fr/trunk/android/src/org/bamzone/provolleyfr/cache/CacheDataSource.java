package org.bamzone.provolleyfr.cache;

import android.database.SQLException;

public interface CacheDataSource {
	
	public void open() throws SQLException;
	public void close();
	public String getCachedItem(String key);
	public void deleteCachedItem(String key);
	public void insertCachedItem(String key, String content);
	public void deleteAllCachedItems();
	public int countAllCachedItems();
}
