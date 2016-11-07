package com.bxgcloud.service;


import com.bxgcloud.model.UserInfo;
import com.bxgcloud.repository.UserinfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016-08-15.
 */
@Service
public class UserinfoService {
    @Autowired
    private UserinfoRepository userinfoRepository;

    public UserInfo findByUsernameAndPassword(String username, String password){
        return userinfoRepository.findByUsernameAndPassword(username,password);

    }


}
