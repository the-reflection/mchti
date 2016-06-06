package org.reflection.service;

import org.reflection.model.sample.ZxEmp;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.ZxEmpNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("zxEmpService")
@Transactional(readOnly = true)
public class ZxEmpServiceImpl implements ZxEmpService {

    @Autowired
    private SessionFactory sessionFactory;
       
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional(readOnly = true)
    @Override
    public ZxEmp findByCode(String code) {
        return (ZxEmp) getCurrentSession().createQuery("FROM ZxEmp m WHERE m.code='"+code+"'").uniqueResult();
    }

    @Transactional
    @Override
    public ZxEmp create(ZxEmp zxEmp) {
        getCurrentSession().save(zxEmp);
        return zxEmp;
    }
    
    @Override
    @Transactional
    public ZxEmp findById(BigInteger id) {
        ZxEmp zxEmp = (ZxEmp) getCurrentSession().get(ZxEmp.class, id);
        
            Hibernate.initialize(zxEmp.getZxLookupBloodGroup());
            Hibernate.initialize(zxEmp.getZxEmpEduDtls());

        //Hibernate.initialize(zxEmp.getHrTlShftDtlId());
        //Hibernate.initialize(zxEmp.getPersonEduDtlList());
        return zxEmp;
    }
    
    @Override
    @Transactional(rollbackFor = ZxEmpNotFoundException.class)
    public ZxEmp delete(BigInteger id) throws ZxEmpNotFoundException {
           
        ZxEmp zxEmp = (ZxEmp) getCurrentSession().get(ZxEmp.class, id);
            
        if (zxEmp == null) {
            throw new ZxEmpNotFoundException();
        }
    
        getCurrentSession().delete(zxEmp);
        return zxEmp;
    }
    
    @Override
    @Transactional
    public List<ZxEmp> findAll() {
        List<ZxEmp> zxEmps =getCurrentSession().createQuery("FROM " + ZxEmp.class.getName()).list();
            
        for (ZxEmp zxEmp : zxEmps) {
            Hibernate.initialize(zxEmp.getZxLookupBloodGroup());
            Hibernate.initialize(zxEmp.getZxEmpEduDtls());

            //Hibernate.initialize(zxEmp.getHrTlShftDtlId());
            //Hibernate.initialize(zxEmp.getPersonEduDtlList());
        }
        return zxEmps;
    }
      
    @Transactional(rollbackFor = ZxEmpNotFoundException.class)
    @Override
    public ZxEmp update(ZxEmp updated) throws ZxEmpNotFoundException {

        ZxEmp zxEmp = (ZxEmp) getCurrentSession().get(ZxEmp.class, updated.getId());

        if (zxEmp == null) {
            throw new ZxEmpNotFoundException();
        }

        try {
            PropertyUtils.copyProperties(zxEmp, updated);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 ZxEmp update: "+ e);
        }

        getCurrentSession().save(zxEmp);
        return updated;
    }
    
    @Transactional(rollbackFor = ZxEmpNotFoundException.class)
    @Override
    public ZxEmp copy(final ZxEmp copied) {
    
        ZxEmp zxEmp = new ZxEmp();//(ZxEmp) getCurrentSession().get(ZxEmp.class, copied.getId());
    
        try {
            PropertyUtils.copyProperties(zxEmp, copied);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 ZxEmp copy: "+ e);
        }
    
        getCurrentSession().save(zxEmp);
        return zxEmp;
    }
    
    @Transactional
    @Override
    public List<ZxEmp> findAll(_SearchDTO pageable) {

        Session session = getCurrentSession();

        Query query = session.createQuery("FROM ZxEmp m");
        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<ZxEmp> zxEmps = (List<ZxEmp>) query.list();
        for (ZxEmp zxEmp : zxEmps) {
            Hibernate.initialize(zxEmp.getZxLookupBloodGroup());
            Hibernate.initialize(zxEmp.getZxEmpEduDtls());

        //Hibernate.initialize(zxEmp.getHrTlShftDtlId());
        //Hibernate.initialize(zxEmp.getPersonEduDtlList());
        }

        Long totRecs = (Long) session.createQuery("SELECT COUNT(m) FROM ZxEmp m").uniqueResult();

        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);
        return zxEmps;

    //Criteria criteria = getCurrentSession().createCriteria(ZxEmp.class);
    //criteria.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
    //criteria.setMaxResults(pageable.getPageSize());
    //
    //List<ZxEmp> zxEmps = (List<ZxEmp>) criteria.list();
    //
    //int totRecs = 27;
    //
    //pageable.setTotalPages(totRecs / pageable.getPageSize() + 1);
    //pageable.setTotalRecs(totRecs);  
    }
    
    @Transactional
    @Override
    public List<ZxEmp> search(_SearchDTO pageable) {
        
        String searchTerm = pageable.getSearchTerm().toUpperCase();
        Session session = getCurrentSession();

        //String qu = "FROM ZxEmp m WHERE 1=1 AND UPPER(m.title) LIKE UPPER(CONCAT('%',:search,'%'))";
        String qu = "FROM ZxEmp m WHERE 1=1  AND UPPER(CONCAT(m.remarks, m.code, m.fullName)) LIKE CONCAT('%',:search,'%')";

        Query queryCount = session.createQuery("SELECT COUNT(m) " + qu);
        queryCount.setParameter("search", searchTerm);
        Long totRecs = (Long) queryCount.uniqueResult();
        
        Query query = session.createQuery(qu);
        query.setParameter("search", searchTerm);

        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<ZxEmp> zxEmps = (List<ZxEmp>) query.list();

        for (ZxEmp zxEmp : zxEmps) {
            Hibernate.initialize(zxEmp.getZxLookupBloodGroup());
            Hibernate.initialize(zxEmp.getZxEmpEduDtls());

            //Hibernate.initialize(zxEmp.getHrTlShftDtlId());
            //Hibernate.initialize(zxEmp.getPersonEduDtlList());
        }
        
        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);

        return zxEmps;
    }
}
