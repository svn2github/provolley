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
package org.bamzone.provolleyfr.provider;

import org.bamzone.provolleyfr.ProVolley;

import android.content.SharedPreferences;
import android.content.res.Resources;

public class JSONProviderFactory {
	
    public static JSONProvider getDataProvider(Resources resources, SharedPreferences prefs) {
    	
    	String server = prefs.getString(ProVolley.PREF_KEY_SERVER, ProVolley.PREF_VALUE_PROD);
    	
    	if (ProVolley.PREF_VALUE_LOCAL.equals(server)) {
    		return new LocalJSONProvider(resources);
    	}
    	else if (ProVolley.PREF_VALUE_TEST.equals(server)) {
    		return new TestJSONProvider();
    	}
    	else { // Production
    		return new ProductionJSONProvider();
    	}
    }

}
