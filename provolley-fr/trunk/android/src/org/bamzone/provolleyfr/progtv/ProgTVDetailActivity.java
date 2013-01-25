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
package org.bamzone.provolleyfr.progtv;

import org.bamzone.provolleyfr.ProVolley;
import org.bamzone.provolleyfr.R;
import org.bamzone.provolleyfr.data.NewsItem;
import org.bamzone.provolleyfr.data.TVEmission;
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

public class ProgTVDetailActivity extends Activity {
	
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

	  TVEmission currentTVEmission;
	  
      @Override
	  public void onSaveInstanceState(Bundle savedInstanceState) 
	  {
		  super.onSaveInstanceState(savedInstanceState);
		  
		  // Save current tvEmission to be retrieved when onCreate will be called
		  savedInstanceState.putParcelable(ProVolley.INTENT_EXTRA_PROGSTVITEM, new Sharable(currentTVEmission));
	  }
	  
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progtv_detail_activity);
		
		Sharable sharable;
		if(savedInstanceState != null)
			sharable = (Sharable)(savedInstanceState.getParcelable(ProVolley.INTENT_EXTRA_PROGSTVITEM));
		else 
			sharable = (Sharable)(getIntent().getExtras().getParcelable(ProVolley.INTENT_EXTRA_PROGSTVITEM));
		currentTVEmission=(TVEmission)(sharable.obj());

	    TextView dateHeure = (TextView) findViewById(R.id.DateHeureTextView);
	    TextView duree = (TextView) findViewById(R.id.DureeTextView);
	    TextView chaine = (TextView) findViewById(R.id.ChaineTextView);
	    TextView titre = (TextView) findViewById(R.id.TitreTextView);
	    TextView texte = (TextView) findViewById(R.id.TexteTextView);
	    TextView type = (TextView) findViewById(R.id.TypeTextView);
	    TextView diffusion = (TextView) findViewById(R.id.DiffusionTextView);
	    ImageView logo = (ImageView) findViewById(R.id.logoImageView);
	   
	    if(dateHeure!=null)
	    	dateHeure.setText(currentTVEmission.getDate()+" à "+currentTVEmission.getHeure());
	    if(duree!=null)
	    	duree.setText(currentTVEmission.getDuree());
	    if(chaine!=null)
	    	chaine.setText(currentTVEmission.getNomChaine());
	    if (titre!=null)
	    	titre.setText(Html.fromHtml(currentTVEmission.getTitre(),null,new ListTagHandler()));
	    if(type!=null)
	    	type.setText(currentTVEmission.getType());
	    if(diffusion!=null) {
	    	String texteDiffusion = currentTVEmission.getDiffusion();
	    	// Set Direct to Red color
	    	if (ProVolley.PROGTV_DIRECT.equalsIgnoreCase(texteDiffusion)) diffusion.setTextColor(Color.RED);
	    	diffusion.setText(texteDiffusion);
	    }
	    if (texte!=null) {
		    texte.setText(Html.fromHtml(currentTVEmission.getDescription(),null,new ListTagHandler()));
		    texte.setLinkTextColor(Color.WHITE);
			Linkify.addLinks(texte, Linkify.ALL);
	    }
	    if (logo != null) {
	    	logo.setImageDrawable(ResourcesProviderFactory.getDataProvider(this.getResources()).getLogoChaine(currentTVEmission.getIdChaine()));
	    }
	}
}