package com.bxgcloud.controller;

import com.bxgcloud.model.UserInfo;
import com.bxgcloud.repository.UserinfoRepository;
import com.bxgcloud.service.UserinfoService;
import com.bxgcloud.util.PageTool;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by gaobin on 2016/11/8.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserinfoService userinfoService;
    @Autowired
    private UserinfoRepository userinfoRepository;



    @RequestMapping("/record")
    public String getRecordPage(){
        return "user/user_record";
    }

    /**
     * 获取所有用户
    */
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public PageTool getUserInfo(){
        List<UserInfo> list = userinfoRepository.findAll();
        Integer currentPage = Integer.parseInt("1");
        PageTool pageTool = new PageTool(currentPage, list.size(), list.size(), list);
        return pageTool;
    }
    /**
     * 添加用户页面
    */
    @RequestMapping("/addUserInfoPage")
    public String addUserInfo(HttpServletRequest request){
        return "user/user_add";

    }

    @RequestMapping("/getKuaidiMessage")
    @ResponseBody
    public String getKuaidiMessage() {
        String sendUrl = "http://www.kuaidi100.com/query";
        HttpClient httpclient = null;
        PostMethod post = null;
        String type = "zhongtong";
        String postid = "417151308174";
        String info = null;
        try {
            httpclient = new HttpClient();
            post = new PostMethod(sendUrl);
            //设置编码方式
            post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
            //添加参数
            post.addParameter("type", type);
            post.addParameter("postid", postid);
            httpclient.executeMethod(post);
            info = new String(post.getResponseBody(), "UTF-8");
            System.out.print(info);


        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            //关闭连接，释放资源
            post.releaseConnection();
            ((SimpleHttpConnectionManager) httpclient.getHttpConnectionManager()).shutdown();
        }

        return info;
    }


}
