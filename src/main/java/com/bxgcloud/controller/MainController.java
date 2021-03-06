package com.bxgcloud.controller;


import com.bxgcloud.model.Menu;
import com.bxgcloud.model.UserInfo;
import com.bxgcloud.repository.UserinfoRepository;
import com.bxgcloud.service.MenuService;
import com.bxgcloud.service.UserinfoService;
import com.bxgcloud.util.StringUtil;
import com.bxgcloud.util.userUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

/**
 * Created by gaobin on 2016/8/12.
 */
@Controller
public class MainController {
    @Autowired
    private UserinfoService userinfoService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private UserinfoRepository userinfoRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "login";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (StringUtil.isNotBlank(username) && StringUtil.isNotBlank("password")) {
            UserInfo user = userinfoService.findByUsernameAndPassword(username, password);
            if (user == null) {
                request.setAttribute("loginMessage", "用户名或密码错误！");
                return "login";
            } else {
                request.getSession().setAttribute("currUser", user);
                return "redirect:main.html";
            }
        } else {
            request.setAttribute("loginMessage", "用户名和密码不能为空");
            return "login";
        }

    }

    @RequestMapping("main.html")
    public String main(HttpServletRequest request, Model model) throws ParseException {
        List<Menu> menus = menuService.findAllMenu();
        if (null != menus.get(0).getUrl() && !"".equals(menus.get(0).getUrl())) {
            request.setAttribute("defaultMainPage", menus.get(0).getUrl());
        }
        request.setAttribute("menus", menus);
        UserInfo userInfo = userUtil.getCurrentUser(request);
        List<UserInfo> userInfos = userinfoRepository.findByIdNot(userInfo.getId());
        model.addAttribute("userInfos",userInfos);
        return "fault_operator_index";
    }
    @RequestMapping("/logout")
    public String loginOut(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "login";
    }

}