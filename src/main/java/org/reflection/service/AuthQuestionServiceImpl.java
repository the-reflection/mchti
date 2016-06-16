package org.reflection.service;

import org.reflection.model.security.AuthQuestion;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.AuthQuestionNotFoundException;
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

@Service("authQuestionService")
@Transactional(readOnly = true)
public class AuthQuestionServiceImpl implements AuthQuestionService {

    @Autowired
    private SessionFactory sessionFactory;
    
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }



    @Transactional
    @Override
    public AuthQuestion create(AuthQuestion authQuestion) {
        getCurrentSession().save(authQuestion);
        return authQuestion;
    }
    
    @Override
    @Transactional
    public AuthQuestion findById(BigInteger id) {
        AuthQuestion authQuestion = (AuthQuestion) getCurrentSession().get(AuthQuestion.class, id);
        
            Hibernate.initialize(authQuestion.getAuthUserAuthQuestions());

        //Hibernate.initialize(authQuestion.getHrTlShftDtlId());
        //Hibernate.initialize(authQuestion.getPersonEduDtlList());
        return authQuestion;
    }
    
    @Override
    @Transactional(rollbackFor = AuthQuestionNotFoundException.class)
    public AuthQuestion delete(BigInteger authQuestionId) throws AuthQuestionNotFoundException {
           
        AuthQuestion authQuestion = (AuthQuestion) getCurrentSession().get(AuthQuestion.class, authQuestionId);
            
        if (authQuestion == null) {
            throw new AuthQuestionNotFoundException();
        }
    
        getCurrentSession().delete(authQuestion);
        return authQuestion;
    }
    
    @Override
    @Transactional
    public List<AuthQuestion> findAll() {
        List<AuthQuestion> authQuestions =getCurrentSession().createQuery("FROM " + AuthQuestion.class.getName()).list();
            
        for (AuthQuestion authQuestion : authQuestions) {
            Hibernate.initialize(authQuestion.getAuthUserAuthQuestions());

            //Hibernate.initialize(authQuestion.getHrTlShftDtlId());
            //Hibernate.initialize(authQuestion.getPersonEduDtlList());
        }
        return authQuestions;
    }
      
    @Transactional(rollbackFor = AuthQuestionNotFoundException.class)
    @Override
    public AuthQuestion update(AuthQuestion updated) throws AuthQuestionNotFoundException {

        AuthQuestion authQuestion = (AuthQuestion) getCurrentSession().get(AuthQuestion.class, updated.getId());

        if (authQuestion == null) {
            throw new AuthQuestionNotFoundException();
        }

        try {
            PropertyUtils.copyProperties(authQuestion, updated);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 AuthQuestion update: "+ e);
        }

        getCurrentSession().save(authQuestion);
        return updated;
    }
    
    @Transactional(rollbackFor = AuthQuestionNotFoundException.class)
    @Override
    public AuthQuestion copy(final AuthQuestion copied) {
    
        AuthQuestion authQuestion = new AuthQuestion();//(AuthQuestion) getCurrentSession().get(AuthQuestion.class, copied.getId());
    
        try {
            PropertyUtils.copyProperties(authQuestion, copied);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 AuthQuestion copy: "+ e);
        }
    
        getCurrentSession().save(authQuestion);
        return authQuestion;
    }
    
    @Transactional
    @Override
    public List<AuthQuestion> findAll(_SearchDTO pageable) {

        Session session = getCurrentSession();

        Query query = session.createQuery("FROM AuthQuestion m");
        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<AuthQuestion> authQuestions = (List<AuthQuestion>) query.list();
        for (AuthQuestion authQuestion : authQuestions) {
            Hibernate.initialize(authQuestion.getAuthUserAuthQuestions());

        //Hibernate.initialize(authQuestion.getHrTlShftDtlId());
        //Hibernate.initialize(authQuestion.getPersonEduDtlList());
        }

        Long totRecs = (Long) session.createQuery("SELECT count(m) FROM AuthQuestion m").uniqueResult();

        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);
        return authQuestions;

    //Criteria criteria = getCurrentSession().createCriteria(AuthQuestion.class);
    //criteria.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
    //criteria.setMaxResults(pageable.getPageSize());
    //
    //List<AuthQuestion> authQuestions = (List<AuthQuestion>) criteria.list();
    //
    //int totRecs = 27;
    //
    //pageable.setTotalPages(totRecs / pageable.getPageSize() + 1);
    //pageable.setTotalRecs(totRecs);  
    }
    
    @Transactional
    @Override
    public List<AuthQuestion> search(_SearchDTO pageable) {
        
        String searchTerm = pageable.getSearchTerm().toUpperCase();
        Session session = getCurrentSession();

        //String qu = "FROM AuthQuestion m WHERE 1=1 AND UPPER(m.title) LIKE UPPER(CONCAT('%',:search,'%'))";
        String qu = "FROM AuthQuestion m WHERE 1=1 MAC_SEARCHABLE_WHERE_TRUE_AND";

        Query queryCount = session.createQuery("SELECT count(m) " + qu);
        queryCount.setParameter("search", searchTerm);
        Long totRecs = (Long) queryCount.uniqueResult();
        
        Query query = session.createQuery(qu);
        query.setParameter("search", searchTerm);

        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<AuthQuestion> authQuestions = (List<AuthQuestion>) query.list();

        for (AuthQuestion authQuestion : authQuestions) {
            Hibernate.initialize(authQuestion.getAuthUserAuthQuestions());

            //Hibernate.initialize(authQuestion.getHrTlShftDtlId());
            //Hibernate.initialize(authQuestion.getPersonEduDtlList());
        }
        
        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);

        return authQuestions;
    }
}
