package org.reflection.service;

import org.reflection.model.security.AuthMenu;
import org.reflection.dto._SearchDTO;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtils;
import java.math.BigInteger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.reflection.exception.AuthMenuNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("authMenuService")
@Transactional(readOnly = true)
public class AuthMenuServiceImpl implements AuthMenuService {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    @Override
    public AuthMenu create(AuthMenu authMenu) {
        getCurrentSession().save(authMenu);
        return authMenu;
    }

    @Override
    @Transactional
    public AuthMenu findById(BigInteger id) {
        AuthMenu authMenu = (AuthMenu) getCurrentSession().get(AuthMenu.class, id);

        //Hibernate.initialize(authMenu.getHrTlShftDtlId());
        //Hibernate.initialize(authMenu.getPersonEduDtlList());
        return authMenu;
    }

    @Override
    @Transactional(rollbackFor = AuthMenuNotFoundException.class)
    public AuthMenu delete(BigInteger authMenuId) throws AuthMenuNotFoundException {

        AuthMenu authMenu = (AuthMenu) getCurrentSession().get(AuthMenu.class, authMenuId);

        if (authMenu == null) {
            throw new AuthMenuNotFoundException();
        }

        getCurrentSession().delete(authMenu);
        return authMenu;
    }

    @Override
    @Transactional
    public List<AuthMenu> findAll() {
        List<AuthMenu> authMenus = getCurrentSession().createQuery("FROM " + AuthMenu.class.getName()).list();

        for (AuthMenu authMenu : authMenus) {

            //Hibernate.initialize(authMenu.getHrTlShftDtlId());
            //Hibernate.initialize(authMenu.getPersonEduDtlList());
        }
        return authMenus;
    }

    @Transactional(rollbackFor = AuthMenuNotFoundException.class)
    @Override
    public AuthMenu update(AuthMenu updated) throws AuthMenuNotFoundException {

        AuthMenu authMenu = (AuthMenu) getCurrentSession().get(AuthMenu.class, updated.getId());

        if (authMenu == null) {
            throw new AuthMenuNotFoundException();
        }

        try {
            PropertyUtils.copyProperties(authMenu, updated);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 AuthMenu update: " + e);
        }

        getCurrentSession().save(authMenu);
        return updated;
    }

    @Transactional(rollbackFor = AuthMenuNotFoundException.class)
    @Override
    public AuthMenu copy(final AuthMenu copied) {

        AuthMenu authMenu = new AuthMenu();//(AuthMenu) getCurrentSession().get(AuthMenu.class, copied.getId());

        try {
            PropertyUtils.copyProperties(authMenu, copied);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 AuthMenu copy: " + e);
        }

        getCurrentSession().save(authMenu);
        return authMenu;
    }

    @Transactional
    @Override
    public List<AuthMenu> findAll(_SearchDTO pageable) {

        Session session = getCurrentSession();

        Query query = session.createQuery("FROM AuthMenu m");
        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<AuthMenu> authMenus = (List<AuthMenu>) query.list();
        for (AuthMenu authMenu : authMenus) {

            //Hibernate.initialize(authMenu.getHrTlShftDtlId());
            //Hibernate.initialize(authMenu.getPersonEduDtlList());
        }

        Long totRecs = (Long) session.createQuery("SELECT count(m) FROM AuthMenu m").uniqueResult();

        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);
        return authMenus;

        //Criteria criteria = getCurrentSession().createCriteria(AuthMenu.class);
        //criteria.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        //criteria.setMaxResults(pageable.getPageSize());
        //
        //List<AuthMenu> authMenus = (List<AuthMenu>) criteria.list();
        //
        //int totRecs = 27;
        //
        //pageable.setTotalPages(totRecs / pageable.getPageSize() + 1);
        //pageable.setTotalRecs(totRecs);  
    }

    @Transactional
    @Override
    public List<AuthMenu> search(_SearchDTO pageable) {

        String searchTerm = pageable.getSearchTerm().toUpperCase();
        Session session = getCurrentSession();

        //String qu = "FROM AuthMenu m WHERE 1=1 AND UPPER(m.title) LIKE UPPER(CONCAT('%',:search,'%'))";
        String qu = "FROM AuthMenu m WHERE 1=1  AND UPPER(CONCAT(m.code)) LIKE CONCAT('%',:search,'%')";

        Query queryCount = session.createQuery("SELECT count(m) " + qu);
        queryCount.setParameter("search", searchTerm);
        Long totRecs = (Long) queryCount.uniqueResult();

        Query query = session.createQuery(qu);
        query.setParameter("search", searchTerm);

        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<AuthMenu> authMenus = (List<AuthMenu>) query.list();

        for (AuthMenu authMenu : authMenus) {

            //Hibernate.initialize(authMenu.getHrTlShftDtlId());
            //Hibernate.initialize(authMenu.getPersonEduDtlList());
        }

        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);

        return authMenus;
    }
}
