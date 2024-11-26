package com.toniceciro.springbootaccessapplication.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity(name = "USER_LIST2")
@Table(schema = "SPRING_ACCESS_DB")
public class SpringAccessDbJoinEntityModel {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer user_id;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "MIDDLE_NAME")
    private String middleName;
    @Column(name = "LAST_NAME")
    private String lastName;

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

    @Column(name = "BIRTH_DAY")
    private String birthDay;
    @Column(name = "BIRTH_YEAR")
    private String birthYear;
    @Column(name = "BIRTH_MONTH")
    private String birthMonth;
}
