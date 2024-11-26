package com.toniceciro.springbootaccessapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.toniceciro.springbootaccessapplication.entity.SpringAccessDbJoinEntityModel;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SpringAccessDbJoinJpaRepository extends JpaRepository<SpringAccessDbJoinEntityModel,Integer>,
        JpaSpecificationExecutor<SpringAccessDbJoinEntityModel>{

    @Query(nativeQuery = true, value = "select spring_access_db.user_list.user_id,first_name,middle_name,last_name,country,street_address,barangay,city,province,zip_code,street_number,birth_day,birth_month,birth_year " +
            "from spring_access_db.user_list " +
            "inner join spring_access_db.address_list " +
            "on spring_access_db.user_list.user_id = spring_access_db.address_list.user_id " +
            "inner join spring_access_db.birth_list " +
            "on spring_access_db.user_list.user_id = spring_access_db.birth_list.user_id")
    List<SpringAccessDbJoinEntityModel> getAllEntries();

    @Query(nativeQuery = true, value = "select spring_access_db.user_list.user_id,first_name,middle_name,last_name,country,street_address,barangay,city,province,zip_code,street_number,birth_day,birth_month,birth_year " +
            "from spring_access_db.user_list " +
            "inner join spring_access_db.address_list " +
            "on spring_access_db.user_list.user_id = spring_access_db.address_list.user_id " +
            "inner join spring_access_db.birth_list " +
            "on spring_access_db.user_list.user_id = spring_access_db.birth_list.user_id " +
            "where spring_access_db.user_list.user_id = ?1")
    Optional<SpringAccessDbJoinEntityModel> getEntry(Integer user_id);
}
