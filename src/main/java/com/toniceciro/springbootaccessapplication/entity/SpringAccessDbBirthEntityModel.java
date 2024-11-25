package com.toniceciro.springbootaccessapplication.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "BIRTH_LIST")
@Table(schema = "SPRING_ACCESS_DB")
public class SpringAccessDbBirthEntityModel {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer user_id;
    @Column(name = "BIRTH_DAY")
    private String birthDay;
    @Column(name = "BIRTH_YEAR")
    private String birthYear;
    @Column(name = "BIRTH_MONTH")
    private String birthMonth;

}
