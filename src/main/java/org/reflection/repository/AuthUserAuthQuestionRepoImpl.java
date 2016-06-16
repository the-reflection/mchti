package org.reflection.repository;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import org.reflection.model.security.AuthUserAuthQuestion;

@Repository("authUserAuthQuestionRepo")
public class AuthUserAuthQuestionRepoImpl extends AbstractRepo<BigInteger, AuthUserAuthQuestion> implements AuthUserAuthQuestionRepo {

    public AuthUserAuthQuestion findById(BigInteger id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    public List<AuthUserAuthQuestion> findAll() {
        Criteria crit = createEntityCriteria();
        return (List<AuthUserAuthQuestion>) crit.list();
    }

    public void save(AuthUserAuthQuestion authUserAuthQuestion) {
        persist(authUserAuthQuestion);
    }
}
