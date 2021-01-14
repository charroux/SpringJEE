package com.example.CarRental;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, String>{
	
	List<Car> findByBrand(String brand);

}
