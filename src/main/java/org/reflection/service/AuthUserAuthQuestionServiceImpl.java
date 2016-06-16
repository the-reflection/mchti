package org.reflection.service;

import org.reflection.model.security.AuthUserAuthQuestion;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.AuthUserAuthQuestionNotFoundException;
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

@Service("authUserAuthQuestionService")
@Transactional(readOnly = true)
public class AuthUserAuthQuestionServiceImpl implements AuthUserAuthQuestionService {

    @Autowired
    private SessionFactory sessionFactory;
    
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }



    @Transactional
    @Override
    public AuthUserAuthQuestion create(AuthUserAuthQuestion authUserAuthQuestion) {
        getCurrentSession().save(authUserAuthQuestion);
        return authUserAuthQuestion;
    }
    
    @Override
    @Transactional
    public AuthUserAuthQuestion findById(BigInteger id) {
        AuthUserAuthQuestion authUserAuthQuestion = (AuthUserAuthQuestion) getCurrentSession().get(AuthUserAuthQuestion.class, id);
        

        //Hibernate.initialize(authUserAuthQuestion.getHrTlShftDtlId());
        //Hibernate.initialize(authUserAuthQuestion.getPersonEduDtlList());
        return authUserAuthQuestion;
    }
    
    @Override
    @Transactional(rollbackFor = AuthUserAuthQuestionNotFoundException.class)
    public AuthUserAuthQuestion delete(BigInteger authUserAuthQuestionId) throws AuthUserAuthQuestionNotFoundException {
           
        AuthUserAuthQuestion authUserAuthQuestion = (AuthUserAuthQuestion) getCurrentSession().get(AuthUserAuthQuestion.class, authUserAuthQuestionId);
            
        if (authUserAuthQuestion == null) {
            throw new AuthUserAuthQuestionNotFoundException();
        }
    
        getCurrentSession().delete(authUserAuthQuestion);
        return authUserAuthQuestion;
    }
    
    @Override
    @Transactional
    public List<AuthUserAuthQuestion> findAll() {
        List<AuthUserAuthQuestion> authUserAuthQuestions =getCurrentSession().createQuery("FROM " + AuthUserAuthQuestion.class.getName()).list();
            
        for (AuthUserAuthQuestion authUserAuthQuestion : authUserAuthQuestions) {

            //Hibernate.initialize(authUserAuthQuestion.getHrTlShftDtlId());
            //Hibernate.initialize(authUserAuthQuestion.getPersonEduDtlList());
        }
        return authUserAuthQuestions;
    }
      
    @Transactional(rollbackFor = AuthUserAuthQuestionNotFoundException.class)
    @Override
    public AuthUserAuthQuestion update(AuthUserAuthQuestion updated) throws AuthUserAuthQuestionNotFoundException {

        AuthUserAuthQuestion authUserAuthQuestion = (AuthUserAuthQuestion) getCurrentSession().get(AuthUserAuthQuestion.class, updated.getId());

        if (authUserAuthQuestion == null) {
            throw new AuthUserAuthQuestionNotFoundException();
        }

        try {
            PropertyUtils.copyProperties(authUserAuthQuestion, updated);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 AuthUserAuthQuestion update: "+ e);
        }

        getCurrentSession().save(authUserAuthQuestion);
        return updated;
    }
    
    @Transactional(rollbackFor = AuthUserAuthQuestionNotFoundException.class)
    @Override
    public AuthUserAuthQuestion copy(final AuthUserAuthQuestion copied) {
    
        AuthUserAuthQuestion authUserAuthQuestion = new AuthUserAuthQuestion();//(AuthUserAuthQuestion) getCurrentSession().get(AuthUserAuthQuestion.class, copied.getId());
    
        try {
            PropertyUtils.copyProperties(authUserAuthQuestion, copied);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 AuthUserAuthQuestion copy: "+ e);
        }
    
        getCurrentSession().save(authUserAuthQuestion);
        return authUserAuthQuestion;
    }
    
    @Transactional
    @Override
    public List<AuthUserAuthQuestion> findAll(_SearchDTO pageable) {

        Session session = getCurrentSession();

        Query query = session.createQuery("FROM AuthUserAuthQuestion m");
        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<AuthUserAuthQuestion> authUserAuthQuestions = (List<AuthUserAuthQuestion>) query.list();
        for (AuthUserAuthQuestion authUserAuthQuestion : authUserAuthQuestions) {

        //Hibernate.initialize(authUserAuthQuestion.getHrTlShftDtlId());
        //Hibernate.initialize(authUserAuthQuestion.getPersonEduDtlList());
        }

        Long totRecs = (Long) session.createQuery("SELECT count(m) FROM AuthUserAuthQuestion m").uniqueResult();

        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);
        return authUserAuthQuestions;

    //Criteria criteria = getCurrentSession().createCriteria(AuthUserAuthQuestion.class);
    //criteria.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
    //criteria.setMaxResults(pageable.getPageSize());
    //
    //List<AuthUserAuthQuestion> authUserAuthQuestions = (List<AuthUserAuthQuestion>) criteria.list();
    //
    //int totRecs = 27;
    //
    //pageable.setTotalPages(totRecs / pageable.getPageSize() + 1);
    //pageable.setTotalRecs(totRecs);  
    }
    
    @Transactional
    @Override
    public List<AuthUserAuthQuestion> search(_SearchDTO pageable) {
        
        String searchTerm = pageable.getSearchTerm().toUpperCase();
        Session session = getCurrentSession();

        //String qu = "FROM AuthUserAuthQuestion m WHERE 1=1 AND UPPER(m.title) LIKE UPPER(CONCAT('%',:search,'%'))";
        String qu = "FROM AuthUserAuthQuestion m WHERE 1=1 MAC_SEARCHABLE_WHERE_TRUE_AND";

        Query queryCount = session.createQuery("SELECT count(m) " + qu);
        queryCount.setParameter("search", searchTerm);
        Long totRecs = (Long) queryCount.uniqueResult();
        
        Query query = session.createQuery(qu);
        query.setParameter("search", searchTerm);

        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<AuthUserAuthQuestion> authUserAuthQuestions = (List<AuthUserAuthQuestion>) query.list();

        for (AuthUserAuthQuestion authUserAuthQuestion : authUserAuthQuestions) {

            //Hibernate.initialize(authUserAuthQuestion.getHrTlShftDtlId());
            //Hibernate.initialize(authUserAuthQuestion.getPersonEduDtlList());
        }
        
        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);

        return authUserAuthQuestions;
    }
}
