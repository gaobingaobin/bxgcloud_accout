package com.bxgcloud.repository.customize.Impl;


import com.bxgcloud.model.bill.PersonBill;
import com.bxgcloud.repository.customize.PersonBillCustomize;
import com.bxgcloud.util.StringUtil;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * Created by gaobin on 2016/11/9.
 */
@Repository
 class PersonBillImpl implements PersonBillCustomize {
    @PersistenceContext//(unitName = "JPA")
    private EntityManager em;

    @Override
    public Long findPersonBillSizeByPage(Map<String, String> conditions) {
        StringBuffer sql = new StringBuffer();
        sql.append("select  count(p) from PersonBill p where 1=1 ");
        Query query = em.createQuery(sql.toString());
        return (long)query.getSingleResult();

    }

    @Override
    public List<PersonBill> findPersonBillByPage(Map<String, String> conditions) {
        StringBuffer sql = new StringBuffer();
        sql.append("from PersonBill p where 1=1");
        if(StringUtil.isNotBlank(conditions.get("sidx"))&&StringUtil.isNotBlank(conditions.get("sord"))){
            sql.append("order by p."+conditions.get("sidx")+" "+conditions.get("sord"));
        }
        Integer currentPage = 0;
        Integer pageSize = 0;
        if (StringUtil.isNotBlank(conditions.get("page"))) {
            currentPage = Integer.parseInt(conditions.get("page"));
        }
        if (StringUtil.isNotBlank(conditions.get("size"))) {
            pageSize = Integer.parseInt(conditions.get("size"));
        }
        Query query = em.createQuery(sql.toString());
        if (pageSize > 0) {
            query.setFirstResult((currentPage - 1) * pageSize);
            query.setMaxResults(pageSize);
        }
        List result = query.getResultList();
        return result;
    }
}
