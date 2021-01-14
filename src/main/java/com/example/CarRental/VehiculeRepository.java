package com.example.CarRental;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface VehiculeRepository extends CrudRepository<Vehicule, String>{
	
	List<Vehicule> findByBrand(String brand);

}
