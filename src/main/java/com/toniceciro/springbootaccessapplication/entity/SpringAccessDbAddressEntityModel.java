package com.toniceciro.springbootaccessapplication.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "ADDRESS_LIST")
@Table(schema = "SPRING_ACCESS_DB")
public class SpringAccessDbAddressEntityModel {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer user_id;
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "STREET_NUMBER")
    private String streetNumber;
    @Column(name = "STREET_ADDRESS")
    private String streetAddress;
    @Column(name = "BARANGAY")
    private String barangay;
    @Column(name = "CITY")
    private String city;
    @Column(name = "PROVINCE")
    private String province;
    @Column(name = "ZIP_CODE")
    private Integer zipCode;
}
