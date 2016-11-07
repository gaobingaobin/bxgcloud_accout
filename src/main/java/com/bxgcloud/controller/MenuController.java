package com.bxgcloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by gaobin on 2016/11/7.
 */
@Controller
@RequestMapping("/page")
public class MenuController {

  /**
   * 记账系统主页
    */
    @RequestMapping("/page_menu")
    public String pageMenu(){
        return "user/user_menu";
    }

    @RequestMapping("/user_menu")
    public String userMenu(){
        return "user/user_menu";
    }
}
