package com.toniceciro.springbootaccessapplication.repository;
import com.toniceciro.springbootaccessapplication.entity.SpringAccessDbUserEntityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SpringAccessDbUserJpaRepository extends JpaRepository<SpringAccessDbUserEntityModel,Integer>,
        JpaSpecificationExecutor<SpringAccessDbUserEntityModel>{

    //Inserts values to row
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO spring_access_db.user_list (first_name, middle_name, last_name) " +
            "VALUES (?1, ?2, ?3)")
    Integer createEntry(String first_name, String middle_name, String last_name);
    //Get the user_id value of inserted row's auto increment value

    @Query(nativeQuery = true, value = "SELECT LAST_INSERT_ID()")
    Integer getId();
}
