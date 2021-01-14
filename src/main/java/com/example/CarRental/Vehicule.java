package com.example.CarRental;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Vehicule {

	private String plateNumber;
	private String brand;
	private int price;
	boolean rented;
	List<Rent> rents = new ArrayList<Rent>();
	
	List<Dates> dates = new ArrayList<Dates>();
	
	public Vehicule() {
		super();
	}
	
	public Vehicule(String plateNumber, String brand, int price) {
		super();
		this.plateNumber = plateNumber;
		this.brand = brand;
		this.price = price;
	}
	
	@Id
	public String getPlateNumber() {
		return plateNumber;
	}
	
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}

	@Transient
	public List<Dates> getDates() {
		return dates;
	}

	public void setDates(List<Dates> dates) {
		this.dates = dates;
	}

	public boolean isRented() {
		return rented;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}

	@OneToMany(mappedBy = "vehicule", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<Rent> getRents() {
		return rents;
	}

	public void setRents(List<Rent> rents) {
		this.rents = rents;
	}

	@Override
	public String toString() {
		return "Vehicule [plateNumber=" + plateNumber + ", brand=" + brand + ", price=" + price + ", rented=" + rented
				+ ", rents=" + rents + ", dates=" + dates + "]";
	}



}
