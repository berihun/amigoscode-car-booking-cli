package com.innovatecksolutions.model;

import java.math.BigDecimal;

public class Car {
    @Id
    private Long id;
    private String registrationNumber;
    private BigDecimal pricePerDay;
    private Brand brand;
}
