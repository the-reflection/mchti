package org.reflection.service;

import org.reflection.model.com.SupplierCompany;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.SupplierCompanyNotFoundException;
import org.reflection.repositories.SupplierCompanyRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("supplierCompanyService")
@Transactional(readOnly = true)
public class SupplierCompanyServiceImpl implements SupplierCompanyService {

    @Autowired
    private SupplierCompanyRepository supplierCompanyRepository;

    @Transactional(readOnly = true)
    @Override
    public SupplierCompany findByCode(String code) {
        return supplierCompanyRepository.findByCode(code);
    }

    @Transactional
    @Override
    public SupplierCompany create(SupplierCompany lookup) {
        return supplierCompanyRepository.save(lookup);
    }

    @Override
    @Transactional
    public SupplierCompany findById(BigInteger id) {
        SupplierCompany supplierCompany=supplierCompanyRepository.findOne(id);

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return supplierCompany;
    }

    @Override
    @Transactional(rollbackFor = SupplierCompanyNotFoundException.class)
    public SupplierCompany delete(BigInteger id) throws SupplierCompanyNotFoundException {

        SupplierCompany supplierCompany = supplierCompanyRepository.findOne(id);

        if (supplierCompany == null) {
            throw new SupplierCompanyNotFoundException();
        }
        supplierCompanyRepository.delete(id);
        return supplierCompany;
    }

    @Override
    @Transactional
    public Iterable<SupplierCompany> findAll() {
        Iterable<SupplierCompany> supplierCompanys=supplierCompanyRepository.findAll();
        
        for (SupplierCompany supplierCompany : supplierCompanys) {

        //Hibernate.initialize(supplierCompany.getA());
        //Hibernate.initialize(supplierCompany.getZs());
        }
        
        return supplierCompanys;
    }

    @Transactional(rollbackFor = SupplierCompanyNotFoundException.class)
    @Override
    public SupplierCompany update(SupplierCompany updated) throws SupplierCompanyNotFoundException {

        SupplierCompany supplierCompany = supplierCompanyRepository.findOne(updated.getId());

        if (supplierCompany == null) {
            throw new SupplierCompanyNotFoundException();
        }

        BeanUtils.copyProperties(updated, supplierCompany);
        return supplierCompanyRepository.save(supplierCompany);
    }

    @Transactional(rollbackFor = SupplierCompanyNotFoundException.class)
    @Override
    public SupplierCompany copy(final SupplierCompany copied) {

        SupplierCompany supplierCompany = new SupplierCompany();
        BeanUtils.copyProperties(copied, supplierCompany);
        supplierCompany.setId(null);

        return supplierCompanyRepository.save(supplierCompany);
    }

    @Transactional
    @Override
    public Iterable<SupplierCompany> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<SupplierCompany> search(_SearchDTO pageable) {
        return findAll();
    }
}