package org.reflection.service;

import org.reflection.model.com.SupplierCompany;
import org.reflection.exception.SupplierCompanyNotFoundException;
import org.reflection.dto._SearchDTO;
import java.math.BigInteger;

public interface SupplierCompanyService {

    public SupplierCompany findByCode(String code);

    public SupplierCompany findById(BigInteger id);
    
    public SupplierCompany create(SupplierCompany supplierCompany);
    
    public SupplierCompany update(SupplierCompany supplierCompany) throws SupplierCompanyNotFoundException;
    
    public SupplierCompany copy(SupplierCompany supplierCompany);
    
    public SupplierCompany delete(BigInteger id) throws SupplierCompanyNotFoundException;
   
    public Iterable<SupplierCompany> search(_SearchDTO pageable);
    
    public Iterable<SupplierCompany> findAll(_SearchDTO pageable);
    
    public Iterable<SupplierCompany> findAll();
}