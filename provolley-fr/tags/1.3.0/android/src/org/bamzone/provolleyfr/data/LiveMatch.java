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

public class LiveMatch {

	String equipe1;
	String equipe2;
	String resultat; // ou Date
	String score; // ou heure
	String service;
	String competition;
	String etat;
	String classement1;
	String classement2;
	String saison;
	String victoire;
	String codem;
	String code1;
	String code2;

	public String getEquipe1() {
		return equipe1;
	}

	public String getEquipe2() {
		return equipe2;
	}

	public String getResultat() {
		return resultat;
	}

	public String getScore() {
		return score;
	}

	public String getService() {
		return service;
	}

	public String getCompetition() {
		return competition;
	}

	public String getEtat() {
		return etat;
	}

	public String getSaison() {
		return saison;
	}

	public String getClassement1() {
		return classement1;
	}

	public String getClassement2() {
		return classement2;
	}

	public String getVictoire() {
		return victoire;
	}

	public String getCodem() {
		return codem;
	}

	public void setCodem(String codem) {
		this.codem = codem;
	}

	public String getCode1() {
		return code1;
	}

	public void setCode1(String code1) {
		this.code1 = code1;
	}

	public String getCode2() {
		return code2;
	}

	public void setCode2(String code2) {
		this.code2 = code2;
	}

	public LiveMatch(String saison, String competition, String codem, 
			String code1, String equipe1, String classement1, 
			String code2, String equipe2, String classement2, 
			String resultat, String score, String service, 
			String etat, String victoire) {
		super();
		this.equipe1 = equipe1;
		this.equipe2 = equipe2;
		this.resultat = resultat;
		this.score = score;
		this.service = service;
		this.competition = competition;
		this.etat = etat;
		this.classement1 = classement1;
		this.classement2 = classement2;
		this.saison = saison;
		this.victoire = victoire;
		this.codem = codem;
		this.code1 = code1;
		this.code2 = code2;
	}
}
