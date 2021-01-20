package com.example.CarRental;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

@RestController
public class CarRentalService {
	
	//private List<Car> cars = new ArrayList<Car>();
	VehiculeRepository vehiculeRepository;
	
	@Autowired
	public CarRentalService(VehiculeRepository vehiculeRepository) {
		this.vehiculeRepository = vehiculeRepository;
		
		Car car = new Car("11AA22", "Aston Martin", 10000, 5);
		
/*		Rent rent = new Rent();
		Date begin = Calendar.getInstance().getTime();
		rent.setBegin(begin);
		Date end = Calendar.getInstance().getTime();
		rent.setEnd(end);
		
		car.getRents().add(rent);
		rent.setVehicule(car);
		
		Person person = new Person("Tintin");
		person.getRents().add(rent);
		rent.setPerson(person);
*/		
		vehiculeRepository.save(car);
	}
	
	@GetMapping("/cars")
	public Iterable<Vehicule> getListOfCars(){
		return vehiculeRepository.findAll();
	}

	@GetMapping("/cars/{plateNumber}")
	public Vehicule getCar(@PathVariable(value = "plateNumber") String plaque){
		return vehiculeRepository.findById(plaque).get();
	}
	
	@PostMapping("/cars")
	public void addCar(@RequestBody Car car) throws Exception{
		vehiculeRepository.save(car);
	}
	
	@PutMapping("/cars/{plateNumber}")
	public void rent(@PathVariable("plateNumber") String plaque, 
			@RequestParam(value="rent", required = true)boolean rent, 
			@RequestBody Dates dates) throws ParseException{
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
		
		Vehicule vehicule = vehiculeRepository.findById(plaque).get();
		
		if(vehicule != null){
			if(rent == true) {
				
				Rent newRent = new Rent();
				
				String begin = dates.getBegin();
				Date beginDate = dateFormat.parse(begin);
				newRent.setBegin(beginDate);
				
				String end = dates.getBegin();
				Date endDate = dateFormat.parse(end);
				newRent.setEnd(endDate);
				
				vehicule.getRents().add(newRent);
				newRent.setVehicule(vehicule);
				
				vehicule.setRented(true);
				
			} else {
				
				vehicule.setRented(false);
				
			}
			
			vehiculeRepository.save(vehicule);
			
		}
		
	}


	

}
