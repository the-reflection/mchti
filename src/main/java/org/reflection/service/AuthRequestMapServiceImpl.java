package org.reflection.service;

import org.reflection.model.security.AuthRequestMap;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.AuthRequestMapNotFoundException;
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

@Service("authRequestMapService")
@Transactional(readOnly = true)
public class AuthRequestMapServiceImpl implements AuthRequestMapService {

    @Autowired
    private SessionFactory sessionFactory;
    
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }



    @Transactional
    @Override
    public AuthRequestMap create(AuthRequestMap authRequestMap) {
        getCurrentSession().save(authRequestMap);
        return authRequestMap;
    }
    
    @Override
    @Transactional
    public AuthRequestMap findById(BigInteger id) {
        AuthRequestMap authRequestMap = (AuthRequestMap) getCurrentSession().get(AuthRequestMap.class, id);
        

        //Hibernate.initialize(authRequestMap.getHrTlShftDtlId());
        //Hibernate.initialize(authRequestMap.getPersonEduDtlList());
        return authRequestMap;
    }
    
    @Override
    @Transactional(rollbackFor = AuthRequestMapNotFoundException.class)
    public AuthRequestMap delete(BigInteger authRequestMapId) throws AuthRequestMapNotFoundException {
           
        AuthRequestMap authRequestMap = (AuthRequestMap) getCurrentSession().get(AuthRequestMap.class, authRequestMapId);
            
        if (authRequestMap == null) {
            throw new AuthRequestMapNotFoundException();
        }
    
        getCurrentSession().delete(authRequestMap);
        return authRequestMap;
    }
    
    @Override
    @Transactional
    public List<AuthRequestMap> findAll() {
        List<AuthRequestMap> authRequestMaps =getCurrentSession().createQuery("FROM " + AuthRequestMap.class.getName()).list();
            
        for (AuthRequestMap authRequestMap : authRequestMaps) {

            //Hibernate.initialize(authRequestMap.getHrTlShftDtlId());
            //Hibernate.initialize(authRequestMap.getPersonEduDtlList());
        }
        return authRequestMaps;
    }
      
    @Transactional(rollbackFor = AuthRequestMapNotFoundException.class)
    @Override
    public AuthRequestMap update(AuthRequestMap updated) throws AuthRequestMapNotFoundException {

        AuthRequestMap authRequestMap = (AuthRequestMap) getCurrentSession().get(AuthRequestMap.class, updated.getId());

        if (authRequestMap == null) {
            throw new AuthRequestMapNotFoundException();
        }

        try {
            PropertyUtils.copyProperties(authRequestMap, updated);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 AuthRequestMap update: "+ e);
        }

        getCurrentSession().save(authRequestMap);
        return updated;
    }
    
    @Transactional(rollbackFor = AuthRequestMapNotFoundException.class)
    @Override
    public AuthRequestMap copy(final AuthRequestMap copied) {
    
        AuthRequestMap authRequestMap = new AuthRequestMap();//(AuthRequestMap) getCurrentSession().get(AuthRequestMap.class, copied.getId());
    
        try {
            PropertyUtils.copyProperties(authRequestMap, copied);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 AuthRequestMap copy: "+ e);
        }
    
        getCurrentSession().save(authRequestMap);
        return authRequestMap;
    }
    
    @Transactional
    @Override
    public List<AuthRequestMap> findAll(_SearchDTO pageable) {

        Session session = getCurrentSession();

        Query query = session.createQuery("FROM AuthRequestMap m");
        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<AuthRequestMap> authRequestMaps = (List<AuthRequestMap>) query.list();
        for (AuthRequestMap authRequestMap : authRequestMaps) {

        //Hibernate.initialize(authRequestMap.getHrTlShftDtlId());
        //Hibernate.initialize(authRequestMap.getPersonEduDtlList());
        }

        Long totRecs = (Long) session.createQuery("SELECT count(m) FROM AuthRequestMap m").uniqueResult();

        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);
        return authRequestMaps;

    //Criteria criteria = getCurrentSession().createCriteria(AuthRequestMap.class);
    //criteria.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
    //criteria.setMaxResults(pageable.getPageSize());
    //
    //List<AuthRequestMap> authRequestMaps = (List<AuthRequestMap>) criteria.list();
    //
    //int totRecs = 27;
    //
    //pageable.setTotalPages(totRecs / pageable.getPageSize() + 1);
    //pageable.setTotalRecs(totRecs);  
    }
    
    @Transactional
    @Override
    public List<AuthRequestMap> search(_SearchDTO pageable) {
        
        String searchTerm = pageable.getSearchTerm().toUpperCase();
        Session session = getCurrentSession();

        //String qu = "FROM AuthRequestMap m WHERE 1=1 AND UPPER(m.title) LIKE UPPER(CONCAT('%',:search,'%'))";
        String qu = "FROM AuthRequestMap m WHERE 1=1 MAC_SEARCHABLE_WHERE_TRUE_AND";

        Query queryCount = session.createQuery("SELECT count(m) " + qu);
        queryCount.setParameter("search", searchTerm);
        Long totRecs = (Long) queryCount.uniqueResult();
        
        Query query = session.createQuery(qu);
        query.setParameter("search", searchTerm);

        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<AuthRequestMap> authRequestMaps = (List<AuthRequestMap>) query.list();

        for (AuthRequestMap authRequestMap : authRequestMaps) {

            //Hibernate.initialize(authRequestMap.getHrTlShftDtlId());
            //Hibernate.initialize(authRequestMap.getPersonEduDtlList());
        }
        
        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);

        return authRequestMaps;
    }
}
