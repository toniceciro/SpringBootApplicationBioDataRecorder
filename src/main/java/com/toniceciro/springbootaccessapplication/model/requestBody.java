package com.toniceciro.springbootaccessapplication.model;

import lombok.Data;
@Data
public class requestBody {

    //Request data for User
    private String userFirstName;
    private String userMiddleName;
    private String userLastName;
    //Request data for birth data
    private Integer birthDay;
    private String birthMonth;
    private Integer birthYear;
    //Request data for Address
    private String addressCountry;
    private Integer addressStreetNumber;
    private String addressStreetAddress;
    private String addressBarangay;
    private String addressCity;
    private String addressProvince;
    private Integer addressZipCode;
}
