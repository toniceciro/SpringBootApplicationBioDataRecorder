package com.toniceciro.springbootaccessapplication.model;

import com.toniceciro.springbootaccessapplication.entity.SpringAccessDbJoinEntityModel;
import lombok.Data;


@Data
public class responseBody {

    private String status;
    private Integer user_id;
}
