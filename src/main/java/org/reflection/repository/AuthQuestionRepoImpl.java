package org.reflection.repository;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import org.reflection.model.security.AuthQuestion;

@Repository("authQuestionRepo")
public class AuthQuestionRepoImpl extends AbstractRepo<BigInteger, AuthQuestion> implements AuthQuestionRepo {

    public AuthQuestion findById(BigInteger id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    public List<AuthQuestion> findAll() {
        Criteria crit = createEntityCriteria();
        return (List<AuthQuestion>) crit.list();
    }

    public void save(AuthQuestion authQuestion) {
        persist(authQuestion);
    }
}
