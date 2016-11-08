package com.bxgcloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by gaobin on 2016/11/8.
 */
@Controller
@RequestMapping("/user")
public class UserController {


    @RequestMapping("record")
    public String getRecordPage(){
        return "user/user_record";
    }
}
