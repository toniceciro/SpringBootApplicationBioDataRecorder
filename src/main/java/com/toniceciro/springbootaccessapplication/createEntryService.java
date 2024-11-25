package com.toniceciro.springbootaccessapplication;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.toniceciro.springbootaccessapplication.repository.*;
import com.toniceciro.springbootaccessapplication.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Transactional
@Service
public class createEntryService {
    //Initialize Jpa Repositories
    @Autowired
    protected SpringAccessDbUserJpaRepository springAccessDbUserJpaRepository;
    @Autowired
    protected SpringAccessDbAddressJpaRepository springAccessDbAddressJpaRepository;
    @Autowired
    protected SpringAccessDbBirthJpaRepository springAccessDbBirthJpaRepository;

    public responseBody createNewEntry(requestBody data) {
        //validate inputs
        Boolean isInputValidated = checkInputs(data);
        if (isInputValidated){
            //create user & get user_id primary key value
            Integer userID = createNewUser(data.getUserFirstName(), data.getUserMiddleName(), data.getUserLastName());
            //insert address with obtained userId
            insertAddressValues(data.getAddressStreetAddress(), data.getAddressStreetNumber(),data.getAddressBarangay(), data.getAddressCity(),data.getAddressProvince(),data.getAddressZipCode(),data.getAddressCountry(),userID);
            insertBirthData(data.getBirthDay(),data.getBirthYear(),data.getBirthMonth(),userID);
            //If no runtime exceptions, return response
            responseBody response = new responseBody();
            response.setStatus("OK");
            response.setUser_id(userID);
            System.out.println("Success!");
            return response;
        }
        else{
            responseBody response = new responseBody();
            response.setStatus("ERROR");
            return response;
        }

    }
    //Validate inputs
    private Boolean checkInputs(requestBody request){
                return true;
    }
    //Functions to abstract DB operations
    private Integer createNewUser(String first_name, String middle_name, String last_name) {
        try{
            springAccessDbUserJpaRepository.createEntry(first_name,middle_name,last_name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return springAccessDbUserJpaRepository.getId();
    }
    private void insertAddressValues(String streetAddress, Integer streetNumber,String barangay, String city,
                                     String province, Integer zipCode, String country,
                                     Integer user_id) {
        Integer affectedRows;
        try{
            affectedRows = springAccessDbAddressJpaRepository.createAddressEntry(streetAddress,streetNumber,city,barangay,city,zipCode,country,user_id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (affectedRows == 0){
            throw new RuntimeException("No rows were updated, check DB?");
        }
    }
    private void insertBirthData(Integer birthDay, Integer birthYear, String birthMonth, Integer user_id) {
        Integer affectedRows;
        try{
            affectedRows = springAccessDbBirthJpaRepository.createBirthEntry(birthDay,birthYear,birthMonth,user_id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (affectedRows == 0){
            throw new RuntimeException("No rows were updated, check DB?");
        }
    }
    //Regex Validators
    private static Boolean checkIfAlpha(String checkData){
        final Pattern pattern = Pattern.compile("[A-Za-z]+", Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(checkData);
        return matcher.matches();
    }
    private static Boolean checkIfAlphaNumeric(String checkData){
        final Pattern pattern = Pattern.compile("[A-Za-z0-9]+", Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(checkData);
        return matcher.matches();
    }
    private static Boolean checkZipCode(Integer checkData){
        return checkData.toString().length() == 4;
    }
}
