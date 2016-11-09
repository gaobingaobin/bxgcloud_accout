package com.bxgcloud.controller;

import com.bxgcloud.model.bill.PersonBill;
import com.bxgcloud.repository.customize.PersonBillCustomize;
import com.bxgcloud.service.PersonBillService;
import com.bxgcloud.util.PageTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
}
