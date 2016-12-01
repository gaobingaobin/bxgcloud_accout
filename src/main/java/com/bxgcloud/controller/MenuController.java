package com.bxgcloud.controller;

import com.bxgcloud.model.UserInfo;
import com.bxgcloud.repository.UserinfoRepository;
import com.bxgcloud.util.userUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by gaobin on 2016/11/7.
 */
@Controller
@RequestMapping("/page")
public class MenuController {
    @Autowired
    private UserinfoRepository userinfoRepository;

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
    @RequestMapping("/chat_menu")
    public String chat(HttpServletRequest request,Model model){
        List<UserInfo> userInfos = userinfoRepository.findAll();
        model.addAttribute("userInfos", userInfos);
        return "chat";
    }
    @RequestMapping("/commonbill_menu")
    public String commonbillMenu(){
        return "commonbill/commonbill_menu";
    }

    @RequestMapping("/chatPage")
    public String chatPage(HttpServletRequest request,Model model){
        String toUsername = request.getParameter("toUsername");
        UserInfo userInfo = userinfoRepository.findByUsername(toUsername);
        model.addAttribute("userInfo",userInfo);
        return "chatPage";
    }
}
