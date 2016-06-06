package org.reflection.service;

import org.reflection.model.sample.ZxLookup;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.ZxLookupNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("zxLookupService")
@Transactional(readOnly = true)
public class ZxLookupServiceImpl implements ZxLookupService {

    @Autowired
    private SessionFactory sessionFactory;
    
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    @Override
    public ZxLookup create(ZxLookup zxLookup) {
        getCurrentSession().save(zxLookup);
        return zxLookup;
    }
    
    @Override
    @Transactional
    public ZxLookup findById(BigInteger id) {
        ZxLookup zxLookup = (ZxLookup) getCurrentSession().get(ZxLookup.class, id);
        
        //Hibernate.initialize(zxLookup.getHrTlShftDtlId());
        //Hibernate.initialize(zxLookup.getPersonEduDtlList());
        return zxLookup;
    }
    
    @Override
    @Transactional(rollbackFor = ZxLookupNotFoundException.class)
    public ZxLookup delete(BigInteger zxLookupId) throws ZxLookupNotFoundException {
           
        ZxLookup zxLookup = (ZxLookup) getCurrentSession().get(ZxLookup.class, zxLookupId);
            
        if (zxLookup == null) {
            throw new ZxLookupNotFoundException();
        }
    
        getCurrentSession().delete(zxLookup);
        return zxLookup;
    }
    
    @Override
    @Transactional
    public List<ZxLookup> findAll() {
        List<ZxLookup> zxLookups =getCurrentSession().createQuery("FROM " + ZxLookup.class.getName()).list();
            
        for (ZxLookup zxLookup : zxLookups) {

            //Hibernate.initialize(zxLookup.getHrTlShftDtlId());
            //Hibernate.initialize(zxLookup.getPersonEduDtlList());
        }
        return zxLookups;
    }
      
    @Transactional(rollbackFor = ZxLookupNotFoundException.class)
    @Override
    public ZxLookup update(ZxLookup updated) throws ZxLookupNotFoundException {

        ZxLookup zxLookup = (ZxLookup) getCurrentSession().get(ZxLookup.class, updated.getId());

        if (zxLookup == null) {
            throw new ZxLookupNotFoundException();
        }

        try {
            PropertyUtils.copyProperties(zxLookup, updated);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 zxLookup update: "+ e);
        }

        getCurrentSession().save(zxLookup);
        return updated;
    }
    
    @Transactional(rollbackFor = ZxLookupNotFoundException.class)
    @Override
    public ZxLookup copy(final ZxLookup copied) {
    
        ZxLookup zxLookup = new ZxLookup();//(ZxLookup) getCurrentSession().get(ZxLookup.class, copied.getId());
    
        try {
            PropertyUtils.copyProperties(zxLookup, copied);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 zxLookup copy: "+ e);
        }
    
        getCurrentSession().save(zxLookup);
        return zxLookup;
    }
    
    @Transactional
    @Override
    public List<ZxLookup> findAll(_SearchDTO pageable) {

        Session session = getCurrentSession();

        Query query = session.createQuery("FROM "+ZxLookup.class.getName()+" m");
        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<ZxLookup> zxLookups = (List<ZxLookup>) query.list();
        for (ZxLookup zxLookup : zxLookups) {

            //Hibernate.initialize(zxLookup.getHrTlShftDtlId());
            //Hibernate.initialize(zxLookup.getPersonEduDtlList());
        }

        Long totRecs = (Long) session.createQuery("SELECT COUNT(m) FROM "+ZxLookup.class.getName()+" m").uniqueResult();

        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);
        return zxLookups;
    }
    
    @Transactional
    @Override
    public List<ZxLookup> search(_SearchDTO pageable) {
        
        String searchTerm = pageable.getSearchTerm().toUpperCase();
        Session session = getCurrentSession();

        //String qu = "FROM ZxLookup m WHERE 1=1 AND UPPER(m.title) LIKE UPPER(CONCAT('%',:search,'%'))";
        String qu = "FROM "+ZxLookup.class.getName()+" m WHERE 1=1  AND UPPER(CONCAT(m.remarks, m.title)) LIKE CONCAT('%',:search,'%')";

        Query queryCount = session.createQuery("SELECT COUNT(m) " + qu);
        queryCount.setParameter("search", searchTerm);
        Long totRecs = (Long) queryCount.uniqueResult();
        
        Query query = session.createQuery(qu);
        query.setParameter("search", searchTerm);

        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<ZxLookup> zxLookups = (List<ZxLookup>) query.list();

        for (ZxLookup zxLookup : zxLookups) {

            //Hibernate.initialize(zxLookup.getHrTlShftDtlId());
            //Hibernate.initialize(zxLookup.getPersonEduDtlList());
        }
        
        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);

        return zxLookups;
    }
}
