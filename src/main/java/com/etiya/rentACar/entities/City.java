package com.etiya.rentACar.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "returnCity")
    private List<Rental> returnRentals;

    @OneToMany(mappedBy = "rentCity")
    private List<Rental> rentRentals;

    @OneToMany(mappedBy = "city")
    private List<Car> cars;

    @OneToMany(mappedBy = "returnCity")
    private List<Payment> returnPayments;

    @OneToMany(mappedBy = "rentCity")
    private List<Payment> rentPayments;


}