package com.innovatecksolutions.repository;

import com.innovatecksolutions.model.Car;

import java.util.List;

public interface CarRepository {
//    void save(Car car);
//    void update(Car car);
//    void delete(Car car);
    List<Car> findAllCars();
}
