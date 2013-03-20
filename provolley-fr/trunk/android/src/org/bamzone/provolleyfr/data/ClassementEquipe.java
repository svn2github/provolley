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

public class ClassementEquipe {

	private int rang;
	private String equipe;
	private int point;
	private int mj;
	private int mg;
	private int mp;
	private int m30;
	private int m31;
	private int m32;
	private int m23;
	private int m13;
	private int m03;
	private int sp;
	private int sc;
	private String rs;
	private int pp;
	private int pc;
	private String rp;
	private String etat;
	private String etat2;
	private int pen;
	
	public ClassementEquipe(int rang, String equipe, int point, int mj, int mg,
			int mp, int m30, int m31, int m32, int m23, int m13, int m03,
			int sp, int sc, String rs, int pp, int pc, String rp, String etat,
			String etat2, int pen) {
		super();
		this.rang = rang;
		this.equipe = equipe;
		this.point = point;
		this.mj = mj;
		this.mg = mg;
		this.mp = mp;
		this.m30 = m30;
		this.m31 = m31;
		this.m32 = m32;
		this.m23 = m23;
		this.m13 = m13;
		this.m03 = m03;
		this.sp = sp;
		this.sc = sc;
		this.rs = rs;
		this.pp = pp;
		this.pc = pc;
		this.rp = rp;
		this.etat = etat;
		this.etat2 = etat2;
		this.pen = pen;
	}

	public int getRang() {
		return rang;
	}

	public String getEquipe() {
		return equipe;
	}

	public int getPoint() {
		return point;
	}

	public int getMj() {
		return mj;
	}

	public int getMg() {
		return mg;
	}

	public int getMp() {
		return mp;
	}

	public int getM30() {
		return m30;
	}

	public int getM31() {
		return m31;
	}

	public int getM32() {
		return m32;
	}

	public int getM23() {
		return m23;
	}

	public int getM13() {
		return m13;
	}

	public int getM03() {
		return m03;
	}

	public int getSp() {
		return sp;
	}

	public int getSc() {
		return sc;
	}

	public String getRs() {
		return rs;
	}

	public int getPp() {
		return pp;
	}

	public int getPc() {
		return pc;
	}

	public String getRp() {
		return rp;
	}
	
	public String getEtat() {
		return etat;
	}

	public String getEtat2() {
		return etat2;
	}

	public void setEtat2(String assure) {
		this.etat2 = etat2;
	}

	public int getPen() {
		return pen;
	}

	public void setPen(int pen) {
		this.pen = pen;
	}
	
}
