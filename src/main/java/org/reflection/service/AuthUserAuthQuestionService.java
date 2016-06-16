package org.reflection.service;

import org.reflection.model.security.AuthUserAuthQuestion;
import org.reflection.exception.AuthUserAuthQuestionNotFoundException;
import org.reflection.dto._SearchDTO;
import java.util.List;
import java.math.BigInteger;

public interface AuthUserAuthQuestionService {



    public AuthUserAuthQuestion findById(BigInteger id);
    
    public AuthUserAuthQuestion create(AuthUserAuthQuestion authUserAuthQuestion);
    
    public AuthUserAuthQuestion update(AuthUserAuthQuestion authUserAuthQuestion) throws AuthUserAuthQuestionNotFoundException;
    
    public AuthUserAuthQuestion copy(AuthUserAuthQuestion authUserAuthQuestion);
    
    public AuthUserAuthQuestion delete(BigInteger id) throws AuthUserAuthQuestionNotFoundException;
   
    public List<AuthUserAuthQuestion> search(_SearchDTO pageable);
    
    public List<AuthUserAuthQuestion> findAll(_SearchDTO pageable);
    
    public List<AuthUserAuthQuestion> findAll();
}