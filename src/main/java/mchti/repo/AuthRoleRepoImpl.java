package mchti.repo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mchti.model.security.AuthRole;

@Repository("authRoleRepo")
public class AuthRoleRepoImpl extends AbstractRepo<Long, AuthRole> implements AuthRoleRepo {

    public AuthRole findById(Long id) {
        return getByKey(id);
    }

    public AuthRole findByAuthority(String authority) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("authority", authority));
        return (AuthRole) crit.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<AuthRole> findAll() {
        Criteria crit = createEntityCriteria();
        crit.addOrder(Order.asc("authority"));
        return (List<AuthRole>) crit.list();
    }

}
