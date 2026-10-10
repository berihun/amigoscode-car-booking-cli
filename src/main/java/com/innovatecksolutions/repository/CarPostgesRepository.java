package com.innovatecksolutions.repository;

import com.innovatecksolutions.DbConfig.DatabaseConnection;
import com.innovatecksolutions.model.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarPostgesRepository implements CarRepository {

    @Override
    public List<Car> findAllCars() {
        String sql= "select * from cars";
        List<Car> carList =new ArrayList<>();

        try(Connection connection= DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();) {

            while (resultSet.next()) {
                Car car = new Car();
                car.setRegistrationNumber(resultSet.getString("registration_no"));
                car.setCarId(resultSet.getString("car_id"));
                car.setPricePerDay(resultSet.getBigDecimal("price"));
//                car.setBrand(resultSet.getString("brand"));
                carList.add(car);
            }

        }catch (Exception e){
            System.out.println("Database error fetching cars: "+e.getMessage());
        }
        return carList;
    }

    // Inside CarPostgesRepository.java
    private Car mapRowToCar(ResultSet rs) throws SQLException {
        Car car = new Car();
        car.setCarId(rs.getString("car_id"));
        car.setRegistrationNumber(rs.getString("registration_no"));

        // Map 'price' column to pricePerDay
        car.setPricePerDay(rs.getBigDecimal("price"));

//        car.setBrand(rs.getString("brand"));
//        car.setCarType(rs.getString("car_type"));
        return car;
    }
}
