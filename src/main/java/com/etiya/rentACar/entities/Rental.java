package com.etiya.rentACar.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="rentals")
@Entity
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="rentDate")
    private LocalDate rentDate;

    @Column(name="returnDate")
    private LocalDate returnDate;

    @Column(name="rentCity")
    private String rentCity;

    @Column(name = "deliveryCity")
    private String deliveryCity;

    @Column(name="totalPrice")
    private double totalPrice;

    @Column(name="lateFee")
    private double lateFee;

    @ManyToOne
    @JoinColumn(name="car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "rental")
    private List<AdditionalProperty> additionalProperties;

}