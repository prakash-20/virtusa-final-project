package com.examly.springapp.controller;

import com.examly.springapp.entity.Vehicle;
import com.examly.springapp.model.AddVehicleRequest;
import com.examly.springapp.model.EditVehicleRequest;
import com.examly.springapp.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@AllArgsConstructor
public class VehicleController {
	private final VehicleService vehicleService;

    @PostMapping("/admin/addVehicle")
    public void addVehicle(@RequestBody AddVehicleRequest addVehicleRequest){
    	try {
             vehicleService.addVehicle(addVehicleRequest);
        }catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,exception.getMessage());
            
        }
    }

    @PutMapping("/admin/editVehicle/{vehicleId}")
    public Vehicle editVehicle(@PathVariable int vehicleId, @RequestBody EditVehicleRequest editVehicleRequest){
		try {
			return vehicleService.editVehicle(vehicleId, editVehicleRequest);
		} catch (Exception exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,exception.getMessage());
		}
    }

    @DeleteMapping("/admin/deleteVehicle/{vehicleId}")
    public Vehicle deleteVehicle(@PathVariable int vehicleId){
        try {
			return vehicleService.deleteVehicle(vehicleId);
		} catch (Exception exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,exception.getMessage());
		}
    }

    @GetMapping("/admin/getVehicle/{vehicleId}")
    public Vehicle getVehicleByIdAdmin(@PathVariable int vehicleId){
        try{
            return vehicleService.getVehicleById(vehicleId);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,exception.getMessage());
        }
    }

    @GetMapping("/user/getVehicle/{vehicleId}")
    public Vehicle getVehicleByIdUser(@PathVariable int vehicleId){
        try{
            return vehicleService.getVehicleById(vehicleId);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,exception.getMessage());
        }
    }
}
