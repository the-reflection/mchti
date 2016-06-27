package org.reflection.service;

import org.reflection.model.com.AdmParam;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmParamNotFoundException;
import org.reflection.repositories.AdmParamRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("admParamService")
@Transactional(readOnly = true)
public class AdmParamServiceImpl implements AdmParamService {

    @Autowired
    private AdmParamRepository admParamRepository;



    @Transactional
    @Override
    public AdmParam create(AdmParam lookup) {
        return admParamRepository.save(lookup);
    }

    @Override
    @Transactional
    public AdmParam findById(BigInteger id) {
        AdmParam admParam=admParamRepository.findOne(id);

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return admParam;
    }

    @Override
    @Transactional(rollbackFor = AdmParamNotFoundException.class)
    public AdmParam delete(BigInteger id) throws AdmParamNotFoundException {

        AdmParam admParam = admParamRepository.findOne(id);

        if (admParam == null) {
            throw new AdmParamNotFoundException();
        }
        admParamRepository.delete(id);
        return admParam;
    }

    @Override
    @Transactional
    public Iterable<AdmParam> findAll() {
        Iterable<AdmParam> admParams=admParamRepository.findAll();
        
        for (AdmParam admParam : admParams) {

        //Hibernate.initialize(admParam.getA());
        //Hibernate.initialize(admParam.getZs());
        }
        
        return admParams;
    }

    @Transactional(rollbackFor = AdmParamNotFoundException.class)
    @Override
    public AdmParam update(AdmParam updated) throws AdmParamNotFoundException {

        AdmParam admParam = admParamRepository.findOne(updated.getId());

        if (admParam == null) {
            throw new AdmParamNotFoundException();
        }

        BeanUtils.copyProperties(updated, admParam);
        return admParamRepository.save(admParam);
    }

    @Transactional(rollbackFor = AdmParamNotFoundException.class)
    @Override
    public AdmParam copy(final AdmParam copied) {

        AdmParam admParam = new AdmParam();
        BeanUtils.copyProperties(copied, admParam);
        admParam.setId(null);

        return admParamRepository.save(admParam);
    }

    @Transactional
    @Override
    public Iterable<AdmParam> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<AdmParam> search(_SearchDTO pageable) {
        return findAll();
    }
}