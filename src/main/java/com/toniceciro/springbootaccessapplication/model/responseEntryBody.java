package com.toniceciro.springbootaccessapplication.model;

import com.toniceciro.springbootaccessapplication.entity.SpringAccessDbJoinEntityModel;
import lombok.Data;

import java.util.List;
@Data
public class responseEntryBody {
    List<SpringAccessDbJoinEntityModel> data;
}
