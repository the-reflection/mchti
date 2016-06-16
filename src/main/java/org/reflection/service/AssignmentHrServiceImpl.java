package org.reflection.service;

import org.reflection.model.hcm.cr.AssignmentHr;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.AssignmentHrNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("assignmentHrService")
@Transactional(readOnly = true)
public class AssignmentHrServiceImpl implements AssignmentHrService {

    @Autowired
    private SessionFactory sessionFactory;
    
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }



    @Transactional
    @Override
    public AssignmentHr create(AssignmentHr assignmentHr) {
        getCurrentSession().save(assignmentHr);
        return assignmentHr;
    }
    
    @Override
    @Transactional
    public AssignmentHr findById(BigInteger id) {
        AssignmentHr assignmentHr = (AssignmentHr) getCurrentSession().get(AssignmentHr.class, id);
        
            Hibernate.initialize(assignmentHr.getEmployee());
            Hibernate.initialize(assignmentHr.getDepartment());
            Hibernate.initialize(assignmentHr.getDesignation());

        //Hibernate.initialize(assignmentHr.getHrTlShftDtlId());
        //Hibernate.initialize(assignmentHr.getPersonEduDtlList());
        return assignmentHr;
    }
    
    @Override
    @Transactional(rollbackFor = AssignmentHrNotFoundException.class)
    public AssignmentHr delete(BigInteger assignmentHrId) throws AssignmentHrNotFoundException {
           
        AssignmentHr assignmentHr = (AssignmentHr) getCurrentSession().get(AssignmentHr.class, assignmentHrId);
            
        if (assignmentHr == null) {
            throw new AssignmentHrNotFoundException();
        }
    
        getCurrentSession().delete(assignmentHr);
        return assignmentHr;
    }
    
    @Override
    @Transactional
    public List<AssignmentHr> findAll() {
        List<AssignmentHr> assignmentHrs =getCurrentSession().createQuery("FROM " + AssignmentHr.class.getName()).list();
            
        for (AssignmentHr assignmentHr : assignmentHrs) {
            Hibernate.initialize(assignmentHr.getEmployee());
            Hibernate.initialize(assignmentHr.getDepartment());
            Hibernate.initialize(assignmentHr.getDesignation());

            //Hibernate.initialize(assignmentHr.getHrTlShftDtlId());
            //Hibernate.initialize(assignmentHr.getPersonEduDtlList());
        }
        return assignmentHrs;
    }
      
    @Transactional(rollbackFor = AssignmentHrNotFoundException.class)
    @Override
    public AssignmentHr update(AssignmentHr updated) throws AssignmentHrNotFoundException {

        AssignmentHr assignmentHr = (AssignmentHr) getCurrentSession().get(AssignmentHr.class, updated.getId());

        if (assignmentHr == null) {
            throw new AssignmentHrNotFoundException();
        }

        try {
            PropertyUtils.copyProperties(assignmentHr, updated);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 AssignmentHr update: "+ e);
        }

        getCurrentSession().save(assignmentHr);
        return updated;
    }
    
    @Transactional(rollbackFor = AssignmentHrNotFoundException.class)
    @Override
    public AssignmentHr copy(final AssignmentHr copied) {
    
        AssignmentHr assignmentHr = new AssignmentHr();//(AssignmentHr) getCurrentSession().get(AssignmentHr.class, copied.getId());
    
        try {
            PropertyUtils.copyProperties(assignmentHr, copied);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 AssignmentHr copy: "+ e);
        }
    
        getCurrentSession().save(assignmentHr);
        return assignmentHr;
    }
    
    @Transactional
    @Override
    public List<AssignmentHr> findAll(_SearchDTO pageable) {

        Session session = getCurrentSession();

        Query query = session.createQuery("FROM AssignmentHr m");
        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<AssignmentHr> assignmentHrs = (List<AssignmentHr>) query.list();
        for (AssignmentHr assignmentHr : assignmentHrs) {
            Hibernate.initialize(assignmentHr.getEmployee());
            Hibernate.initialize(assignmentHr.getDepartment());
            Hibernate.initialize(assignmentHr.getDesignation());

        //Hibernate.initialize(assignmentHr.getHrTlShftDtlId());
        //Hibernate.initialize(assignmentHr.getPersonEduDtlList());
        }

        Long totRecs = (Long) session.createQuery("SELECT count(m) FROM AssignmentHr m").uniqueResult();

        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);
        return assignmentHrs;

    //Criteria criteria = getCurrentSession().createCriteria(AssignmentHr.class);
    //criteria.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
    //criteria.setMaxResults(pageable.getPageSize());
    //
    //List<AssignmentHr> assignmentHrs = (List<AssignmentHr>) criteria.list();
    //
    //int totRecs = 27;
    //
    //pageable.setTotalPages(totRecs / pageable.getPageSize() + 1);
    //pageable.setTotalRecs(totRecs);  
    }
    
    @Transactional
    @Override
    public List<AssignmentHr> search(_SearchDTO pageable) {
        
        String searchTerm = pageable.getSearchTerm().toUpperCase();
        Session session = getCurrentSession();

        //String qu = "FROM AssignmentHr m WHERE 1=1 AND UPPER(m.title) LIKE UPPER(CONCAT('%',:search,'%'))";
        String qu = "FROM AssignmentHr m WHERE 1=1  AND UPPER(CONCAT(m.code)) LIKE CONCAT('%',:search,'%')";

        Query queryCount = session.createQuery("SELECT count(m) " + qu);
        queryCount.setParameter("search", searchTerm);
        Long totRecs = (Long) queryCount.uniqueResult();
        
        Query query = session.createQuery(qu);
        query.setParameter("search", searchTerm);

        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<AssignmentHr> assignmentHrs = (List<AssignmentHr>) query.list();

        for (AssignmentHr assignmentHr : assignmentHrs) {
            Hibernate.initialize(assignmentHr.getEmployee());
            Hibernate.initialize(assignmentHr.getDepartment());
            Hibernate.initialize(assignmentHr.getDesignation());

            //Hibernate.initialize(assignmentHr.getHrTlShftDtlId());
            //Hibernate.initialize(assignmentHr.getPersonEduDtlList());
        }
        
        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);

        return assignmentHrs;
    }
}
