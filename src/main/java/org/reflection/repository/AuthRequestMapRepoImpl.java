package org.reflection.repository;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import org.reflection.model.security.AuthRequestMap;

@Repository("authRequestMapRepo")
public class AuthRequestMapRepoImpl extends AbstractRepo<BigInteger, AuthRequestMap> implements AuthRequestMapRepo {

    public AuthRequestMap findById(BigInteger id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    public List<AuthRequestMap> findAll() {
        Criteria crit = createEntityCriteria();
        return (List<AuthRequestMap>) crit.list();
    }

    public void save(AuthRequestMap authRequestMap) {
        persist(authRequestMap);
    }
}
