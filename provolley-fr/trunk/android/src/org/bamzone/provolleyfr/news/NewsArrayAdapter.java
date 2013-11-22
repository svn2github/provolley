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

import org.bamzone.provolleyfr.ProVolleyApplication;
import org.bamzone.provolleyfr.R;
import org.bamzone.provolleyfr.data.NewsItem;
import org.bamzone.provolleyfr.provider.ResourcesProvider;
import org.bamzone.provolleyfr.provider.ResourcesProviderFactory;
import org.bamzone.provolleyfr.utils.ListTagHandler;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsArrayAdapter extends ArrayAdapter<NewsItem> {
	private final List<NewsItem> values;
	private final ResourcesProvider resourcesProvider;

	public NewsArrayAdapter(List<NewsItem> values) {
		super(ProVolleyApplication.getAppContext(), R.layout.news_row, values);
		this.values = values;
		this.resourcesProvider = ResourcesProviderFactory.getDataProvider();
	}

	/* Return the number of items in our model */
	@Override
	public int getCount() {
		return values.size();
	}

	/* Return a single item from a selected position from our model */
	@Override
	public NewsItem getItem(int arg0) {
		return values.get(arg0);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) ProVolleyApplication.getAppContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.news_row, parent, false);

		TextView dateHeure = (TextView) rowView.findViewById(R.id.DateHeureTextView);
		TextView titre = (TextView) rowView.findViewById(R.id.TitreTextView);

		dateHeure.setText(values.get(position).getDate() + ", " + values.get(position).getHeure());
		titre.setText(Html.fromHtml(values.get(position).getTitre(), null, new ListTagHandler()));

		ImageView logo = (ImageView) rowView.findViewById(R.id.logoImageView);
		if (logo != null) {
			logo.setImageDrawable(resourcesProvider.getLogoNews(values.get(position).getCategorie()));
		}

		return rowView;
	}
}