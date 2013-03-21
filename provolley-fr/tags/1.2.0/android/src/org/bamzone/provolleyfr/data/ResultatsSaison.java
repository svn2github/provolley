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

public class ResultatsSaison {
	String saison;
	int minJournee;
	int maxJournee;
	int currentJournee;
	List<ResultatsJournee> journees;
	
	public ResultatsSaison(String saison, int minJournee, int maxJournee,
			int currentJournee) {
		super();
		this.saison = saison;
		this.minJournee = minJournee;
		this.maxJournee = maxJournee;
		this.currentJournee = currentJournee;
		this.journees = new ArrayList<ResultatsJournee>();
	}
	
	public String getSaison() {
		return saison;
	}
	public void setSaison(String saison) {
		this.saison = saison;
	}
	public int getMinJournee() {
		return minJournee;
	}
	public void setMinJournee(int minJournee) {
		this.minJournee = minJournee;
	}
	public int getMaxJournee() {
		return maxJournee;
	}
	public void setMaxJournee(int maxJournee) {
		this.maxJournee = maxJournee;
	}
	public int getCurrentJournee() {
		return currentJournee;
	}
	public void setCurrentJournee(int currentJournee) {
		this.currentJournee = currentJournee;
	}
	public List<ResultatsJournee> getJournees() {
		return journees;
	}
}
