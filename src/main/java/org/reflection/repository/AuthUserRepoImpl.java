package org.reflection.repository;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.reflection.model.security.AuthUser;
import java.math.BigInteger;

@Repository("authUserRepo")
public class AuthUserRepoImpl extends AbstractRepo<BigInteger, AuthUser> implements AuthUserRepo {

    @Override
    public AuthUser findById(BigInteger id) {
        AuthUser user = getByKey(id);
        if (user != null) {
            Hibernate.initialize(user.getAuthRoles());
        }
        return user;
    }

    @Override
    public AuthUser findByUsername(String username) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("username", username));
        AuthUser user = (AuthUser) crit.uniqueResult();
        if (user != null) {
            Hibernate.initialize(user.getAuthRoles());
        }
        return user;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<AuthUser> findAllAuthUsers() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("username"));
//        Criteria criteria = createEntityCriteria().addOrder(Order.asc("fullName"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<AuthUser> users = (List<AuthUser>) criteria.list();

        // No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
        // Uncomment below lines for eagerly fetching of userProfiles if you want.
        /*
		for(AuthUser user : users){
			Hibernate.initialize(user.getUserProfiles());
		}*/
        return users;
    }

    public void save(AuthUser user) {
        persist(user);
    }

    public void deleteByUsername(String username) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("username", username));
        AuthUser user = (AuthUser) crit.uniqueResult();
        delete(user);
    }

}
