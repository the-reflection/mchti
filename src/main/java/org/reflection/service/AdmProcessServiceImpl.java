package org.reflection.service;

import org.reflection.model.com.AdmProcess;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmProcessNotFoundException;
import org.reflection.repositories.AdmProcessRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("admProcessService")
@Transactional(readOnly = true)
public class AdmProcessServiceImpl implements AdmProcessService {

    @Autowired
    private AdmProcessRepository admProcessRepository;



    @Transactional
    @Override
    public AdmProcess create(AdmProcess lookup) {
        return admProcessRepository.save(lookup);
    }

    @Override
    @Transactional
    public AdmProcess findById(BigInteger id) {
        AdmProcess admProcess=admProcessRepository.findOne(id);

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return admProcess;
    }

    @Override
    @Transactional(rollbackFor = AdmProcessNotFoundException.class)
    public AdmProcess delete(BigInteger id) throws AdmProcessNotFoundException {

        AdmProcess admProcess = admProcessRepository.findOne(id);

        if (admProcess == null) {
            throw new AdmProcessNotFoundException();
        }
        admProcessRepository.delete(id);
        return admProcess;
    }

    @Override
    @Transactional
    public Iterable<AdmProcess> findAll() {
        Iterable<AdmProcess> admProcesss=admProcessRepository.findAll();
        
        for (AdmProcess admProcess : admProcesss) {

        //Hibernate.initialize(admProcess.getA());
        //Hibernate.initialize(admProcess.getZs());
        }
        
        return admProcesss;
    }

    @Transactional(rollbackFor = AdmProcessNotFoundException.class)
    @Override
    public AdmProcess update(AdmProcess updated) throws AdmProcessNotFoundException {

        AdmProcess admProcess = admProcessRepository.findOne(updated.getId());

        if (admProcess == null) {
            throw new AdmProcessNotFoundException();
        }

        BeanUtils.copyProperties(updated, admProcess);
        return admProcessRepository.save(admProcess);
    }

    @Transactional(rollbackFor = AdmProcessNotFoundException.class)
    @Override
    public AdmProcess copy(final AdmProcess copied) {

        AdmProcess admProcess = new AdmProcess();
        BeanUtils.copyProperties(copied, admProcess);
        admProcess.setId(null);

        return admProcessRepository.save(admProcess);
    }

    @Transactional
    @Override
    public Iterable<AdmProcess> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<AdmProcess> search(_SearchDTO pageable) {
        return findAll();
    }
}