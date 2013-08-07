package com.ecomputercoach.file;

import java.io.Serializable;

public class CareerPeriod implements Serializable {

	private static final long serialVersionUID = 1982319804492173019L;
	private int debutYear; 
	private int finalYear;
	
	public int getDebutYear() {
		return debutYear;
	}
	public void setDebutYear(int debutYear) {
		this.debutYear = debutYear;
	}
	public int getFinalYear() {
		return finalYear;
	}
	public void setFinalYear(int finalYear) {
		this.finalYear = finalYear;
	}
}
