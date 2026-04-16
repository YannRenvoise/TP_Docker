package com.example.myservice.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarTest {

    @Test
    public void testCarConstructor() {
        Car car = new Car("ABC123", "Toyota", "Yaris", 65.0, true);
        assertEquals("ABC123", car.getPlateNumber());
        assertEquals("Toyota", car.getBrand());
        assertEquals("Yaris", car.getModel());
        assertEquals(65.0, car.getDailyRate());
        assertTrue(car.isAvailable());
    }

    @Test
    public void testSetPlateNumber() {
        Car car = new Car("ABC123", "Toyota", "Yaris", 65.0, true);
        car.setPlateNumber("XYZ789");
        assertEquals("XYZ789", car.getPlateNumber());
    }

    @Test
    public void testSetBrand() {
        Car car = new Car("ABC123", "Toyota", "Yaris", 65.0, true);
        car.setBrand("Honda");
        assertEquals("Honda", car.getBrand());
    }

    @Test
    public void testSetModel() {
        Car car = new Car("ABC123", "Toyota", "Yaris", 65.0, true);
        car.setModel("Corolla");
        assertEquals("Corolla", car.getModel());
    }

    @Test
    public void testSetDailyRate() {
        Car car = new Car("ABC123", "Toyota", "Yaris", 65.0, true);
        car.setDailyRate(72.5);
        assertEquals(72.5, car.getDailyRate());
    }

    @Test
    public void testSetAvailability() {
        Car car = new Car("ABC123", "Toyota", "Yaris", 65.0, true);
        car.setAvailable(false);
        assertFalse(car.isAvailable());
    }

    @Test
    public void testToString() {
        Car car = new Car("ABC123", "Toyota", "Yaris", 65.0, true);
        String expected = "Car{plateNumber='ABC123', brand='Toyota', model='Yaris', dailyRate=65.0, available=true}";
        assertEquals(expected, car.toString());
    }
}
