package org.reflection.service;

import org.reflection.model.com.AdmModule;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmModuleNotFoundException;
import org.reflection.repositories.AdmModuleRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("admModuleService")
@Transactional(readOnly = true)
public class AdmModuleServiceImpl implements AdmModuleService {

    @Autowired
    private AdmModuleRepository admModuleRepository;

    @Transactional(readOnly = true)
    @Override
    public AdmModule findByCode(String code) {
        return admModuleRepository.findByCode(code);
    }

    @Transactional
    @Override
    public AdmModule create(AdmModule lookup) {
        return admModuleRepository.saveAndFlush(lookup);
    }

    @Override
    @Transactional
    public AdmModule findById(BigInteger id) {
        AdmModule admModule=admModuleRepository.getOne(id);

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return admModule;
    }

    @Override
    @Transactional(rollbackFor = AdmModuleNotFoundException.class)
    public AdmModule delete(BigInteger id) throws AdmModuleNotFoundException {

        AdmModule admModule = admModuleRepository.getOne(id);

        if (admModule == null) {
            throw new AdmModuleNotFoundException();
        }
        admModuleRepository.delete(id);
        return admModule;
    }

    @Override
    @Transactional
    public Iterable<AdmModule> findAll() {
        Iterable<AdmModule> admModules=admModuleRepository.findAll();
        
        for (AdmModule admModule : admModules) {

        //Hibernate.initialize(admModule.getA());
        //Hibernate.initialize(admModule.getZs());
        }
        
        return admModules;
    }

    @Transactional(rollbackFor = AdmModuleNotFoundException.class)
    @Override
    public AdmModule update(AdmModule updated) throws AdmModuleNotFoundException {

        AdmModule admModule = admModuleRepository.getOne(updated.getId());

        if (admModule == null) {
            throw new AdmModuleNotFoundException();
        }

        BeanUtils.copyProperties(updated, admModule);
        return admModuleRepository.save(admModule);
    }

    @Transactional(rollbackFor = AdmModuleNotFoundException.class)
    @Override
    public AdmModule copy(final AdmModule copied) {

        AdmModule admModule = new AdmModule();
        BeanUtils.copyProperties(copied, admModule);
        admModule.setId(null);

        return admModuleRepository.save(admModule);
    }

    @Transactional
    @Override
    public Iterable<AdmModule> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<AdmModule> search(_SearchDTO pageable) {
        return findAll();
    }
}