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
package org.bamzone.provolleyfr.coupe;

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

public class CoupeTabActivity extends TabActivity {

   /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coupe_tab_activity);
 
        TabHost tabHost = getTabHost();
 
        // Tab for LAM
        TabSpec cnmspec = tabHost.newTabSpec(ProVolley.CODE_COMPETITION_CNM);
        cnmspec.setIndicator(null, getResources().getDrawable(R.drawable.ic_cnm));
        Intent cnmIntent = new Intent(this, CoupeActivity.class);
        cnmIntent.putExtra(ProVolley.INTENT_EXTRA_COMPETITION, ProVolley.CODE_COMPETITION_CNM);
        cnmspec.setContent(cnmIntent);
 
        // Tab for LAF
        TabSpec cnfspec = tabHost.newTabSpec(ProVolley.CODE_COMPETITION_CNF);
        cnfspec.setIndicator(null, getResources().getDrawable(R.drawable.ic_cnf));
        Intent cnfIntent = new Intent(this, CoupeActivity.class);
        cnfIntent.putExtra(ProVolley.INTENT_EXTRA_COMPETITION, ProVolley.CODE_COMPETITION_CNF);
        cnfspec.setContent(cnfIntent);

        // Adding all TabSpec to TabHost
        tabHost.addTab(cnmspec); // Adding photos tab
        tabHost.addTab(cnfspec); // Adding videos tab
    }
 }
