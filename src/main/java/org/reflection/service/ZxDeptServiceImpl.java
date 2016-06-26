package org.reflection.service;

import org.reflection.model.sample.ZxDept;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.ZxDeptNotFoundException;
import org.reflection.repositories.ZxDeptRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("zxDeptService")
@Transactional(readOnly = true)
public class ZxDeptServiceImpl implements ZxDeptService {

    @Autowired
    private ZxDeptRepository zxDeptRepository;



    @Transactional
    @Override
    public ZxDept create(ZxDept lookup) {
        return zxDeptRepository.save(lookup);
    }

    @Override
    @Transactional
    public ZxDept findById(BigInteger id) {
        ZxDept zxDept=zxDeptRepository.findOne(id);

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return zxDept;
    }

    @Override
    @Transactional(rollbackFor = ZxDeptNotFoundException.class)
    public ZxDept delete(BigInteger id) throws ZxDeptNotFoundException {

        ZxDept zxDept = zxDeptRepository.findOne(id);

        if (zxDept == null) {
            throw new ZxDeptNotFoundException();
        }
        zxDeptRepository.delete(id);
        return zxDept;
    }

    @Override
    @Transactional
    public Iterable<ZxDept> findAll() {
        Iterable<ZxDept> zxDepts=zxDeptRepository.findAll();
        
        for (ZxDept zxDept : zxDepts) {

        //Hibernate.initialize(zxDept.getA());
        //Hibernate.initialize(zxDept.getZs());
        }
        
        return zxDepts;
    }

    @Transactional(rollbackFor = ZxDeptNotFoundException.class)
    @Override
    public ZxDept update(ZxDept updated) throws ZxDeptNotFoundException {

        ZxDept zxDept = zxDeptRepository.findOne(updated.getId());

        if (zxDept == null) {
            throw new ZxDeptNotFoundException();
        }

        BeanUtils.copyProperties(updated, zxDept);
        return zxDeptRepository.save(zxDept);
    }

    @Transactional(rollbackFor = ZxDeptNotFoundException.class)
    @Override
    public ZxDept copy(final ZxDept copied) {

        ZxDept zxDept = new ZxDept();
        BeanUtils.copyProperties(copied, zxDept);
        zxDept.setId(null);

        return zxDeptRepository.save(zxDept);
    }

    @Transactional
    @Override
    public Iterable<ZxDept> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<ZxDept> search(_SearchDTO pageable) {
        return findAll();
    }
}