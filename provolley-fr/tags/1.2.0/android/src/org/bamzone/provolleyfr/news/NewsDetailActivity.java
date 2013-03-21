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

import org.bamzone.provolleyfr.ProVolley;
import org.bamzone.provolleyfr.R;
import org.bamzone.provolleyfr.data.NewsItem;
import org.bamzone.provolleyfr.provider.ResourcesProviderFactory;
import org.bamzone.provolleyfr.utils.ListTagHandler;
import org.bamzone.provolleyfr.utils.Sharable;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.util.Linkify;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.analytics.tracking.android.EasyTracker;

public class NewsDetailActivity extends Activity {
	
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

	  NewsItem currentNewsItem;

      @Override
	  public void onSaveInstanceState(Bundle savedInstanceState) 
	  {
		  super.onSaveInstanceState(savedInstanceState);
		  
		  // Save current newsItem to be retrieved when onCreate will be called
		  savedInstanceState.putParcelable(ProVolley.INTENT_EXTRA_NEWSITEM, new Sharable(currentNewsItem));
	  }
	  
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_detail_activity);
		
		// Retrive current news item as a Sharable. 
		// If returning from background state, in the saved instance, 
		// otherwise in the extra parameters from the Intent
		Sharable sharable;
		if(savedInstanceState != null)
			sharable = (Sharable)(savedInstanceState.getParcelable(ProVolley.INTENT_EXTRA_NEWSITEM));
		else 
			sharable = (Sharable)(getIntent().getExtras().getParcelable(ProVolley.INTENT_EXTRA_NEWSITEM));
		currentNewsItem=(NewsItem)(sharable.obj());
			
	    TextView dateHeure = (TextView) this.findViewById(R.id.DateHeureTextView);
	    TextView titre = (TextView) this.findViewById(R.id.TitreTextView);
	    TextView texte = (TextView) this.findViewById(R.id.TexteTextView);
	    ImageView logo = (ImageView) findViewById(R.id.logoImageView);

	    if (dateHeure!=null){
		    dateHeure.setText(currentNewsItem.getDate()+", "+currentNewsItem.getHeure());
	    }
	    if (titre != null) {
	    	titre.setText(Html.fromHtml(currentNewsItem.getTitre(),null,new ListTagHandler()));
	    }
	    if (texte != null) {
		    texte.setText(Html.fromHtml(currentNewsItem.getDescription(),null,new ListTagHandler()));
		    texte.setLinkTextColor(Color.WHITE);
			Linkify.addLinks(texte, Linkify.ALL);
	    }
		if (logo != null) {
	    	logo.setImageDrawable(ResourcesProviderFactory.getDataProvider(this.getResources()).getLogoNews(currentNewsItem.getCategorie()));
	    }
	}

}