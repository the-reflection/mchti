package org.reflection.repository;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import org.reflection.model.security.AuthRole;

@Repository("authRoleRepo")
public class AuthRoleRepoImpl extends AbstractRepo<BigInteger, AuthRole> implements AuthRoleRepo {

    public AuthRole findById(BigInteger id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    public List<AuthRole> findAll() {
        Criteria crit = createEntityCriteria();
        return (List<AuthRole>) crit.list();
    }

    public void save(AuthRole authRole) {
        persist(authRole);
    }
}
