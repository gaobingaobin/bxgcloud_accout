package com.bxgcloud.controller;

import com.bxgcloud.model.UserInfo;
import com.bxgcloud.model.bill.PersonBill;
import com.bxgcloud.repository.PersonBillRepository;
import com.bxgcloud.repository.UserinfoRepository;
import com.bxgcloud.repository.customize.PersonBillCustomize;
import com.bxgcloud.service.PersonBillService;
import com.bxgcloud.service.UserinfoService;
import com.bxgcloud.util.PageTool;
import com.bxgcloud.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gaobin on 2016/11/9.
 */
@Controller
@RequestMapping("/personbill")
public class PersonBillContorller {

    @Autowired
    private PersonBillService personBillService;
    @Autowired
    private PersonBillRepository personBillRepository;
    @Autowired
    private UserinfoRepository userinfoRepository;

    @RequestMapping("/record")
    public String getRecordPage(){
        return "personbill/personbill_record";
    }

    @RequestMapping("/getPersonBill")
    @ResponseBody
    public PageTool getPersonBill(HttpServletRequest request){
        Map<String, String> conditions = new HashMap<String, String>();
        conditions.put("page", request.getParameter("page"));
        conditions.put("size", request.getParameter("rows"));
        conditions.put("sidx", request.getParameter("sidx"));
        conditions.put("sord", request.getParameter("sord"));
        Long total = personBillService.findPersonBillSizeByPage(conditions);
        List<PersonBill> list = personBillService.findPersonBillByPage(conditions);
        Integer currentPage = Integer.parseInt(request.getParameter("page"));
        Integer pageSize = Integer.parseInt(request.getParameter("rows"));
        PageTool pageTool = new PageTool(currentPage, total.intValue(), pageSize, list);
        return pageTool;
    }


    /**@author gaobin
     * 跳转增加账单页面
    */
    @RequestMapping("/addPersonBillPage")
    public String addPersonBillPage(){
        return "personbill/personbill_add";
    }
    /**@author gaobin
     * 增加个人账单
     */
    @RequestMapping("/addPersonBill")
    public  String addPersonBill(HttpServletRequest request) throws ParseException {
        String userId =request.getParameter("userId");
        String type =request.getParameter("type");
        String money =request.getParameter("money");
        String remark =request.getParameter("remark");
        String billDate =request.getParameter("billDate");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse(billDate);
        PersonBill personBill = new PersonBill();
        UserInfo userInfo = userinfoRepository.findById(Integer.parseInt(userId));
        personBill.setUserInfo(userInfo);
        personBill.setMoney(Float.parseFloat(money));
        personBill.setType(type);
        personBill.setBillDate(date);
        if(StringUtil.isNotBlank(remark)){
            personBill.setRemark(remark);
        }
        personBillRepository.save(personBill);
        return "success";



    }

}
