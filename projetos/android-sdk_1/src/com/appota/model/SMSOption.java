package com.appota.model;

import java.io.Serializable;

public class SMSOption implements Serializable{
	
	private String syntax;
	private String sendNumber;
	private int tym;
	private double amount;
	private String currency;
	
	public String getSyntax() {
		return syntax;
	}
	public void setSyntax(String syntax) {
		this.syntax = syntax;
	}
	public String getSendNumber() {
		return sendNumber;
	}
	public void setSendNumber(String sendNumber) {
		this.sendNumber = sendNumber;
	}
	public int getTym() {
		return tym;
	}
	public void setTym(int tym) {
		this.tym = tym;
	}
	public double getAmount() {
		return (int)amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	@Override
	public String toString() {
		return sendNumber + ":     Amount: " + amount + " " + currency +  " --> " + tym + " TYM ";
	}
	
}
