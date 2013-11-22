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
package org.bamzone.provolleyfr.news;

import java.util.List;

import org.bamzone.provolleyfr.ProVolley;
import org.bamzone.provolleyfr.R;
import org.bamzone.provolleyfr.data.NewsItem;
import org.bamzone.provolleyfr.data.NewsProVolley;
import org.bamzone.provolleyfr.provider.JSONProvider;
import org.bamzone.provolleyfr.provider.JSONProviderFactory;
import org.bamzone.provolleyfr.utils.Sharable;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.analytics.tracking.android.EasyTracker;

public class NewsActivity extends ListActivity {
	
	  @Override
	  public void onStart() {
	    super.onStart();
	
	    EasyTracker.getInstance().activityStart(this); 
	  }

	  @Override
	  public void onStop() {
	    super.onStop();

	    EasyTracker.getInstance().activityStop(this);
	  }

	  NewsProVolley news;
	  JSONProvider dataProvider;

	  public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_activity);

		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(this);

		dataProvider = JSONProviderFactory.getDataProvider(prefs);

		displayResults(NewsHelper.getNewsProVolleyFromCache());
		
		DownloadNews task  = new DownloadNews();
        task.execute();
        
	}

	private void displayResults(NewsProVolley result) {
        if (result != null) {
        	
        	news = result;
			final List<NewsItem> newsItems = result.getNewsItems();
			
			if (newsItems.size()==0) {
				// FIXME : hardcoded
				final ArrayAdapter<String> adapter = new ArrayAdapter<String>(NewsActivity.this,R.layout.news_none, new String[] {"Pas de programme trouvé..."});
			        setListAdapter(adapter);
			}
			else {
				final NewsArrayAdapter adapter = new NewsArrayAdapter(newsItems);
				setListAdapter(adapter);
			
				NewsActivity.this.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						
						Intent newsDetailIntent = new Intent(NewsActivity.this, NewsDetailActivity.class);
						Sharable newsItem = new Sharable(adapter.getItem(position));
						newsDetailIntent.putExtra(ProVolley.INTENT_EXTRA_NEWSITEM, newsItem);
						startActivity(newsDetailIntent);
					}
				});
			}
        }
	}
	
	private class DownloadNews extends AsyncTask<Void, Void, NewsProVolley> {

		@Override
		protected NewsProVolley doInBackground(Void... args) {
  			 Log.d(this.getClass().getName(),"Downloading News");
			 return(NewsHelper.getNewsProVolleyFromServer(dataProvider));
		}
		
		protected void onPostExecute(NewsProVolley result) {
			displayResults(result);
			
			if (news == null) {
				// FIXME : hardcoded
				Toast.makeText(NewsActivity.this, "Les informations ne sont pas disponibles pour le moment. Veuillez réessayer dans un instant.", Toast.LENGTH_LONG).show();
				NewsActivity.this.finish();
			}
		}
	}
}