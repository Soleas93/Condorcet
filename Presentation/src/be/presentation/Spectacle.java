package be.presentation;

import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;

public class Spectacle implements Comparable {
	private static final SecureRandom random = new SecureRandom();
	
	private int num;
	private Date date;
	private String description;
	
	public Spectacle(int num, Date date, String description) {
		this.num = num;
		this.date = date;
		this.description = description;
	}
	
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getPlace() {
		return random.nextInt(450)+1;
	}

	@Override
	public int compareTo(Object o) {
		Spectacle other = (Spectacle) o;
		return this.num - other.num;
	}

}
