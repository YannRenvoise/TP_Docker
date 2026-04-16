package com.example.myservice.services;

import com.example.myservice.entities.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarServiceTest {

    private CarService carService;

    @BeforeEach
    public void setUp() {
        carService = new CarService();
        carService.loadCatalog();
    }

    @Test
    public void testAddCar() {
        Car car = new Car("ABC123", "Toyota", "Yaris", 65.0, true);
        carService.addCar(car);
        assertEquals(4, carService.getCars().size());
        assertEquals("Yaris", carService.getCar("ABC123").getModel());
    }

    @Test
    public void testGetCars() {
        assertEquals(3, carService.getCars().size());
    }

    @Test
    public void testGetCarByPlateNumber() {
        Car car = new Car("ABC123", "Toyota", "Yaris", 65.0, true);
        carService.addCar(car);
        Car result = carService.getCar("ABC123");
        assertNotNull(result);
        assertEquals("ABC123", result.getPlateNumber());
        assertEquals("Toyota", result.getBrand());
        assertEquals("Yaris", result.getModel());
    }

    @Test
    public void testGetCarNotFound() {
        Car result = carService.getCar("NOTFOUND");
        assertNull(result);
    }

    @Test
    public void testGetAvailableCars() {
        assertEquals(2, carService.getAvailableCars().size());
    }

    @Test
    public void testUpdateAvailability() {
        Car updated = carService.updateAvailability("CC-303-CC", true);

        assertNotNull(updated);
        assertTrue(updated.isAvailable());
        assertEquals(3, carService.getAvailableCars().size());
    }

    @Test
    public void testAddDuplicateCarFails() {
        Car duplicate = new Car("AA-101-AA", "Renault", "Clio", 45.0, true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> carService.addCar(duplicate));

        assertEquals("A car with this plate number already exists", exception.getMessage());
    }

    @Test
    public void testInvalidDailyRateFails() {
        Car invalid = new Car("XYZ789", "Honda", "Civic", 0.0, true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> carService.addCar(invalid));

        assertEquals("Daily rate must be greater than zero", exception.getMessage());
    }
}
