package com.example.myservice.services;

import com.example.myservice.entities.Car;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final Map<String, Car> cars = new ConcurrentHashMap<>();

    @PostConstruct
    public void loadCatalog() {
        cars.clear();
        addCar(new Car("AA-101-AA", "Renault", "Clio", 45.0, true));
        addCar(new Car("BB-202-BB", "Peugeot", "208", 52.0, true));
        addCar(new Car("CC-303-CC", "Tesla", "Model 3", 110.0, false));
    }

    public Car addCar(Car car) {
        validate(car);
        if (cars.containsKey(car.getPlateNumber())) {
            throw new IllegalArgumentException("A car with this plate number already exists");
        }
        cars.put(car.getPlateNumber(), car);
        return car;
    }

    public List<Car> getCars() {
        return cars.values().stream()
                .sorted(Comparator.comparing(Car::getPlateNumber))
                .collect(Collectors.toList());
    }

    public List<Car> getAvailableCars() {
        return getCars().stream()
                .filter(Car::isAvailable)
                .collect(Collectors.toList());
    }

    public Car getCar(String plateNumber) {
        return cars.get(plateNumber);
    }

    public Car updateAvailability(String plateNumber, boolean available) {
        Car car = cars.get(plateNumber);
        if (car == null) {
            return null;
        }
        car.setAvailable(available);
        return car;
    }

    private void validate(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("Car payload is required");
        }
        if (isBlank(car.getPlateNumber())) {
            throw new IllegalArgumentException("Plate number is required");
        }
        if (isBlank(car.getBrand())) {
            throw new IllegalArgumentException("Brand is required");
        }
        if (isBlank(car.getModel())) {
            throw new IllegalArgumentException("Model is required");
        }
        if (car.getDailyRate() <= 0) {
            throw new IllegalArgumentException("Daily rate must be greater than zero");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
