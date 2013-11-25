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
package org.bamzone.provolleyfr.classements;

import org.bamzone.provolleyfr.ProVolley;
import org.bamzone.provolleyfr.R;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class ClassementsTabActivity extends TabActivity {

	  /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classements_tab_activity);
 
        TabHost tabHost = getTabHost();
 
        // Tab for Photos
        TabSpec lamspec = tabHost.newTabSpec(ProVolley.CODE_COMPETITION_LAM);
        lamspec.setIndicator(null, getResources().getDrawable(R.drawable.ic_lam));
        Intent lamIntent = new Intent(this, ClassementsActivity.class);
        lamIntent.putExtra(ProVolley.INTENT_EXTRA_COMPETITION, ProVolley.CODE_COMPETITION_LAM);
        lamspec.setContent(lamIntent);
 
        // Tab for Songs
        TabSpec lbmspec = tabHost.newTabSpec(ProVolley.CODE_COMPETITION_LBM);
        lbmspec.setIndicator(null, getResources().getDrawable(R.drawable.ic_lbm));
        Intent lbmIntent = new Intent(this, ClassementsActivity.class);
        lbmIntent.putExtra(ProVolley.INTENT_EXTRA_COMPETITION, ProVolley.CODE_COMPETITION_LBM);
        lbmspec.setContent(lbmIntent);
 
        // Tab for Videos
        TabSpec lafspec = tabHost.newTabSpec(ProVolley.CODE_COMPETITION_LAF);
        lafspec.setIndicator(null, getResources().getDrawable(R.drawable.ic_laf));
        Intent lafIntent = new Intent(this, ClassementsActivity.class);
        lafIntent.putExtra(ProVolley.INTENT_EXTRA_COMPETITION, ProVolley.CODE_COMPETITION_LAF);
        lafspec.setContent(lafIntent);

        // Adding all TabSpec to TabHost
        tabHost.addTab(lamspec); // Adding photos tab
        tabHost.addTab(lbmspec); // Adding songs tab
        tabHost.addTab(lafspec); // Adding videos tab
    }
 }
