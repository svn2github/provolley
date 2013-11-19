package org.bamzone.provolleyfr.cache;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CacheDataSource {
	
	private CacheDatabaseHelper cacheDBHelper;
	private SQLiteDatabase database;
	
	public CacheDataSource(Context context) {
		cacheDBHelper = new CacheDatabaseHelper(context);
	}
	
	public void open() throws SQLException {
		database = cacheDBHelper.getWritableDatabase();
	}
	
	public void close() {
		cacheDBHelper.close();
		database = null;
	}
	
	public String getCachedItem(String key) {
		Cursor cursor = database.query(CacheDatabaseHelper.PROVOLLEY_CACHE_TABLE, new String[]{CacheDatabaseHelper.PROVOLLEY_CACHE_CONTENT},CacheDatabaseHelper.PROVOLLEY_CACHE_QUERY_BY_ID,new String[]{key},null,null,null);
		if (cursor.moveToFirst()) {
			String content=cursor.getString(0);
			cursor.close();
			return content;
		}
		else return null;
	}
	
	public void deleteCachedItem(String key) {
		database.delete(CacheDatabaseHelper.PROVOLLEY_CACHE_TABLE, CacheDatabaseHelper.PROVOLLEY_CACHE_QUERY_BY_ID, new String[]{key});
	}

	public void insertCachedItem(String key, String content) {
		deleteCachedItem(key);
		ContentValues values = new ContentValues(2);
		values.put(CacheDatabaseHelper.PROVOLLEY_CACHE_ID, key);
		values.put(CacheDatabaseHelper.PROVOLLEY_CACHE_CONTENT, content);
		database.insert(CacheDatabaseHelper.PROVOLLEY_CACHE_TABLE, null, values);
	}

	public void deleteAllCachedItems() {
		database.delete(CacheDatabaseHelper.PROVOLLEY_CACHE_TABLE,null,null);
	}
}
