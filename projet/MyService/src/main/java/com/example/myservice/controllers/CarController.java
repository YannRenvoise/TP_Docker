package com.example.myservice.controllers;

import com.example.myservice.entities.Car;
import com.example.myservice.services.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/")
    public Map<String, Object> sayHello() {
        return Map.of(
                "service", "Car rental catalog",
                "status", "running",
                "availableCars", carService.getAvailableCars().size(),
                "totalCars", carService.getCars().size()
        );
    }

    @PostMapping("/cars")
    public ResponseEntity<?> addCar(@RequestBody Car car) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(carService.addCar(car));
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(Map.of("error", exception.getMessage()));
        }
    }

    @GetMapping("/cars")
    public List<Car> getCars() {
        return carService.getCars();
    }

    @GetMapping("/cars/available")
    public List<Car> getAvailableCars() {
        return carService.getAvailableCars();
    }

    @GetMapping("/cars/{plateNumber}")
    public ResponseEntity<?> getCar(@PathVariable String plateNumber) {
        Car car = carService.getCar(plateNumber);
        if (car == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Car not found"));
        }
        return ResponseEntity.ok(car);
    }

    @PatchMapping("/cars/{plateNumber}/availability")
    public ResponseEntity<?> updateAvailability(@PathVariable String plateNumber,
                                                @RequestParam boolean available) {
        Car car = carService.updateAvailability(plateNumber, available);
        if (car == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Car not found"));
        }
        return ResponseEntity.ok(car);
    }
}
