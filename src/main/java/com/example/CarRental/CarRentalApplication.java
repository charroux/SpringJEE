package com.example.CarRental;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CarRentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRentalApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(VehiculeRepository vehiculeRepository) {
		return (args) -> {
			
			Car car = new Car("11AA22", "Aston Martin", 10000);
			
			Rent rent = new Rent();
			Date begin = Calendar.getInstance().getTime();
			rent.setBegin(begin);
			Date end = Calendar.getInstance().getTime();
			rent.setEnd(end);
			
			car.getRents().add(rent);
			rent.setVehicule(car);
			
			Person person = new Person("Tintin");
			person.getRents().add(rent);
			rent.setPerson(person);
			
			vehiculeRepository.save(car);
			
			Iterable<Vehicule> vehicules = vehiculeRepository.findAll();;
			for(Vehicule vehicule: vehicules) {
				System.out.println(vehicule);
			}
			
			vehicules = vehiculeRepository.findByBrand("Aston Martin");
			for(Vehicule vehicule: vehicules) {
				System.out.println(vehicule);
			}
		};
	}
		
}
