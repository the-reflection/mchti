package org.reflection.service;

import org.reflection.model.security.AuthUser;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.AuthUserNotFoundException;
import org.reflection.repository.AuthUserRepo;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("authUserService")
@Transactional(readOnly = true)
public class AuthUserServiceImpl implements AuthUserService {

    @Autowired
    private AuthUserRepo authUserRepo;
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    @Override
    public AuthUser create(AuthUser lookup) {
        getCurrentSession().save(lookup);
        return lookup;
    }

    @Override
    @Transactional
    public AuthUser findById(BigInteger id) {
        AuthUser lookup = (AuthUser) getCurrentSession().get(AuthUser.class, id);

        //Hibernate.initialize(lookup.getHrTlShftDtlId());
        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return lookup;
    }

    @Override
    @Transactional(rollbackFor = AuthUserNotFoundException.class)
    public AuthUser delete(BigInteger lookupId) throws AuthUserNotFoundException {

        AuthUser lookup = (AuthUser) getCurrentSession().get(AuthUser.class, lookupId);

        if (lookup == null) {
            throw new AuthUserNotFoundException();
        }

        getCurrentSession().delete(lookup);
        return lookup;
    }

    @Override
    @Transactional
    public List<AuthUser> findAll() {
        List<AuthUser> lookups = getCurrentSession().createQuery("FROM " + AuthUser.class.getName()).list();

        for (AuthUser lookup : lookups) {

            //Hibernate.initialize(lookup.getHrTlShftDtlId());
            //Hibernate.initialize(lookup.getPersonEduDtlList());
        }
        return lookups;
    }

    @Transactional(rollbackFor = AuthUserNotFoundException.class)
    @Override
    public AuthUser update(AuthUser updated) throws AuthUserNotFoundException {

        AuthUser lookup = (AuthUser) getCurrentSession().get(AuthUser.class, updated.getId());

        if (lookup == null) {
            throw new AuthUserNotFoundException();
        }

        try {
            PropertyUtils.copyProperties(lookup, updated);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {

        }

        getCurrentSession().save(lookup);
        return updated;
    }

    @Transactional(rollbackFor = AuthUserNotFoundException.class)
    public AuthUser copy(final AuthUser updated) {

        AuthUser lookup = null;//new AuthUser();//(AuthUser) getCurrentSession().get(AuthUser.class, updated.getId());

        try {
            PropertyUtils.copyProperties(lookup, updated);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {

        }

        getCurrentSession().save(lookup);
        return lookup;
    }

    @Transactional
    @Override
    public List<AuthUser> findAll(_SearchDTO pageable) {

        Session session = getCurrentSession();

        Query query = session.createQuery("FROM AuthUser m");
        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<AuthUser> lookups = (List<AuthUser>) query.list();
        for (AuthUser lookup : lookups) {

            //Hibernate.initialize(lookup.getHrTlShftDtlId());
            //Hibernate.initialize(lookup.getPersonEduDtlList());
        }

        Long totRecs = (Long) session.createQuery("SELECT COUNT(m) FROM AuthUser m").uniqueResult();

        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);
        return lookups; 
    }

    @Transactional
    @Override
    public List<AuthUser> search(_SearchDTO pageable) {

        String searchTerm = pageable.getSearchTerm().toUpperCase();
        Session session = getCurrentSession();

        //String qu = "FROM AuthUser m WHERE 1=1 AND UPPER(m.title) LIKE UPPER(CONCAT('%',:search,'%'))";
        String qu = "FROM AuthUser m WHERE 1=1 AND UPPER(CONCAT(m.remarks, m.title)) LIKE CONCAT('%',:search,'%')";

        Query queryCount = session.createQuery("SELECT COUNT(m) " + qu);
        queryCount.setParameter("search", searchTerm);
        Long totRecs = (Long) queryCount.uniqueResult();

        Query query = session.createQuery(qu);
        query.setParameter("search", searchTerm);

        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<AuthUser> lookups = (List<AuthUser>) query.list();

        for (AuthUser lookup : lookups) {

            //Hibernate.initialize(lookup.getHrTlShftDtlId());
            //Hibernate.initialize(lookup.getPersonEduDtlList());
        }

        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);

        return lookups;
    }

    @Override
    public AuthUser findByUsername(String username) throws UsernameNotFoundException {
        return authUserRepo.findByUsername(username);
    }

    @Override
    public void deleteByUsername(String username) {
        authUserRepo.deleteByUsername(username);

    }

    @Override
    public boolean isUsernameUnique(BigInteger id, String username) {
        AuthUser user = findByUsername(username);
        return (user == null || ((id != null) && (Objects.equals(user.getId(), id))));
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AuthUser user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found");
        }

        Set<GrantedAuthority> authorities = new HashSet<>();

        for (org.reflection.model.security.AuthRole authRole : user.getAuthRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + authRole.getAuthority()));
        }

        user.setAuthorities(authorities);
        return user;
    }
}
