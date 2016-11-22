package com.bxgcloud.controller;

import com.bxgcloud.model.UserInfo;
import com.bxgcloud.repository.UserinfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;

/**
 * Created by gaobin on 2016/11/22.
 */
@Controller
@RequestMapping("/commonbill")
public class CommonBillController {
    @Autowired
    private UserinfoRepository userinfoRepository;
    @RequestMapping("/record")
    public String getRecordPage(){
        return "commonbill/commonbill_record";
    }
    @RequestMapping("/addCommonBillPage")
    public String getAddCommonBillPage(){
        return "commonbill/commonbill_add";
    }
    @RequestMapping("/apply")
    public String getApplyPage(Model model){
        List<UserInfo> userlist = userinfoRepository.findAll();
        model.addAttribute("userlist",userlist);
        return "commonbill/commonbill_apply";
    }


}
