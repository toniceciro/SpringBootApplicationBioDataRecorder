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
            return response;
        }
        else{
            responseBody response = new responseBody();
            response.setStatus("ERROR: Invalid Input");
            return response;
        }

    }
    //Validate inputs
    private Boolean checkInputs(requestBody request){
    List<String> nameList = List.of(request.getUserFirstName(),request.getUserMiddleName(),request.getUserLastName());
    List<String> addressList = List.of(request.getAddressProvince(),request.getAddressStreetAddress(),request.getAddressCity(),request.getAddressBarangay(),request.getAddressCountry());

    return checkIfAlpha(nameList) &&
            checkBirthDate(request.getBirthDay(),request.getBirthMonth(),request.getBirthYear()) &&
            checkZipCode(request.getAddressZipCode()) &&
            checkIfAlphaNumeric(addressList);
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
    private static Boolean checkIfAlpha(List<String> checkData){
        final Pattern pattern = Pattern.compile("[A-Za-z]+", Pattern.CASE_INSENSITIVE);
        int x = 0;
        while(x < checkData.size()){
            if (pattern.matcher(checkData.get(x).replaceAll("\\s+","")).matches()){
                x++;
            }
            else{
                return false;
            }
        }
        return true;
    }
    private static Boolean checkIfAlphaNumeric(List<String> checkData){
        final Pattern pattern = Pattern.compile("[A-Za-z0-9]+", Pattern.CASE_INSENSITIVE);
        int x = 0;
        while(x < checkData.size()){
            if (pattern.matcher(checkData.get(x).replaceAll("\\s+","")).matches()){
                x++;
            }
            else{
                return false;
            }
        }
        return true;
    }
    private static Boolean checkZipCode(Integer checkData){
        return checkData.toString().length() == 4;
    }
    private static Boolean checkBirthDate(Integer day, String month, Integer year){
        List<String> validMonthList = List.of("January","February","March","April","May","June","July","August","September","October","November","December");
        List<String> month30Days = List.of("April","June","September","November");
        List<String> month31Days = List.of("January","March","May","July","August","October","December");
        if(validMonthList.contains(month)){
            if(month30Days.contains(month)){
                return day <= 30;
            }
            if(month31Days.contains(month)){
                return day <= 31;
            }
            //Check if leap year
            if(year % 4 == 0 || year % 400 == 0){
                return day <= 29;
            }
            //If not leap year
            else{
                return day <= 28;
            }
        }
        return false;
    }
}
