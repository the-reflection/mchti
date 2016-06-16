package org.reflection.service;

import org.reflection.model.hcm.prl.AssignmentPrl;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.AssignmentPrlNotFoundException;
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

@Service("assignmentPrlService")
@Transactional(readOnly = true)
public class AssignmentPrlServiceImpl implements AssignmentPrlService {

    @Autowired
    private SessionFactory sessionFactory;
    
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }



    @Transactional
    @Override
    public AssignmentPrl create(AssignmentPrl assignmentPrl) {
        getCurrentSession().save(assignmentPrl);
        return assignmentPrl;
    }
    
    @Override
    @Transactional
    public AssignmentPrl findById(BigInteger id) {
        AssignmentPrl assignmentPrl = (AssignmentPrl) getCurrentSession().get(AssignmentPrl.class, id);
        
            Hibernate.initialize(assignmentPrl.getEmployee());

        //Hibernate.initialize(assignmentPrl.getHrTlShftDtlId());
        //Hibernate.initialize(assignmentPrl.getPersonEduDtlList());
        return assignmentPrl;
    }
    
    @Override
    @Transactional(rollbackFor = AssignmentPrlNotFoundException.class)
    public AssignmentPrl delete(BigInteger assignmentPrlId) throws AssignmentPrlNotFoundException {
           
        AssignmentPrl assignmentPrl = (AssignmentPrl) getCurrentSession().get(AssignmentPrl.class, assignmentPrlId);
            
        if (assignmentPrl == null) {
            throw new AssignmentPrlNotFoundException();
        }
    
        getCurrentSession().delete(assignmentPrl);
        return assignmentPrl;
    }
    
    @Override
    @Transactional
    public List<AssignmentPrl> findAll() {
        List<AssignmentPrl> assignmentPrls =getCurrentSession().createQuery("FROM " + AssignmentPrl.class.getName()).list();
            
        for (AssignmentPrl assignmentPrl : assignmentPrls) {
            Hibernate.initialize(assignmentPrl.getEmployee());

            //Hibernate.initialize(assignmentPrl.getHrTlShftDtlId());
            //Hibernate.initialize(assignmentPrl.getPersonEduDtlList());
        }
        return assignmentPrls;
    }
      
    @Transactional(rollbackFor = AssignmentPrlNotFoundException.class)
    @Override
    public AssignmentPrl update(AssignmentPrl updated) throws AssignmentPrlNotFoundException {

        AssignmentPrl assignmentPrl = (AssignmentPrl) getCurrentSession().get(AssignmentPrl.class, updated.getId());

        if (assignmentPrl == null) {
            throw new AssignmentPrlNotFoundException();
        }

        try {
            PropertyUtils.copyProperties(assignmentPrl, updated);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 AssignmentPrl update: "+ e);
        }

        getCurrentSession().save(assignmentPrl);
        return updated;
    }
    
    @Transactional(rollbackFor = AssignmentPrlNotFoundException.class)
    @Override
    public AssignmentPrl copy(final AssignmentPrl copied) {
    
        AssignmentPrl assignmentPrl = new AssignmentPrl();//(AssignmentPrl) getCurrentSession().get(AssignmentPrl.class, copied.getId());
    
        try {
            PropertyUtils.copyProperties(assignmentPrl, copied);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 AssignmentPrl copy: "+ e);
        }
    
        getCurrentSession().save(assignmentPrl);
        return assignmentPrl;
    }
    
    @Transactional
    @Override
    public List<AssignmentPrl> findAll(_SearchDTO pageable) {

        Session session = getCurrentSession();

        Query query = session.createQuery("FROM AssignmentPrl m");
        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<AssignmentPrl> assignmentPrls = (List<AssignmentPrl>) query.list();
        for (AssignmentPrl assignmentPrl : assignmentPrls) {
            Hibernate.initialize(assignmentPrl.getEmployee());

        //Hibernate.initialize(assignmentPrl.getHrTlShftDtlId());
        //Hibernate.initialize(assignmentPrl.getPersonEduDtlList());
        }

        Long totRecs = (Long) session.createQuery("SELECT count(m) FROM AssignmentPrl m").uniqueResult();

        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);
        return assignmentPrls;

    //Criteria criteria = getCurrentSession().createCriteria(AssignmentPrl.class);
    //criteria.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
    //criteria.setMaxResults(pageable.getPageSize());
    //
    //List<AssignmentPrl> assignmentPrls = (List<AssignmentPrl>) criteria.list();
    //
    //int totRecs = 27;
    //
    //pageable.setTotalPages(totRecs / pageable.getPageSize() + 1);
    //pageable.setTotalRecs(totRecs);  
    }
    
    @Transactional
    @Override
    public List<AssignmentPrl> search(_SearchDTO pageable) {
        
        String searchTerm = pageable.getSearchTerm().toUpperCase();
        Session session = getCurrentSession();

        //String qu = "FROM AssignmentPrl m WHERE 1=1 AND UPPER(m.title) LIKE UPPER(CONCAT('%',:search,'%'))";
        String qu = "FROM AssignmentPrl m WHERE 1=1  AND UPPER(CONCAT(m.code)) LIKE CONCAT('%',:search,'%')";

        Query queryCount = session.createQuery("SELECT count(m) " + qu);
        queryCount.setParameter("search", searchTerm);
        Long totRecs = (Long) queryCount.uniqueResult();
        
        Query query = session.createQuery(qu);
        query.setParameter("search", searchTerm);

        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<AssignmentPrl> assignmentPrls = (List<AssignmentPrl>) query.list();

        for (AssignmentPrl assignmentPrl : assignmentPrls) {
            Hibernate.initialize(assignmentPrl.getEmployee());

            //Hibernate.initialize(assignmentPrl.getHrTlShftDtlId());
            //Hibernate.initialize(assignmentPrl.getPersonEduDtlList());
        }
        
        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);

        return assignmentPrls;
    }
}
