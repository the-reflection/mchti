package org.reflection.service;

import org.reflection.model.com.Supplier;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.SupplierNotFoundException;
import org.reflection.repositories.SupplierRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("supplierService")
@Transactional(readOnly = true)
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Transactional(readOnly = true)
    @Override
    public Supplier findByCode(String code) {
        return supplierRepository.findByCode(code);
    }

    @Transactional
    @Override
    public Supplier create(Supplier lookup) {
        return supplierRepository.save(lookup);
    }

    @Override
    @Transactional
    public Supplier findById(BigInteger id) {
        Supplier supplier=supplierRepository.findOne(id);

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return supplier;
    }

    @Override
    @Transactional(rollbackFor = SupplierNotFoundException.class)
    public Supplier delete(BigInteger id) throws SupplierNotFoundException {

        Supplier supplier = supplierRepository.findOne(id);

        if (supplier == null) {
            throw new SupplierNotFoundException();
        }
        supplierRepository.delete(id);
        return supplier;
    }

    @Override
    @Transactional
    public Iterable<Supplier> findAll() {
        Iterable<Supplier> suppliers=supplierRepository.findAll();
        
        for (Supplier supplier : suppliers) {

        //Hibernate.initialize(supplier.getA());
        //Hibernate.initialize(supplier.getZs());
        }
        
        return suppliers;
    }

    @Transactional(rollbackFor = SupplierNotFoundException.class)
    @Override
    public Supplier update(Supplier updated) throws SupplierNotFoundException {

        Supplier supplier = supplierRepository.findOne(updated.getId());

        if (supplier == null) {
            throw new SupplierNotFoundException();
        }

        BeanUtils.copyProperties(updated, supplier);
        return supplierRepository.save(supplier);
    }

    @Transactional(rollbackFor = SupplierNotFoundException.class)
    @Override
    public Supplier copy(final Supplier copied) {

        Supplier supplier = new Supplier();
        BeanUtils.copyProperties(copied, supplier);
        supplier.setId(null);

        return supplierRepository.save(supplier);
    }

    @Transactional
    @Override
    public Iterable<Supplier> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<Supplier> search(_SearchDTO pageable) {
        return findAll();
    }
}