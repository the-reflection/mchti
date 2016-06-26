package org.reflection.service;

import org.reflection.model.com.Company;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.CompanyNotFoundException;
import org.reflection.repositories.CompanyRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("companyService")
@Transactional(readOnly = true)
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;



    @Transactional
    @Override
    public Company create(Company lookup) {
        return companyRepository.save(lookup);
    }

    @Override
    @Transactional
    public Company findById(BigInteger id) {
        Company company=companyRepository.findOne(id);

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return company;
    }

    @Override
    @Transactional(rollbackFor = CompanyNotFoundException.class)
    public Company delete(BigInteger id) throws CompanyNotFoundException {

        Company company = companyRepository.findOne(id);

        if (company == null) {
            throw new CompanyNotFoundException();
        }
        companyRepository.delete(id);
        return company;
    }

    @Override
    @Transactional
    public Iterable<Company> findAll() {
        Iterable<Company> companys=companyRepository.findAll();
        
        for (Company company : companys) {

        //Hibernate.initialize(company.getA());
        //Hibernate.initialize(company.getZs());
        }
        
        return companys;
    }

    @Transactional(rollbackFor = CompanyNotFoundException.class)
    @Override
    public Company update(Company updated) throws CompanyNotFoundException {

        Company company = companyRepository.findOne(updated.getId());

        if (company == null) {
            throw new CompanyNotFoundException();
        }

        BeanUtils.copyProperties(updated, company);
        return companyRepository.save(company);
    }

    @Transactional(rollbackFor = CompanyNotFoundException.class)
    @Override
    public Company copy(final Company copied) {

        Company company = new Company();
        BeanUtils.copyProperties(copied, company);
        company.setId(null);

        return companyRepository.save(company);
    }

    @Transactional
    @Override
    public Iterable<Company> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<Company> search(_SearchDTO pageable) {
        return findAll();
    }
}