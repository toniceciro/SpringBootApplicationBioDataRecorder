package com.toniceciro.springbootaccessapplication;

import com.toniceciro.springbootaccessapplication.entity.SpringAccessDbJoinEntityModel;
import com.toniceciro.springbootaccessapplication.model.requestBody;
import com.toniceciro.springbootaccessapplication.model.responseEntryBody;
import com.toniceciro.springbootaccessapplication.repository.SpringAccessDbJoinJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class getEntryService {
    @Autowired
    protected SpringAccessDbJoinJpaRepository springAccessDbJoinJpaRepository;

    public responseEntryBody getAllEntries(){
        responseEntryBody response = new responseEntryBody();
        response.setData(selectAll());
        return response;
    }
    public responseEntryBody getEntry(requestBody data){
        responseEntryBody response = new responseEntryBody();
        response.setData(selectOne(data.getUser_id()));
        return response;
    }
    private List<SpringAccessDbJoinEntityModel> selectAll(){
        List<SpringAccessDbJoinEntityModel> data;
        try{
            data = springAccessDbJoinJpaRepository.getAllEntries();
        }catch(Exception e){
            throw new RuntimeException("Error in querying data...");
        }
        return data;
    }
    private List<SpringAccessDbJoinEntityModel> selectOne(Integer user_id){
        List<SpringAccessDbJoinEntityModel> data;
        Optional<SpringAccessDbJoinEntityModel> entry;
        try{
            entry = springAccessDbJoinJpaRepository.getEntry(user_id);
            if((entry.isEmpty())){
                throw new RuntimeException("Entry does not exist");
            }
            data = List.of(springAccessDbJoinJpaRepository.getEntry(user_id).get());
        }catch(Exception e){
            throw new RuntimeException("Error in querying data...");
        }
        return data;
    }

}
