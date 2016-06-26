package org.reflection.service;

import org.reflection.model.com.ClientCompany;
import org.reflection.exception.ClientCompanyNotFoundException;
import org.reflection.dto._SearchDTO;
import java.math.BigInteger;

public interface ClientCompanyService {

    public ClientCompany findByCode(String code);

    public ClientCompany findById(BigInteger id);
    
    public ClientCompany create(ClientCompany clientCompany);
    
    public ClientCompany update(ClientCompany clientCompany) throws ClientCompanyNotFoundException;
    
    public ClientCompany copy(ClientCompany clientCompany);
    
    public ClientCompany delete(BigInteger id) throws ClientCompanyNotFoundException;
   
    public Iterable<ClientCompany> search(_SearchDTO pageable);
    
    public Iterable<ClientCompany> findAll(_SearchDTO pageable);
    
    public Iterable<ClientCompany> findAll();
}