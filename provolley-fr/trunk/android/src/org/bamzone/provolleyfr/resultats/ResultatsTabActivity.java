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

import org.bamzone.provolleyfr.ProVolley;
import org.bamzone.provolleyfr.R;
import org.bamzone.provolleyfr.R.drawable;
import org.bamzone.provolleyfr.R.layout;

import com.google.analytics.tracking.android.EasyTracker;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class ResultatsTabActivity extends TabActivity {

   /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultats_tab_activity);
 
        TabHost tabHost = getTabHost();
 
        // Tab for LAM
        TabSpec lamspec = tabHost.newTabSpec(ProVolley.CODE_COMPETITION_LAM);
        lamspec.setIndicator(null, getResources().getDrawable(R.drawable.ic_lam));
        Intent lamIntent = new Intent(this, ResultatsActivity.class);
        lamIntent.putExtra(ProVolley.INTENT_EXTRA_COMPETITION, ProVolley.CODE_COMPETITION_LAM);
        lamspec.setContent(lamIntent);
 
        // Tab for LBM
        TabSpec lbmspec = tabHost.newTabSpec(ProVolley.CODE_COMPETITION_LBM);
        lbmspec.setIndicator(null, getResources().getDrawable(R.drawable.ic_lbm));
        Intent lbmIntent = new Intent(this, ResultatsActivity.class);
        lbmIntent.putExtra(ProVolley.INTENT_EXTRA_COMPETITION, ProVolley.CODE_COMPETITION_LBM);
        lbmspec.setContent(lbmIntent);
 
        // Tab for LAF
        TabSpec lafspec = tabHost.newTabSpec(ProVolley.CODE_COMPETITION_LAF);
        lafspec.setIndicator(null, getResources().getDrawable(R.drawable.ic_laf));
        Intent lafIntent = new Intent(this, ResultatsActivity.class);
        lafIntent.putExtra(ProVolley.INTENT_EXTRA_COMPETITION, ProVolley.CODE_COMPETITION_LAF);
        lafspec.setContent(lafIntent);

        // Adding all TabSpec to TabHost
        tabHost.addTab(lamspec); // Adding photos tab
        tabHost.addTab(lbmspec); // Adding songs tab
        tabHost.addTab(lafspec); // Adding videos tab
    }
 }
