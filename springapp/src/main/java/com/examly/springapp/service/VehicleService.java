package com.examly.springapp.service;

import com.examly.springapp.entity.Vehicle;
import com.examly.springapp.model.AddVehicleRequest;
import com.examly.springapp.model.EditVehicleRequest;
import com.examly.springapp.repository.VehicleRepository;
import com.examly.springapp.utility.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public Optional<Vehicle> findById(int vehicleId) {
        return vehicleRepository.findById(vehicleId);
    }
    
    public List<Vehicle> getVehicles(){
    	return vehicleRepository.findAll();
    }
    
    public void addVehicle(AddVehicleRequest addVehicleRequest) {
		Vehicle vehicle = new Vehicle(addVehicleRequest);
    	boolean vehicleIsPresent = vehicleRepository.findByName(vehicle.getName()).isPresent();
    	if(vehicleIsPresent)
    		throw new IllegalStateException("The vehicle "+vehicle.getName() +" already exists");
    	vehicleRepository.save(vehicle);
    }

    public Vehicle deleteVehicle(int vehicleId) {
    	Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);
    	if(vehicle == null)
    		throw new IllegalStateException("The vehicleId "+vehicleId +" does not exist");
    	vehicleRepository.delete(vehicle);
    	return vehicle;
    }

	public Vehicle getVehicleById(int vehicleId){
		Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);
		if(vehicle == null)
			throw new IllegalStateException("The vehicleId "+vehicleId +" does not exist");
		return vehicle;
	}
    
    public Vehicle editVehicle(int vehicleId, EditVehicleRequest editVehicleRequest) {
    	Vehicle oldVehicle = vehicleRepository.findById(vehicleId).orElse(null);
    	if(oldVehicle == null)
    		throw new IllegalStateException("The vehicleId "+vehicleId +" does not exist");
    	
    	oldVehicle.setName(StringUtils.capitalize(editVehicleRequest.getName()));
    	oldVehicle.setImageUrl(editVehicleRequest.getImageUrl());
    	oldVehicle.setAddress(editVehicleRequest.getAddress());
    	oldVehicle.setDescription(editVehicleRequest.getDescription());
    	oldVehicle.setAvailableStatus(editVehicleRequest.getAvailableStatus());
    	oldVehicle.setTime(editVehicleRequest.getTime());
    	oldVehicle.setCapacity(editVehicleRequest.getCapacity());
    	oldVehicle.setTicketPrice(editVehicleRequest.getTicketPrice());
    	
    	return vehicleRepository.save(oldVehicle);
    }



    
}
