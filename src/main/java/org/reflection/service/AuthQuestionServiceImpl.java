package org.reflection.service;

import org.reflection.model.security.AuthQuestion;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.AuthQuestionNotFoundException;
import org.reflection.repositories.AuthQuestionRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("authQuestionService")
@Transactional(readOnly = true)
public class AuthQuestionServiceImpl implements AuthQuestionService {

    @Autowired
    private AuthQuestionRepository authQuestionRepository;



    @Transactional
    @Override
    public AuthQuestion create(AuthQuestion lookup) {
        return authQuestionRepository.save(lookup);
    }

    @Override
    @Transactional
    public AuthQuestion findById(BigInteger id) {
        AuthQuestion authQuestion=authQuestionRepository.findOne(id);
            Hibernate.initialize(authQuestion.getAuthUserAuthQuestions());

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return authQuestion;
    }

    @Override
    @Transactional(rollbackFor = AuthQuestionNotFoundException.class)
    public AuthQuestion delete(BigInteger id) throws AuthQuestionNotFoundException {

        AuthQuestion authQuestion = authQuestionRepository.findOne(id);

        if (authQuestion == null) {
            throw new AuthQuestionNotFoundException();
        }
        authQuestionRepository.delete(id);
        return authQuestion;
    }

    @Override
    @Transactional
    public Iterable<AuthQuestion> findAll() {
        Iterable<AuthQuestion> authQuestions=authQuestionRepository.findAll();
        
        for (AuthQuestion authQuestion : authQuestions) {
            Hibernate.initialize(authQuestion.getAuthUserAuthQuestions());

        //Hibernate.initialize(authQuestion.getA());
        //Hibernate.initialize(authQuestion.getZs());
        }
        
        return authQuestions;
    }

    @Transactional(rollbackFor = AuthQuestionNotFoundException.class)
    @Override
    public AuthQuestion update(AuthQuestion updated) throws AuthQuestionNotFoundException {

        AuthQuestion authQuestion = authQuestionRepository.findOne(updated.getId());

        if (authQuestion == null) {
            throw new AuthQuestionNotFoundException();
        }

        BeanUtils.copyProperties(updated, authQuestion);
        return authQuestionRepository.save(authQuestion);
    }

    @Transactional(rollbackFor = AuthQuestionNotFoundException.class)
    @Override
    public AuthQuestion copy(final AuthQuestion copied) {

        AuthQuestion authQuestion = new AuthQuestion();
        BeanUtils.copyProperties(copied, authQuestion);
        authQuestion.setId(null);

        return authQuestionRepository.save(authQuestion);
    }

    @Transactional
    @Override
    public Iterable<AuthQuestion> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<AuthQuestion> search(_SearchDTO pageable) {
        return findAll();
    }
}