package com.bxgcloud.service;

import com.bxgcloud.model.bill.PersonBill;
import com.bxgcloud.repository.customize.PersonBillCustomize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by gaobin on 2016/11/9.
 */
@Service
public class PersonBillService {
    @Autowired
    PersonBillCustomize personBillCustomize;


    public Long findPersonBillSizeByPage(Map<String, String> conditions) {
        return personBillCustomize.findPersonBillSizeByPage(conditions);
    }

    public List<PersonBill> findPersonBillByPage(Map<String, String> conditions) {
        return personBillCustomize.findPersonBillByPage(conditions);
    }
}
