package org.reflection.service;

import org.reflection.model.hcm.tl.Period;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.PeriodNotFoundException;
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

@Service("periodService")
@Transactional(readOnly = true)
public class PeriodServiceImpl implements PeriodService {

    @Autowired
    private SessionFactory sessionFactory;
    
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }



    @Transactional
    @Override
    public Period create(Period period) {
        getCurrentSession().save(period);
        return period;
    }
    
    @Override
    @Transactional
    public Period findById(BigInteger id) {
        Period period = (Period) getCurrentSession().get(Period.class, id);
        

        //Hibernate.initialize(period.getHrTlShftDtlId());
        //Hibernate.initialize(period.getPersonEduDtlList());
        return period;
    }
    
    @Override
    @Transactional(rollbackFor = PeriodNotFoundException.class)
    public Period delete(BigInteger periodId) throws PeriodNotFoundException {
           
        Period period = (Period) getCurrentSession().get(Period.class, periodId);
            
        if (period == null) {
            throw new PeriodNotFoundException();
        }
    
        getCurrentSession().delete(period);
        return period;
    }
    
    @Override
    @Transactional
    public List<Period> findAll() {
        List<Period> periods =getCurrentSession().createQuery("FROM " + Period.class.getName()).list();
            
        for (Period period : periods) {

            //Hibernate.initialize(period.getHrTlShftDtlId());
            //Hibernate.initialize(period.getPersonEduDtlList());
        }
        return periods;
    }
      
    @Transactional(rollbackFor = PeriodNotFoundException.class)
    @Override
    public Period update(Period updated) throws PeriodNotFoundException {

        Period period = (Period) getCurrentSession().get(Period.class, updated.getId());

        if (period == null) {
            throw new PeriodNotFoundException();
        }

        try {
            PropertyUtils.copyProperties(period, updated);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 Period update: "+ e);
        }

        getCurrentSession().save(period);
        return updated;
    }
    
    @Transactional(rollbackFor = PeriodNotFoundException.class)
    @Override
    public Period copy(final Period copied) {
    
        Period period = new Period();//(Period) getCurrentSession().get(Period.class, copied.getId());
    
        try {
            PropertyUtils.copyProperties(period, copied);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 Period copy: "+ e);
        }
    
        getCurrentSession().save(period);
        return period;
    }
    
    @Transactional
    @Override
    public List<Period> findAll(_SearchDTO pageable) {

        Session session = getCurrentSession();

        Query query = session.createQuery("FROM Period m");
        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<Period> periods = (List<Period>) query.list();
        for (Period period : periods) {

        //Hibernate.initialize(period.getHrTlShftDtlId());
        //Hibernate.initialize(period.getPersonEduDtlList());
        }

        Long totRecs = (Long) session.createQuery("SELECT count(m) FROM Period m").uniqueResult();

        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);
        return periods;

    //Criteria criteria = getCurrentSession().createCriteria(Period.class);
    //criteria.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
    //criteria.setMaxResults(pageable.getPageSize());
    //
    //List<Period> periods = (List<Period>) criteria.list();
    //
    //int totRecs = 27;
    //
    //pageable.setTotalPages(totRecs / pageable.getPageSize() + 1);
    //pageable.setTotalRecs(totRecs);  
    }
    
    @Transactional
    @Override
    public List<Period> search(_SearchDTO pageable) {
        
        String searchTerm = pageable.getSearchTerm().toUpperCase();
        Session session = getCurrentSession();

        //String qu = "FROM Period m WHERE 1=1 AND UPPER(m.title) LIKE UPPER(CONCAT('%',:search,'%'))";
        String qu = "FROM Period m WHERE 1=1  AND UPPER(CONCAT(m.title, m.code)) LIKE CONCAT('%',:search,'%')";

        Query queryCount = session.createQuery("SELECT count(m) " + qu);
        queryCount.setParameter("search", searchTerm);
        Long totRecs = (Long) queryCount.uniqueResult();
        
        Query query = session.createQuery(qu);
        query.setParameter("search", searchTerm);

        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<Period> periods = (List<Period>) query.list();

        for (Period period : periods) {

            //Hibernate.initialize(period.getHrTlShftDtlId());
            //Hibernate.initialize(period.getPersonEduDtlList());
        }
        
        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);

        return periods;
    }
}
