package org.reflection.service;

import org.reflection.model.sample.ZxEmp;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.ZxEmpNotFoundException;
import org.reflection.repositories.ZxEmpRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("zxEmpService")
@Transactional(readOnly = true)
public class ZxEmpServiceImpl implements ZxEmpService {

    @Autowired
    private ZxEmpRepository zxEmpRepository;

    @Transactional(readOnly = true)
    @Override
    public ZxEmp findByCode(String code) {
        return zxEmpRepository.findByCode(code);
    }

    @Transactional
    @Override
    public ZxEmp create(ZxEmp lookup) {
        return zxEmpRepository.save(lookup);
    }

    @Override
    @Transactional
    public ZxEmp findById(BigInteger id) {
        ZxEmp zxEmp=zxEmpRepository.findOne(id);
            Hibernate.initialize(zxEmp.getZxDept());
            Hibernate.initialize(zxEmp.getZxDesg());
            Hibernate.initialize(zxEmp.getZxLookupBloodGroup());
            Hibernate.initialize(zxEmp.getZxEmpEduDtls());

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return zxEmp;
    }

    @Override
    @Transactional(rollbackFor = ZxEmpNotFoundException.class)
    public ZxEmp delete(BigInteger id) throws ZxEmpNotFoundException {

        ZxEmp zxEmp = zxEmpRepository.findOne(id);

        if (zxEmp == null) {
            throw new ZxEmpNotFoundException();
        }
        zxEmpRepository.delete(id);
        return zxEmp;
    }

    @Override
    @Transactional
    public Iterable<ZxEmp> findAll() {
        Iterable<ZxEmp> zxEmps=zxEmpRepository.findAll();
        
        for (ZxEmp zxEmp : zxEmps) {
            Hibernate.initialize(zxEmp.getZxDept());
            Hibernate.initialize(zxEmp.getZxDesg());
            Hibernate.initialize(zxEmp.getZxLookupBloodGroup());
            Hibernate.initialize(zxEmp.getZxEmpEduDtls());

        //Hibernate.initialize(zxEmp.getA());
        //Hibernate.initialize(zxEmp.getZs());
        }
        
        return zxEmps;
    }

    @Transactional(rollbackFor = ZxEmpNotFoundException.class)
    @Override
    public ZxEmp update(ZxEmp updated) throws ZxEmpNotFoundException {

        ZxEmp zxEmp = zxEmpRepository.findOne(updated.getId());

        if (zxEmp == null) {
            throw new ZxEmpNotFoundException();
        }

        BeanUtils.copyProperties(updated, zxEmp);
        return zxEmpRepository.save(zxEmp);
    }

    @Transactional(rollbackFor = ZxEmpNotFoundException.class)
    @Override
    public ZxEmp copy(final ZxEmp copied) {

        ZxEmp zxEmp = new ZxEmp();
        BeanUtils.copyProperties(copied, zxEmp);
        zxEmp.setId(null);

        return zxEmpRepository.save(zxEmp);
    }

    @Transactional
    @Override
    public Iterable<ZxEmp> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<ZxEmp> search(_SearchDTO pageable) {
        return findAll();
    }
}