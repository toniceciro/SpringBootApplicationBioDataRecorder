package com.toniceciro.springbootaccessapplication.repository;

import com.toniceciro.springbootaccessapplication.entity.SpringAccessDbAddressEntityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SpringAccessDbAddressJpaRepository extends JpaRepository<SpringAccessDbAddressEntityModel,Integer>,
        JpaSpecificationExecutor<SpringAccessDbAddressEntityModel> {

    @Modifying
    @Query(nativeQuery = true, value = "insert into SPRING_ACCESS_DB.address_list (street_address,street_number,barangay,city,province,zip_code,country,user_id) values " +
            "(?1,?2,?3,?4,?5,?6,?7,?8)")
    Integer createAddressEntry(String streetAddress,Integer streetNumber, String barangay, String city, String province, Integer zipCode, String country, Integer user_id);
}
