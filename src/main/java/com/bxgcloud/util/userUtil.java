package com.bxgcloud.util;

import com.bxgcloud.model.UserInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by gaobin on 2016/11/15.
 */
public class userUtil {

    public static UserInfo getCurrentUser(HttpServletRequest request){
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("currUser");
        return userInfo;
    }
}
