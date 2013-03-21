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

import java.util.List;

import org.bamzone.provolleyfr.ProVolley;
import org.bamzone.provolleyfr.R;
import org.bamzone.provolleyfr.data.TVEmission;
import org.bamzone.provolleyfr.provider.ResourcesProvider;
import org.bamzone.provolleyfr.provider.ResourcesProviderFactory;
import org.bamzone.provolleyfr.utils.ListTagHandler;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ProgTVArrayAdapter extends ArrayAdapter<TVEmission> {
  private final Context context;
  private final List<TVEmission> values;
  private final ResourcesProvider resourcesProvider;

  public ProgTVArrayAdapter(Context context, List<TVEmission> values) {
    super(context, R.layout.progtv_row, values);
    this.context = context;
    this.values = values;
    this.resourcesProvider = ResourcesProviderFactory.getDataProvider(context.getResources());
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View rowView = inflater.inflate(R.layout.progtv_row, parent, false);

    TextView dateHeure = (TextView) rowView.findViewById(R.id.DateHeureTextView);
    TextView duree = (TextView) rowView.findViewById(R.id.DureeTextView);
    TextView titre = (TextView) rowView.findViewById(R.id.TitreTextView);
    TextView type = (TextView) rowView.findViewById(R.id.TypeTextView);
    TextView diffusion = (TextView) rowView.findViewById(R.id.DiffusionTextView);
    
    if(dateHeure!=null)
    	dateHeure.setText(values.get(position).getDate()+" à "+values.get(position).getHeure());
    if(duree!=null)
    	duree.setText(values.get(position).getDuree());
    if (titre!=null)
    	titre.setText(Html.fromHtml(values.get(position).getTitre(),null,new ListTagHandler()));
    if(type!=null)
    	type.setText(values.get(position).getType());
    if(diffusion!=null) {
    	String texte = values.get(position).getDiffusion();
    	// Set Direct to Red color
    	if (ProVolley.PROGTV_DIRECT.equalsIgnoreCase(texte)) diffusion.setTextColor(Color.RED);
    	diffusion.setText(texte);
    }

    ImageView logo = (ImageView) rowView.findViewById(R.id.logoImageView);
    if (logo != null) {
    	logo.setImageDrawable(resourcesProvider.getLogoChaine(values.get(position).getIdChaine()));
    }

    return rowView;
  }
} 