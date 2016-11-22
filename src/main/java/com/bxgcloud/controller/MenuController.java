package com.bxgcloud.controller;

import com.bxgcloud.util.userUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by gaobin on 2016/11/7.
 */
@Controller
@RequestMapping("/page")
public class MenuController {

  /**
   * 记账系统主页
    */
    @RequestMapping("/kuaidi_menu")
    public String pageMenu(){
        return "kuaidi";
    }

    @RequestMapping("/user_menu")
    public String userMenu(){
        return "user/user_menu";
    }
    @RequestMapping("/personbill_menu")
    public String personBillMenu(){
        return "personbill/personbill_menu";
    }
    @RequestMapping("/websocket_menu")
    public String webScoket(HttpServletRequest request,Model model){
       model.addAttribute("userinfo", userUtil.getCurrentUser(request));
        return "websocket";
    }
    @RequestMapping("/commonbill_menu")
    public String commonbillMenu(){
        return "commonbill/commonbill_menu";
    }
}
