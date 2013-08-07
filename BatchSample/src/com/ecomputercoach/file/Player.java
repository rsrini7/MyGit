package com.ecomputercoach.file;
 
import java.io.Serializable;
 
public class Player implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4371852035852026914L;
	private String id; 
	private String lastName; 
	private String firstName; 
	private String position;
	
	private CareerPeriod careerPeriod;
	
	@SuppressWarnings("unused")
	private String fullName;
	private double careerLength;	
 
	public String getId() {
		return id;
	}
	public String getLastName() {
		return lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getPosition() {
		return position;
	}
 
	public void setId(String id) {
		this.id = id;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setPosition(String position) {
		this.position = position;
	}
 
	public String getFullName() {
		return this.firstName+" "+this.lastName;
	}	
 
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public double getCareerLength() {
		return careerLength;
	}
	
	public void setCareerLength(double careerLength) {
		this.careerLength = careerPeriod.getFinalYear() - careerPeriod.getDebutYear();
	}
	public CareerPeriod getCareerPeriod() {
		return careerPeriod;
	}
	public void setCareerPeriod(CareerPeriod careerPeriod) {
		this.careerPeriod = careerPeriod;
	}		
}