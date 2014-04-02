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

public class TVEmission {
	String date;
	String heure;
	String type;
	String idChaine;
	String nomChaine;
	String url;
	
	public TVEmission(String date, String heure, String type, String idchaine, String nomchaine,
			String url, String logo, String duree, String diffusion, String titre,
			String description) {
		super();
		this.date = date;
		this.heure = heure;
		this.type = type;
		this.nomChaine = nomchaine;
		this.idChaine = idchaine;
		this.url = url;
		this.logo = logo;
		this.duree = duree;
		this.diffusion = diffusion;
		this.titre = titre;
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public String getHeure() {
		return heure;
	}

	public String getType() {
		return type;
	}

	public String getNomChaine() {
		return nomChaine;
	}

	public String getIdChaine() {
		return idChaine;
	}

	public String getUrl() {
		return url;
	}

	public String getLogo() {
		return logo;
	}

	public String getDuree() {
		return duree;
	}

	public String getDiffusion() {
		return diffusion;
	}

	public String getTitre() {
		return titre;
	}

	public String getDescription() {
		return description;
	}

	String logo;
	String duree;
	String diffusion;
	String titre;
	String description;
	
	
	
}
