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



    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;


    @ManyToOne
    @JoinColumn(name="returnCityId",referencedColumnName = "id")
    private City returnCity;

    @ManyToOne
    @JoinColumn(name="rentCityId",referencedColumnName = "id")
    private City rentCity;


    @OneToMany(mappedBy="rental")
    private List<Invoice> invoices;

    @OneToMany(mappedBy="rental")
    private List<Payment> payments;


    @Column(name="totalPrice")
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name="car_id")
    private Car car;

    @Column(name="startKilometer")
    private double startKilometer;


    @Column(name="returnKilometer")
    private double returnKilometer;



}