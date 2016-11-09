package com.bxgcloud.repository.customize;

import com.bxgcloud.model.bill.PersonBill;

import java.util.List;
import java.util.Map;

/**
 * Created by gaobin on 2016/11/9.
 */
public interface PersonBillCustomize {
    Long findPersonBillSizeByPage(Map<String, String> conditions);

    List<PersonBill> findPersonBillByPage(Map<String, String> conditions);
}
