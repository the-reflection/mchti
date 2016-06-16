package org.reflection.service;

import org.reflection.model.security.AuthRole;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.AuthRoleNotFoundException;
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

@Service("authRoleService")
@Transactional(readOnly = true)
public class AuthRoleServiceImpl implements AuthRoleService {

    @Autowired
    private SessionFactory sessionFactory;
    
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }



    @Transactional
    @Override
    public AuthRole create(AuthRole authRole) {
        getCurrentSession().save(authRole);
        return authRole;
    }
    
    @Override
    @Transactional
    public AuthRole findById(BigInteger id) {
        AuthRole authRole = (AuthRole) getCurrentSession().get(AuthRole.class, id);
        
            Hibernate.initialize(authRole.getAuthUsers());

        //Hibernate.initialize(authRole.getHrTlShftDtlId());
        //Hibernate.initialize(authRole.getPersonEduDtlList());
        return authRole;
    }
    
    @Override
    @Transactional(rollbackFor = AuthRoleNotFoundException.class)
    public AuthRole delete(BigInteger authRoleId) throws AuthRoleNotFoundException {
           
        AuthRole authRole = (AuthRole) getCurrentSession().get(AuthRole.class, authRoleId);
            
        if (authRole == null) {
            throw new AuthRoleNotFoundException();
        }
    
        getCurrentSession().delete(authRole);
        return authRole;
    }
    
    @Override
    @Transactional
    public List<AuthRole> findAll() {
        List<AuthRole> authRoles =getCurrentSession().createQuery("FROM " + AuthRole.class.getName()).list();
            
        for (AuthRole authRole : authRoles) {
            Hibernate.initialize(authRole.getAuthUsers());

            //Hibernate.initialize(authRole.getHrTlShftDtlId());
            //Hibernate.initialize(authRole.getPersonEduDtlList());
        }
        return authRoles;
    }
      
    @Transactional(rollbackFor = AuthRoleNotFoundException.class)
    @Override
    public AuthRole update(AuthRole updated) throws AuthRoleNotFoundException {

        AuthRole authRole = (AuthRole) getCurrentSession().get(AuthRole.class, updated.getId());

        if (authRole == null) {
            throw new AuthRoleNotFoundException();
        }

        try {
            PropertyUtils.copyProperties(authRole, updated);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 AuthRole update: "+ e);
        }

        getCurrentSession().save(authRole);
        return updated;
    }
    
    @Transactional(rollbackFor = AuthRoleNotFoundException.class)
    @Override
    public AuthRole copy(final AuthRole copied) {
    
        AuthRole authRole = new AuthRole();//(AuthRole) getCurrentSession().get(AuthRole.class, copied.getId());
    
        try {
            PropertyUtils.copyProperties(authRole, copied);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 AuthRole copy: "+ e);
        }
    
        getCurrentSession().save(authRole);
        return authRole;
    }
    
    @Transactional
    @Override
    public List<AuthRole> findAll(_SearchDTO pageable) {

        Session session = getCurrentSession();

        Query query = session.createQuery("FROM AuthRole m");
        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<AuthRole> authRoles = (List<AuthRole>) query.list();
        for (AuthRole authRole : authRoles) {
            Hibernate.initialize(authRole.getAuthUsers());

        //Hibernate.initialize(authRole.getHrTlShftDtlId());
        //Hibernate.initialize(authRole.getPersonEduDtlList());
        }

        Long totRecs = (Long) session.createQuery("SELECT count(m) FROM AuthRole m").uniqueResult();

        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);
        return authRoles;

    //Criteria criteria = getCurrentSession().createCriteria(AuthRole.class);
    //criteria.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
    //criteria.setMaxResults(pageable.getPageSize());
    //
    //List<AuthRole> authRoles = (List<AuthRole>) criteria.list();
    //
    //int totRecs = 27;
    //
    //pageable.setTotalPages(totRecs / pageable.getPageSize() + 1);
    //pageable.setTotalRecs(totRecs);  
    }
    
    @Transactional
    @Override
    public List<AuthRole> search(_SearchDTO pageable) {
        
        String searchTerm = pageable.getSearchTerm().toUpperCase();
        Session session = getCurrentSession();

        //String qu = "FROM AuthRole m WHERE 1=1 AND UPPER(m.title) LIKE UPPER(CONCAT('%',:search,'%'))";
        String qu = "FROM AuthRole m WHERE 1=1 MAC_SEARCHABLE_WHERE_TRUE_AND";

        Query queryCount = session.createQuery("SELECT count(m) " + qu);
        queryCount.setParameter("search", searchTerm);
        Long totRecs = (Long) queryCount.uniqueResult();
        
        Query query = session.createQuery(qu);
        query.setParameter("search", searchTerm);

        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<AuthRole> authRoles = (List<AuthRole>) query.list();

        for (AuthRole authRole : authRoles) {
            Hibernate.initialize(authRole.getAuthUsers());

            //Hibernate.initialize(authRole.getHrTlShftDtlId());
            //Hibernate.initialize(authRole.getPersonEduDtlList());
        }
        
        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);

        return authRoles;
    }
}
