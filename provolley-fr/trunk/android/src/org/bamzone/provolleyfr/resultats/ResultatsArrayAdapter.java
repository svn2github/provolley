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
package org.bamzone.provolleyfr.resultats;

import java.util.List;

import org.bamzone.provolleyfr.ProVolley;
import org.bamzone.provolleyfr.R;
import org.bamzone.provolleyfr.coupe.CoupeHelper;
import org.bamzone.provolleyfr.data.LiveMatch;
import org.bamzone.provolleyfr.data.ResultatsMatch;
import org.bamzone.provolleyfr.live.LiveHelper;
import org.bamzone.provolleyfr.provider.ResourcesProvider;
import org.bamzone.provolleyfr.provider.ResourcesProviderFactory;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultatsArrayAdapter extends ArrayAdapter<ResultatsMatch> {
  private final Context context;
  private final List<ResultatsMatch> values;
  private final ResourcesProvider resourcesProvider;

  public ResultatsArrayAdapter(Context context, List<ResultatsMatch> values) {
    super(context, R.layout.resultats_row, values);
    this.context = context;
    this.values = values;
    this.resourcesProvider = ResourcesProviderFactory.getDataProvider(context.getResources());
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View rowView = inflater.inflate(R.layout.resultats_row, parent, false);
    TextView equipe1 = (TextView) rowView.findViewById(R.id.equipe1);
    TextView equipe2 = (TextView) rowView.findViewById(R.id.equipe2);
    TextView resultat = (TextView) rowView.findViewById(R.id.resultat);
    TextView score = (TextView) rowView.findViewById(R.id.score);

    TextView classement1 = (TextView) rowView.findViewById(R.id.classement1);
    TextView classement2 = (TextView) rowView.findViewById(R.id.classement2);
    
    ImageView icon1 = null; // (ImageView) rowView.findViewById(R.id.icon1);
    ImageView icon2 = null; //(ImageView) rowView.findViewById(R.id.icon2);
    
    ResultatsMatch match = values.get(position);

    if(equipe1!=null)equipe1.setText(match.getEquipe1());
    if(equipe2!=null)equipe2.setText(match.getEquipe2());

    // Vainqueur en gras
    if((equipe1!=null)&&ResultatsHelper.isVictoireDomicile(match)) 
    	equipe1.setTypeface(Typeface.DEFAULT_BOLD);
    if((equipe2!=null)&&ResultatsHelper.isVictoireExterieur(match))
    	equipe2.setTypeface(Typeface.DEFAULT_BOLD);

    if(classement1!=null)classement1.setText(Html.fromHtml(match.getClassement1()));
    if(classement2!=null)classement2.setText(Html.fromHtml(match.getClassement2()));
    if(resultat!=null) {
    	resultat.setText(match.getResultat());
        // si match termine, affichage en gras
        if(ResultatsHelper.isMatchTermine(match)) {
        	resultat.setTypeface(Typeface.DEFAULT_BOLD);
        }
    }
    if(score!=null)score.setText(match.getScore());
    
    if (icon1!=null) 
    	icon1.setImageDrawable(resourcesProvider.getMaillot(match.getSaison(), match.getCompetition(), match.getEquipe1(), ProVolley.LIBELLE_MAILLOT_DOMICILE));
    if (icon2!=null) 
    	icon2.setImageDrawable(resourcesProvider.getMaillot(match.getSaison(), match.getCompetition(), match.getEquipe2(), ProVolley.LIBELLE_MAILLOT_EXTERIEUR));
    	
    return rowView;
  }
} 