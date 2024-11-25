package com.toniceciro.springbootaccessapplication.repository;

import com.toniceciro.springbootaccessapplication.entity.SpringAccessDbAddressEntityModel;
import com.toniceciro.springbootaccessapplication.entity.SpringAccessDbBirthEntityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
public interface SpringAccessDbBirthJpaRepository extends JpaRepository<SpringAccessDbBirthEntityModel,Integer>,
        JpaSpecificationExecutor<SpringAccessDbBirthEntityModel> {

    @Modifying
    @Query(nativeQuery = true, value = "insert into SPRING_ACCESS_DB.birth_list " +
            "(birth_day,birth_year,birth_month,user_id) values (?1,?2,?3,?4)")
    Integer createBirthEntry(Integer birthDay,Integer birthYear, String birthMonth, Integer user_id);
}
