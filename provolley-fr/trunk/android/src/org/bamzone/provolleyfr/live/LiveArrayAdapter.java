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
package org.bamzone.provolleyfr.live;

import java.util.List;

import org.bamzone.provolleyfr.ProVolley;
import org.bamzone.provolleyfr.R;
import org.bamzone.provolleyfr.data.LiveMatch;
import org.bamzone.provolleyfr.provider.ResourcesProvider;
import org.bamzone.provolleyfr.provider.ResourcesProviderFactory;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LiveArrayAdapter extends ArrayAdapter<LiveMatch> {
  private final Context context;
  private final List<LiveMatch> values;
  private final ResourcesProvider resourcesProvider;

  public LiveArrayAdapter(Context context, List<LiveMatch> values) {
    super(context, R.layout.live_row, values);
    this.context = context;
    this.values = values;
    this.resourcesProvider = ResourcesProviderFactory.getDataProvider(context.getResources());
  }

  public void refill(List<LiveMatch> matchs) {
	  setNotifyOnChange(false);
	  this.clear();
	  for (LiveMatch m:matchs) this.add(m);
	  notifyDataSetChanged();
  }
  
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View rowView = inflater.inflate(R.layout.live_row, parent, false);
    LiveMatch match = values.get(position);
    
    int color=Color.BLACK;
    if(ProVolley.CODE_COMPETITION_LAF.equals(match.getCompetition())) color = ProVolley.COULEUR_FOND_LIVE_LAF;
    if(ProVolley.CODE_COMPETITION_LAM.equals(match.getCompetition())) color = ProVolley.COULEUR_FOND_LIVE_LAM;
    if(ProVolley.CODE_COMPETITION_LBM.equals(match.getCompetition())) color = ProVolley.COULEUR_FOND_LIVE_LBM;
    if(ProVolley.CODE_COMPETITION_CNF.equals(match.getCompetition())) color = ProVolley.COULEUR_FOND_LIVE_CNF;
    if(ProVolley.CODE_COMPETITION_CNM.equals(match.getCompetition())) color = ProVolley.COULEUR_FOND_LIVE_CNM;
    rowView.setBackgroundColor(color);

    /*
    if(ProVolley.CODE_COMPETITION_LAF.equals(match.getCompetition())) color = ProVolley.COULEUR_SPACE_LIVE_LAF;
    if(ProVolley.CODE_COMPETITION_LAM.equals(match.getCompetition())) color = ProVolley.COULEUR_SPACE_LIVE_LAM;
    if(ProVolley.CODE_COMPETITION_LBM.equals(match.getCompetition())) color = ProVolley.COULEUR_SPACE_LIVE_LBM;
    (TextView) (rowView.findViewById(R.id.space)).setBackgroundColor(color);
	*/

    TextView equipe1 = (TextView) rowView.findViewById(R.id.equipe1);
    TextView equipe2 = (TextView) rowView.findViewById(R.id.equipe2);
    TextView resultat = (TextView) rowView.findViewById(R.id.resultat);
    TextView score = (TextView) rowView.findViewById(R.id.score);
    
    TextView classement1 = (TextView) rowView.findViewById(R.id.classement1);
    TextView classement2 = (TextView) rowView.findViewById(R.id.classement2);

    ImageView maillot1 = (ImageView) rowView.findViewById(R.id.maillot1);
    ImageView maillot2 = (ImageView) rowView.findViewById(R.id.maillot2);

    ImageView service1 = (ImageView) rowView.findViewById(R.id.service1);
    ImageView service2 = (ImageView) rowView.findViewById(R.id.service2);
    
    service1.setImageDrawable(null);
    service2.setImageDrawable(null);
    
    // Affichage du service
    if (LiveHelper.isServiceDomicile(match)) {
    	service1.setImageResource(R.drawable.ic_service);
    }
    if (LiveHelper.isServiceExterieur(match)) {
    	service2.setImageResource(R.drawable.ic_service);
    }
    
    if(equipe1!=null) equipe1.setText(match.getEquipe1());
    if(equipe2!=null) equipe2.setText(match.getEquipe2());
    
    if(classement1!=null) classement1.setText(Html.fromHtml(match.getClassement1()));
    if(classement2!=null) classement2.setText(Html.fromHtml(match.getClassement2()));
    
    resultat.setText(match.getResultat());
    // si match termine, affichage en rouge
    if(LiveHelper.isMatchTermine(match)||LiveHelper.isMatchValide(match)) {
    	resultat.setTextColor(ProVolley.COULEUR_TEXTE_MATCH_TERMINE);
    }
    score.setText(match.getScore());
    
    // Si match en cours, affichage maillot
    if(LiveHelper.isMatchEnCours(match)) {
	    if (maillot1!=null) 
	    	maillot1.setImageDrawable(resourcesProvider.getMaillot(match.getSaison(), match.getCompetition(), match.getEquipe1(), ProVolley.LIBELLE_MAILLOT_DOMICILE));
	    if (maillot2!=null) 
	    	maillot2.setImageDrawable(resourcesProvider.getMaillot(match.getSaison(), match.getCompetition(), values.get(position).getEquipe2(), ProVolley.LIBELLE_MAILLOT_EXTERIEUR));
    }
    else {
	    if (maillot1!=null) 
	    	maillot1.setImageDrawable(null);
	    if (maillot2!=null) 
	    	maillot2.setImageDrawable(null);
    }

    return rowView;
  }
} 