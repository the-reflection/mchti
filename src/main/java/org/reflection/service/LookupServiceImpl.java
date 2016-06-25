package org.reflection.service;

import org.reflection.model.com.Lookup;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.LookupNotFoundException;
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

@Service("lookupService")
@Transactional(readOnly = true)
public class LookupServiceImpl implements LookupService {

    @Autowired
    private SessionFactory sessionFactory;
    
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }



    @Transactional
    @Override
    public Lookup create(Lookup lookup) {
        getCurrentSession().save(lookup);
        return lookup;
    }
    
    @Override
    @Transactional
    public Lookup findById(BigInteger id) {
        Lookup lookup = (Lookup) getCurrentSession().get(Lookup.class, id);
        

        //Hibernate.initialize(lookup.getHrTlShftDtlId());
        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return lookup;
    }
    
    @Override
    @Transactional(rollbackFor = LookupNotFoundException.class)
    public Lookup delete(BigInteger lookupId) throws LookupNotFoundException {
           
        Lookup lookup = (Lookup) getCurrentSession().get(Lookup.class, lookupId);
            
        if (lookup == null) {
            throw new LookupNotFoundException();
        }
    
        getCurrentSession().delete(lookup);
        return lookup;
    }
    
    @Override
    @Transactional
    public List<Lookup> findAll() {
        List<Lookup> lookups =getCurrentSession().createQuery("FROM " + Lookup.class.getName()).list();
            
        for (Lookup lookup : lookups) {

            //Hibernate.initialize(lookup.getHrTlShftDtlId());
            //Hibernate.initialize(lookup.getPersonEduDtlList());
        }
        return lookups;
    }
      
    @Transactional(rollbackFor = LookupNotFoundException.class)
    @Override
    public Lookup update(Lookup updated) throws LookupNotFoundException {

        Lookup lookup = (Lookup) getCurrentSession().get(Lookup.class, updated.getId());

        if (lookup == null) {
            throw new LookupNotFoundException();
        }

        try {
            PropertyUtils.copyProperties(lookup, updated);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 Lookup update: "+ e);
        }

        getCurrentSession().save(lookup);
        return updated;
    }
    
    @Transactional(rollbackFor = LookupNotFoundException.class)
    @Override
    public Lookup copy(final Lookup copied) {
    
        Lookup lookup = new Lookup();//(Lookup) getCurrentSession().get(Lookup.class, copied.getId());
    
        try {
            PropertyUtils.copyProperties(lookup, copied);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 Lookup copy: "+ e);
        }
    
        getCurrentSession().save(lookup);
        return lookup;
    }
    
    @Transactional
    @Override
    public List<Lookup> findAll(_SearchDTO pageable) {

        Session session = getCurrentSession();

        Query query = session.createQuery("FROM Lookup m");
        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<Lookup> lookups = (List<Lookup>) query.list();
        for (Lookup lookup : lookups) {

        //Hibernate.initialize(lookup.getHrTlShftDtlId());
        //Hibernate.initialize(lookup.getPersonEduDtlList());
        }

        Long totRecs = (Long) session.createQuery("SELECT count(m) FROM Lookup m").uniqueResult();

        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);
        return lookups;

    //Criteria criteria = getCurrentSession().createCriteria(Lookup.class);
    //criteria.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
    //criteria.setMaxResults(pageable.getPageSize());
    //
    //List<Lookup> lookups = (List<Lookup>) criteria.list();
    //
    //int totRecs = 27;
    //
    //pageable.setTotalPages(totRecs / pageable.getPageSize() + 1);
    //pageable.setTotalRecs(totRecs);  
    }
    
    @Transactional
    @Override
    public List<Lookup> search(_SearchDTO pageable) {
        
        String searchTerm = pageable.getSearchTerm().toUpperCase();
        Session session = getCurrentSession();

        //String qu = "FROM Lookup m WHERE 1=1 AND UPPER(m.title) LIKE UPPER(CONCAT('%',:search,'%'))";
        String qu = "FROM Lookup m WHERE 1=1  AND UPPER(CONCAT(m.remarks, m.title)) LIKE CONCAT('%',:search,'%')";

        Query queryCount = session.createQuery("SELECT count(m) " + qu);
        queryCount.setParameter("search", searchTerm);
        Long totRecs = (Long) queryCount.uniqueResult();
        
        Query query = session.createQuery(qu);
        query.setParameter("search", searchTerm);

        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<Lookup> lookups = (List<Lookup>) query.list();

        for (Lookup lookup : lookups) {

            //Hibernate.initialize(lookup.getHrTlShftDtlId());
            //Hibernate.initialize(lookup.getPersonEduDtlList());
        }
        
        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);

        return lookups;
    }
}
