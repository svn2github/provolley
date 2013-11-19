package org.bamzone.provolleyfr.cache;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public 	class CacheDatabaseHelper extends SQLiteOpenHelper {

	private final static String PROVOLLEY_CACHE_DB = "provolley_cache.db";
	private final static int PROVOLLEY_CACHE_VERSION=1;
	public final static String PROVOLLEY_CACHE_TABLE = "PROVOLLEY_CACHE";
	public final static String PROVOLLEY_CACHE_ID = "ID";
	public final static String PROVOLLEY_CACHE_CONTENT = "CONTENT";
	private final static String PROVOLLEY_CREATE_CACHE_TABLE = "create table "+PROVOLLEY_CACHE_TABLE+" ("+
			PROVOLLEY_CACHE_ID+" VARCHAR(16) PRIMARY KEY,"+PROVOLLEY_CACHE_CONTENT+" TEXT NOT NULL);";
	private final static String PROVOLLEY_DROP_CACHE_TABLE = "drop table "+PROVOLLEY_CACHE_TABLE+";";
	public final static String PROVOLLEY_CACHE_QUERY_BY_ID = PROVOLLEY_CACHE_ID+"=?";
	
	public CacheDatabaseHelper(Context context) {
		super(context, PROVOLLEY_CACHE_DB, null, PROVOLLEY_CACHE_VERSION);
	}
 
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(PROVOLLEY_CREATE_CACHE_TABLE);
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Upgrade
	}

}

