package org.reflection.service;

import org.reflection.model.hcm.tl.LeaveApp;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.LeaveAppNotFoundException;
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

@Service("leaveAppService")
@Transactional(readOnly = true)
public class LeaveAppServiceImpl implements LeaveAppService {

    @Autowired
    private SessionFactory sessionFactory;
    
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }



    @Transactional
    @Override
    public LeaveApp create(LeaveApp leaveApp) {
        getCurrentSession().save(leaveApp);
        return leaveApp;
    }
    
    @Override
    @Transactional
    public LeaveApp findById(BigInteger id) {
        LeaveApp leaveApp = (LeaveApp) getCurrentSession().get(LeaveApp.class, id);
        
            Hibernate.initialize(leaveApp.getEmployee());

        //Hibernate.initialize(leaveApp.getHrTlShftDtlId());
        //Hibernate.initialize(leaveApp.getPersonEduDtlList());
        return leaveApp;
    }
    
    @Override
    @Transactional(rollbackFor = LeaveAppNotFoundException.class)
    public LeaveApp delete(BigInteger leaveAppId) throws LeaveAppNotFoundException {
           
        LeaveApp leaveApp = (LeaveApp) getCurrentSession().get(LeaveApp.class, leaveAppId);
            
        if (leaveApp == null) {
            throw new LeaveAppNotFoundException();
        }
    
        getCurrentSession().delete(leaveApp);
        return leaveApp;
    }
    
    @Override
    @Transactional
    public List<LeaveApp> findAll() {
        List<LeaveApp> leaveApps =getCurrentSession().createQuery("FROM " + LeaveApp.class.getName()).list();
            
        for (LeaveApp leaveApp : leaveApps) {
            Hibernate.initialize(leaveApp.getEmployee());

            //Hibernate.initialize(leaveApp.getHrTlShftDtlId());
            //Hibernate.initialize(leaveApp.getPersonEduDtlList());
        }
        return leaveApps;
    }
      
    @Transactional(rollbackFor = LeaveAppNotFoundException.class)
    @Override
    public LeaveApp update(LeaveApp updated) throws LeaveAppNotFoundException {

        LeaveApp leaveApp = (LeaveApp) getCurrentSession().get(LeaveApp.class, updated.getId());

        if (leaveApp == null) {
            throw new LeaveAppNotFoundException();
        }

        try {
            PropertyUtils.copyProperties(leaveApp, updated);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 LeaveApp update: "+ e);
        }

        getCurrentSession().save(leaveApp);
        return updated;
    }
    
    @Transactional(rollbackFor = LeaveAppNotFoundException.class)
    @Override
    public LeaveApp copy(final LeaveApp copied) {
    
        LeaveApp leaveApp = new LeaveApp();//(LeaveApp) getCurrentSession().get(LeaveApp.class, copied.getId());
    
        try {
            PropertyUtils.copyProperties(leaveApp, copied);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 LeaveApp copy: "+ e);
        }
    
        getCurrentSession().save(leaveApp);
        return leaveApp;
    }
    
    @Transactional
    @Override
    public List<LeaveApp> findAll(_SearchDTO pageable) {

        Session session = getCurrentSession();

        Query query = session.createQuery("FROM LeaveApp m");
        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<LeaveApp> leaveApps = (List<LeaveApp>) query.list();
        for (LeaveApp leaveApp : leaveApps) {
            Hibernate.initialize(leaveApp.getEmployee());

        //Hibernate.initialize(leaveApp.getHrTlShftDtlId());
        //Hibernate.initialize(leaveApp.getPersonEduDtlList());
        }

        Long totRecs = (Long) session.createQuery("SELECT count(m) FROM LeaveApp m").uniqueResult();

        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);
        return leaveApps;

    //Criteria criteria = getCurrentSession().createCriteria(LeaveApp.class);
    //criteria.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
    //criteria.setMaxResults(pageable.getPageSize());
    //
    //List<LeaveApp> leaveApps = (List<LeaveApp>) criteria.list();
    //
    //int totRecs = 27;
    //
    //pageable.setTotalPages(totRecs / pageable.getPageSize() + 1);
    //pageable.setTotalRecs(totRecs);  
    }
    
    @Transactional
    @Override
    public List<LeaveApp> search(_SearchDTO pageable) {
        
        String searchTerm = pageable.getSearchTerm().toUpperCase();
        Session session = getCurrentSession();

        //String qu = "FROM LeaveApp m WHERE 1=1 AND UPPER(m.title) LIKE UPPER(CONCAT('%',:search,'%'))";
        String qu = "FROM LeaveApp m WHERE 1=1  AND UPPER(CONCAT(m.code)) LIKE CONCAT('%',:search,'%')";

        Query queryCount = session.createQuery("SELECT count(m) " + qu);
        queryCount.setParameter("search", searchTerm);
        Long totRecs = (Long) queryCount.uniqueResult();
        
        Query query = session.createQuery(qu);
        query.setParameter("search", searchTerm);

        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<LeaveApp> leaveApps = (List<LeaveApp>) query.list();

        for (LeaveApp leaveApp : leaveApps) {
            Hibernate.initialize(leaveApp.getEmployee());

            //Hibernate.initialize(leaveApp.getHrTlShftDtlId());
            //Hibernate.initialize(leaveApp.getPersonEduDtlList());
        }
        
        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);

        return leaveApps;
    }
}
