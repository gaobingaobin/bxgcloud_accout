package com.bxgcloud.repository;


import com.bxgcloud.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016-08-15.
 */
@Repository
public interface UserinfoRepository  extends JpaRepository<UserInfo, Integer>, JpaSpecificationExecutor<UserInfo> {
    UserInfo findByUsernameAndPassword(String username, String password);
    UserInfo findByUsername(String username);
    UserInfo findById(Integer id);
    List<UserInfo> findByIdNot(Integer id);


}
