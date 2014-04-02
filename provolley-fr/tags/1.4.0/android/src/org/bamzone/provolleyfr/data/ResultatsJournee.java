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
package org.bamzone.provolleyfr.data;

import java.util.ArrayList;
import java.util.List;

public class ResultatsJournee {
	int numJournee;
	String titre;
	
	public int getNumJournee() {
		return numJournee;
	}

	public void setNumJournee(int numJournee) {
		this.numJournee = numJournee;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	List<ResultatsMatch> matchs;

	public ResultatsJournee(int numJournee, String titre) {
		super();
		this.numJournee = numJournee;
		this.titre = titre;
		this.matchs = new ArrayList<ResultatsMatch>();
	}

	public List<ResultatsMatch> getMatchs() {
		return matchs;
	}

}
