package org.reflection.service;

import org.reflection.model.hcm.proc.ProcOutAttnPeriod;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.ProcOutAttnPeriodNotFoundException;
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

@Service("procOutAttnPeriodService")
@Transactional(readOnly = true)
public class ProcOutAttnPeriodServiceImpl implements ProcOutAttnPeriodService {

    @Autowired
    private SessionFactory sessionFactory;
    
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }



    @Transactional
    @Override
    public ProcOutAttnPeriod create(ProcOutAttnPeriod procOutAttnPeriod) {
        getCurrentSession().save(procOutAttnPeriod);
        return procOutAttnPeriod;
    }
    
    @Override
    @Transactional
    public ProcOutAttnPeriod findById(BigInteger id) {
        ProcOutAttnPeriod procOutAttnPeriod = (ProcOutAttnPeriod) getCurrentSession().get(ProcOutAttnPeriod.class, id);
        
            Hibernate.initialize(procOutAttnPeriod.getPeriod());
            Hibernate.initialize(procOutAttnPeriod.getEmployee());

        //Hibernate.initialize(procOutAttnPeriod.getHrTlShftDtlId());
        //Hibernate.initialize(procOutAttnPeriod.getPersonEduDtlList());
        return procOutAttnPeriod;
    }
    
    @Override
    @Transactional(rollbackFor = ProcOutAttnPeriodNotFoundException.class)
    public ProcOutAttnPeriod delete(BigInteger procOutAttnPeriodId) throws ProcOutAttnPeriodNotFoundException {
           
        ProcOutAttnPeriod procOutAttnPeriod = (ProcOutAttnPeriod) getCurrentSession().get(ProcOutAttnPeriod.class, procOutAttnPeriodId);
            
        if (procOutAttnPeriod == null) {
            throw new ProcOutAttnPeriodNotFoundException();
        }
    
        getCurrentSession().delete(procOutAttnPeriod);
        return procOutAttnPeriod;
    }
    
    @Override
    @Transactional
    public List<ProcOutAttnPeriod> findAll() {
        List<ProcOutAttnPeriod> procOutAttnPeriods =getCurrentSession().createQuery("FROM " + ProcOutAttnPeriod.class.getName()).list();
            
        for (ProcOutAttnPeriod procOutAttnPeriod : procOutAttnPeriods) {
            Hibernate.initialize(procOutAttnPeriod.getPeriod());
            Hibernate.initialize(procOutAttnPeriod.getEmployee());

            //Hibernate.initialize(procOutAttnPeriod.getHrTlShftDtlId());
            //Hibernate.initialize(procOutAttnPeriod.getPersonEduDtlList());
        }
        return procOutAttnPeriods;
    }
      
    @Transactional(rollbackFor = ProcOutAttnPeriodNotFoundException.class)
    @Override
    public ProcOutAttnPeriod update(ProcOutAttnPeriod updated) throws ProcOutAttnPeriodNotFoundException {

        ProcOutAttnPeriod procOutAttnPeriod = (ProcOutAttnPeriod) getCurrentSession().get(ProcOutAttnPeriod.class, updated.getId());

        if (procOutAttnPeriod == null) {
            throw new ProcOutAttnPeriodNotFoundException();
        }

        try {
            PropertyUtils.copyProperties(procOutAttnPeriod, updated);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 ProcOutAttnPeriod update: "+ e);
        }

        getCurrentSession().save(procOutAttnPeriod);
        return updated;
    }
    
    @Transactional(rollbackFor = ProcOutAttnPeriodNotFoundException.class)
    @Override
    public ProcOutAttnPeriod copy(final ProcOutAttnPeriod copied) {
    
        ProcOutAttnPeriod procOutAttnPeriod = new ProcOutAttnPeriod();//(ProcOutAttnPeriod) getCurrentSession().get(ProcOutAttnPeriod.class, copied.getId());
    
        try {
            PropertyUtils.copyProperties(procOutAttnPeriod, copied);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 ProcOutAttnPeriod copy: "+ e);
        }
    
        getCurrentSession().save(procOutAttnPeriod);
        return procOutAttnPeriod;
    }
    
    @Transactional
    @Override
    public List<ProcOutAttnPeriod> findAll(_SearchDTO pageable) {

        Session session = getCurrentSession();

        Query query = session.createQuery("FROM ProcOutAttnPeriod m");
        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<ProcOutAttnPeriod> procOutAttnPeriods = (List<ProcOutAttnPeriod>) query.list();
        for (ProcOutAttnPeriod procOutAttnPeriod : procOutAttnPeriods) {
            Hibernate.initialize(procOutAttnPeriod.getPeriod());
            Hibernate.initialize(procOutAttnPeriod.getEmployee());

        //Hibernate.initialize(procOutAttnPeriod.getHrTlShftDtlId());
        //Hibernate.initialize(procOutAttnPeriod.getPersonEduDtlList());
        }

        Long totRecs = (Long) session.createQuery("SELECT count(m) FROM ProcOutAttnPeriod m").uniqueResult();

        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);
        return procOutAttnPeriods;

    //Criteria criteria = getCurrentSession().createCriteria(ProcOutAttnPeriod.class);
    //criteria.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
    //criteria.setMaxResults(pageable.getPageSize());
    //
    //List<ProcOutAttnPeriod> procOutAttnPeriods = (List<ProcOutAttnPeriod>) criteria.list();
    //
    //int totRecs = 27;
    //
    //pageable.setTotalPages(totRecs / pageable.getPageSize() + 1);
    //pageable.setTotalRecs(totRecs);  
    }
    
    @Transactional
    @Override
    public List<ProcOutAttnPeriod> search(_SearchDTO pageable) {
        
        String searchTerm = pageable.getSearchTerm().toUpperCase();
        Session session = getCurrentSession();

        //String qu = "FROM ProcOutAttnPeriod m WHERE 1=1 AND UPPER(m.title) LIKE UPPER(CONCAT('%',:search,'%'))";
        String qu = "FROM ProcOutAttnPeriod m WHERE 1=1 MAC_SEARCHABLE_WHERE_TRUE_AND";

        Query queryCount = session.createQuery("SELECT count(m) " + qu);
        queryCount.setParameter("search", searchTerm);
        Long totRecs = (Long) queryCount.uniqueResult();
        
        Query query = session.createQuery(qu);
        query.setParameter("search", searchTerm);

        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<ProcOutAttnPeriod> procOutAttnPeriods = (List<ProcOutAttnPeriod>) query.list();

        for (ProcOutAttnPeriod procOutAttnPeriod : procOutAttnPeriods) {
            Hibernate.initialize(procOutAttnPeriod.getPeriod());
            Hibernate.initialize(procOutAttnPeriod.getEmployee());

            //Hibernate.initialize(procOutAttnPeriod.getHrTlShftDtlId());
            //Hibernate.initialize(procOutAttnPeriod.getPersonEduDtlList());
        }
        
        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);

        return procOutAttnPeriods;
    }
}
