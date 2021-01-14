package com.example.CarRental;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

@RestController
public class CarRentalService {
	
	private List<Car> cars = new ArrayList<Car>();
	
	public CarRentalService() {
		cars.add(new Car("11AA22", "Ferrari", 1000));
		cars.add(new Car("33BB44", "Porshe", 2222));
	}
	
	@GetMapping("/cars")
	public List<Car> getListOfCars(){
		return cars;
	}

	@GetMapping("/cars/{plateNumber}")
	public Car getCar(@PathVariable(value = "plateNumber") String plaque){
		for(Car car: cars){
			if(car.getPlateNumber().equals(plaque)){
				return car;
			}
		}
		return null;
	}
	
	@PostMapping("/cars")
	public void addCar(@RequestBody Car car) throws Exception{
		System.out.println(car);
		cars.add(car);
	}
	
	@PutMapping("/cars/{plateNumber}")
	public void rent(@PathVariable("plateNumber") String plaque, 
			@RequestParam(value="rent", required = true)boolean rent, 
			@RequestBody Dates dates){
		for(Car car: cars) {
			if(car.getPlateNumber().equals(plaque)) {
				if(rent == true) {
					car.getDates().add(dates);
					car.setRented(true);
				} else {
					// car.getDates().remove(dates);	décommenter si on veut supprimer l'historique des dates
					car.setRented(false);
				}
			}
		}
		
	}


	

}
