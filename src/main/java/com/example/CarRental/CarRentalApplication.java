package com.example.CarRental;

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
	public CommandLineRunner demo(CarRepository carRepository) {
		return (args) -> {
			Car car = new Car("11AA22", "Aston Martin", 10000);
			carRepository.save(car);
			List<Car> cars = carRepository.findByBrand("Aston Martin");
			for(Car c: cars) {
				System.out.println(c);
			}
		};
	}
		
}
