package org.reflection.service;

import org.reflection.model.com.Supplier;
import org.reflection.exception.SupplierNotFoundException;
import org.reflection.dto._SearchDTO;
import java.math.BigInteger;

public interface SupplierService {

    public Supplier findByCode(String code);

    public Supplier findById(BigInteger id);
    
    public Supplier create(Supplier supplier);
    
    public Supplier update(Supplier supplier) throws SupplierNotFoundException;
    
    public Supplier copy(Supplier supplier);
    
    public Supplier delete(BigInteger id) throws SupplierNotFoundException;
   
    public Iterable<Supplier> search(_SearchDTO pageable);
    
    public Iterable<Supplier> findAll(_SearchDTO pageable);
    
    public Iterable<Supplier> findAll();
}