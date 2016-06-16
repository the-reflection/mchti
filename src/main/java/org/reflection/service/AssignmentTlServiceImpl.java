package org.reflection.service;

import org.reflection.model.hcm.tl.AssignmentTl;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.AssignmentTlNotFoundException;
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

@Service("assignmentTlService")
@Transactional(readOnly = true)
public class AssignmentTlServiceImpl implements AssignmentTlService {

    @Autowired
    private SessionFactory sessionFactory;
    
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }



    @Transactional
    @Override
    public AssignmentTl create(AssignmentTl assignmentTl) {
        getCurrentSession().save(assignmentTl);
        return assignmentTl;
    }
    
    @Override
    @Transactional
    public AssignmentTl findById(BigInteger id) {
        AssignmentTl assignmentTl = (AssignmentTl) getCurrentSession().get(AssignmentTl.class, id);
        
            Hibernate.initialize(assignmentTl.getEmployee());
            Hibernate.initialize(assignmentTl.getShift());
            Hibernate.initialize(assignmentTl.getRoster());

        //Hibernate.initialize(assignmentTl.getHrTlShftDtlId());
        //Hibernate.initialize(assignmentTl.getPersonEduDtlList());
        return assignmentTl;
    }
    
    @Override
    @Transactional(rollbackFor = AssignmentTlNotFoundException.class)
    public AssignmentTl delete(BigInteger assignmentTlId) throws AssignmentTlNotFoundException {
           
        AssignmentTl assignmentTl = (AssignmentTl) getCurrentSession().get(AssignmentTl.class, assignmentTlId);
            
        if (assignmentTl == null) {
            throw new AssignmentTlNotFoundException();
        }
    
        getCurrentSession().delete(assignmentTl);
        return assignmentTl;
    }
    
    @Override
    @Transactional
    public List<AssignmentTl> findAll() {
        List<AssignmentTl> assignmentTls =getCurrentSession().createQuery("FROM " + AssignmentTl.class.getName()).list();
            
        for (AssignmentTl assignmentTl : assignmentTls) {
            Hibernate.initialize(assignmentTl.getEmployee());
            Hibernate.initialize(assignmentTl.getShift());
            Hibernate.initialize(assignmentTl.getRoster());

            //Hibernate.initialize(assignmentTl.getHrTlShftDtlId());
            //Hibernate.initialize(assignmentTl.getPersonEduDtlList());
        }
        return assignmentTls;
    }
      
    @Transactional(rollbackFor = AssignmentTlNotFoundException.class)
    @Override
    public AssignmentTl update(AssignmentTl updated) throws AssignmentTlNotFoundException {

        AssignmentTl assignmentTl = (AssignmentTl) getCurrentSession().get(AssignmentTl.class, updated.getId());

        if (assignmentTl == null) {
            throw new AssignmentTlNotFoundException();
        }

        try {
            PropertyUtils.copyProperties(assignmentTl, updated);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 AssignmentTl update: "+ e);
        }

        getCurrentSession().save(assignmentTl);
        return updated;
    }
    
    @Transactional(rollbackFor = AssignmentTlNotFoundException.class)
    @Override
    public AssignmentTl copy(final AssignmentTl copied) {
    
        AssignmentTl assignmentTl = new AssignmentTl();//(AssignmentTl) getCurrentSession().get(AssignmentTl.class, copied.getId());
    
        try {
            PropertyUtils.copyProperties(assignmentTl, copied);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 AssignmentTl copy: "+ e);
        }
    
        getCurrentSession().save(assignmentTl);
        return assignmentTl;
    }
    
    @Transactional
    @Override
    public List<AssignmentTl> findAll(_SearchDTO pageable) {

        Session session = getCurrentSession();

        Query query = session.createQuery("FROM AssignmentTl m");
        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<AssignmentTl> assignmentTls = (List<AssignmentTl>) query.list();
        for (AssignmentTl assignmentTl : assignmentTls) {
            Hibernate.initialize(assignmentTl.getEmployee());
            Hibernate.initialize(assignmentTl.getShift());
            Hibernate.initialize(assignmentTl.getRoster());

        //Hibernate.initialize(assignmentTl.getHrTlShftDtlId());
        //Hibernate.initialize(assignmentTl.getPersonEduDtlList());
        }

        Long totRecs = (Long) session.createQuery("SELECT count(m) FROM AssignmentTl m").uniqueResult();

        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);
        return assignmentTls;

    //Criteria criteria = getCurrentSession().createCriteria(AssignmentTl.class);
    //criteria.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
    //criteria.setMaxResults(pageable.getPageSize());
    //
    //List<AssignmentTl> assignmentTls = (List<AssignmentTl>) criteria.list();
    //
    //int totRecs = 27;
    //
    //pageable.setTotalPages(totRecs / pageable.getPageSize() + 1);
    //pageable.setTotalRecs(totRecs);  
    }
    
    @Transactional
    @Override
    public List<AssignmentTl> search(_SearchDTO pageable) {
        
        String searchTerm = pageable.getSearchTerm().toUpperCase();
        Session session = getCurrentSession();

        //String qu = "FROM AssignmentTl m WHERE 1=1 AND UPPER(m.title) LIKE UPPER(CONCAT('%',:search,'%'))";
        String qu = "FROM AssignmentTl m WHERE 1=1  AND UPPER(CONCAT(m.code)) LIKE CONCAT('%',:search,'%')";

        Query queryCount = session.createQuery("SELECT count(m) " + qu);
        queryCount.setParameter("search", searchTerm);
        Long totRecs = (Long) queryCount.uniqueResult();
        
        Query query = session.createQuery(qu);
        query.setParameter("search", searchTerm);

        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<AssignmentTl> assignmentTls = (List<AssignmentTl>) query.list();

        for (AssignmentTl assignmentTl : assignmentTls) {
            Hibernate.initialize(assignmentTl.getEmployee());
            Hibernate.initialize(assignmentTl.getShift());
            Hibernate.initialize(assignmentTl.getRoster());

            //Hibernate.initialize(assignmentTl.getHrTlShftDtlId());
            //Hibernate.initialize(assignmentTl.getPersonEduDtlList());
        }
        
        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);

        return assignmentTls;
    }
}
