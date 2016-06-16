package org.reflection.service;

import org.reflection.model.security.AuthQuestion;
import org.reflection.exception.AuthQuestionNotFoundException;
import org.reflection.dto._SearchDTO;
import java.util.List;
import java.math.BigInteger;

public interface AuthQuestionService {



    public AuthQuestion findById(BigInteger id);
    
    public AuthQuestion create(AuthQuestion authQuestion);
    
    public AuthQuestion update(AuthQuestion authQuestion) throws AuthQuestionNotFoundException;
    
    public AuthQuestion copy(AuthQuestion authQuestion);
    
    public AuthQuestion delete(BigInteger id) throws AuthQuestionNotFoundException;
   
    public List<AuthQuestion> search(_SearchDTO pageable);
    
    public List<AuthQuestion> findAll(_SearchDTO pageable);
    
    public List<AuthQuestion> findAll();
}