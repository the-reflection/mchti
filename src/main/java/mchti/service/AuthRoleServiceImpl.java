package mchti.service;


import mchti.dto._SearchDTO;
import mchti.exception.AuthRoleNotFoundException;
import mchti.model.security.AuthRole;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtils;
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
    public AuthRole create(AuthRole lookup) {
        getCurrentSession().save(lookup);
        return lookup;
    }

    @Override
    @Transactional
    public AuthRole findById(Long id) {
        AuthRole lookup = (AuthRole) getCurrentSession().get(AuthRole.class, id);

        //Hibernate.initialize(lookup.getHrTlShftDtlId());
        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return lookup;
    }

    @Override
    @Transactional(rollbackFor = AuthRoleNotFoundException.class)
    public AuthRole delete(Long lookupId) throws AuthRoleNotFoundException {

        AuthRole lookup = (AuthRole) getCurrentSession().get(AuthRole.class, lookupId);

        if (lookup == null) {
            throw new AuthRoleNotFoundException();
        }

        getCurrentSession().delete(lookup);
        return lookup;
    }

    @Override
    @Transactional
    public List<AuthRole> findAll() {
        List<AuthRole> lookups = getCurrentSession().createQuery("FROM " + AuthRole.class.getName()).list();

        for (AuthRole lookup : lookups) {

            //Hibernate.initialize(lookup.getHrTlShftDtlId());
            //Hibernate.initialize(lookup.getPersonEduDtlList());
        }
        return lookups;
    }

    @Transactional(rollbackFor = AuthRoleNotFoundException.class)
    @Override
    public AuthRole update(AuthRole updated) throws AuthRoleNotFoundException {

        AuthRole lookup = (AuthRole) getCurrentSession().get(AuthRole.class, updated.getId());

        if (lookup == null) {
            throw new AuthRoleNotFoundException();
        }

        try {
            PropertyUtils.copyProperties(lookup, updated);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {

        }

        getCurrentSession().save(lookup);
        return updated;
    }

    @Transactional(rollbackFor = AuthRoleNotFoundException.class)
    public AuthRole copy(final AuthRole updated) {

        AuthRole lookup = new AuthRole();//(AuthRole) getCurrentSession().get(AuthRole.class, updated.getId());

        try {
            PropertyUtils.copyProperties(lookup, updated);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {

        }

        getCurrentSession().save(lookup);
        return lookup;
    }

    @Transactional
    @Override
    public List<AuthRole> findAll(_SearchDTO pageable) {

        Session session = getCurrentSession();

        Query query = session.createQuery("FROM AuthRole m");
        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<AuthRole> lookups = (List<AuthRole>) query.list();
        for (AuthRole lookup : lookups) {

            //Hibernate.initialize(lookup.getHrTlShftDtlId());
            //Hibernate.initialize(lookup.getPersonEduDtlList());
        }

        Long totRecs = (Long) session.createQuery("SELECT COUNT(m) FROM AuthRole m").uniqueResult();

        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);
        return lookups;

        //Criteria criteria = getCurrentSession().createCriteria(AuthRole.class);
        //criteria.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        //criteria.setMaxResults(pageable.getPageSize());
        //
        //List<AuthRole> lookups = (List<AuthRole>) criteria.list();
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
        String qu = "FROM AuthRole m WHERE 1=1 AND UPPER(CONCAT(m.remarks, m.title)) LIKE CONCAT('%',:search,'%')";

        Query queryCount = session.createQuery("SELECT COUNT(m) " + qu);
        queryCount.setParameter("search", searchTerm);
        Long totRecs = (Long) queryCount.uniqueResult();

        Query query = session.createQuery(qu);
        query.setParameter("search", searchTerm);

        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<AuthRole> lookups = (List<AuthRole>) query.list();

        for (AuthRole lookup : lookups) {

            //Hibernate.initialize(lookup.getHrTlShftDtlId());
            //Hibernate.initialize(lookup.getPersonEduDtlList());
        }

        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);

        return lookups;
    }

  
}
