package com.etiya.rentACar.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="rentDate")
    private LocalDate rentDate;

    @Column(name="returnDate")
    private LocalDate returnDate;

    @Column(name="totalPrice")
    private double totalPrice;

    @Column(name = "cardNumber")
    private String cardNumber;

    @Column(name = "cardFirstName")
    private String cardFirstName;

    @Column(name = "cardLastName")
    private String cardLastName;

    @Column(name = "expirationDate")
    private String expirationDate;

    @Column(name = "cvc")
    private String cvc;

    @ManyToOne
    @JoinColumn(name="car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name="OrderedAdditionalProperty_id")
    private OrderedAdditionalProperty orderedAdditionalProperty;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;


    @ManyToOne
    @JoinColumn(name="returnCityId",referencedColumnName = "id")
    private City returnCity;

    @ManyToOne
    @JoinColumn(name="rentCityId",referencedColumnName = "id")
    private City rentCity;

    @ManyToOne//OnetoOne olmalı mı?
    @JoinColumn(name="invoice_id")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name="rental_id")
    private Rental rental;

    @Column(name = "dayCount")
    private int dayCount;

}
