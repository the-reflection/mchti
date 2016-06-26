package org.reflection.service;

import org.reflection.model.com.Company;
import org.reflection.exception.CompanyNotFoundException;
import org.reflection.dto._SearchDTO;
import java.math.BigInteger;

public interface CompanyService {



    public Company findById(BigInteger id);
    
    public Company create(Company company);
    
    public Company update(Company company) throws CompanyNotFoundException;
    
    public Company copy(Company company);
    
    public Company delete(BigInteger id) throws CompanyNotFoundException;
   
    public Iterable<Company> search(_SearchDTO pageable);
    
    public Iterable<Company> findAll(_SearchDTO pageable);
    
    public Iterable<Company> findAll();
}