package org.reflection.service;

import org.reflection.model.hcm.tl.CustomizedHolidayApp;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.CustomizedHolidayAppNotFoundException;
import org.reflection.repositories.CustomizedHolidayAppRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("customizedHolidayAppService")
@Transactional(readOnly = true)
public class CustomizedHolidayAppServiceImpl implements CustomizedHolidayAppService {

    @Autowired
    private CustomizedHolidayAppRepository customizedHolidayAppRepository;



    @Transactional
    @Override
    public CustomizedHolidayApp create(CustomizedHolidayApp lookup) {
        return customizedHolidayAppRepository.save(lookup);
    }

    @Override
    @Transactional
    public CustomizedHolidayApp findById(BigInteger id) {
        CustomizedHolidayApp customizedHolidayApp=customizedHolidayAppRepository.findOne(id);

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return customizedHolidayApp;
    }

    @Override
    @Transactional(rollbackFor = CustomizedHolidayAppNotFoundException.class)
    public CustomizedHolidayApp delete(BigInteger id) throws CustomizedHolidayAppNotFoundException {

        CustomizedHolidayApp customizedHolidayApp = customizedHolidayAppRepository.findOne(id);

        if (customizedHolidayApp == null) {
            throw new CustomizedHolidayAppNotFoundException();
        }
        customizedHolidayAppRepository.delete(id);
        return customizedHolidayApp;
    }

    @Override
    @Transactional
    public Iterable<CustomizedHolidayApp> findAll() {
        Iterable<CustomizedHolidayApp> customizedHolidayApps=customizedHolidayAppRepository.findAll();
        
        for (CustomizedHolidayApp customizedHolidayApp : customizedHolidayApps) {

        //Hibernate.initialize(customizedHolidayApp.getA());
        //Hibernate.initialize(customizedHolidayApp.getZs());
        }
        
        return customizedHolidayApps;
    }

    @Transactional(rollbackFor = CustomizedHolidayAppNotFoundException.class)
    @Override
    public CustomizedHolidayApp update(CustomizedHolidayApp updated) throws CustomizedHolidayAppNotFoundException {

        CustomizedHolidayApp customizedHolidayApp = customizedHolidayAppRepository.findOne(updated.getId());

        if (customizedHolidayApp == null) {
            throw new CustomizedHolidayAppNotFoundException();
        }

        BeanUtils.copyProperties(updated, customizedHolidayApp);
        return customizedHolidayAppRepository.save(customizedHolidayApp);
    }

    @Transactional(rollbackFor = CustomizedHolidayAppNotFoundException.class)
    @Override
    public CustomizedHolidayApp copy(final CustomizedHolidayApp copied) {

        CustomizedHolidayApp customizedHolidayApp = new CustomizedHolidayApp();
        BeanUtils.copyProperties(copied, customizedHolidayApp);
        customizedHolidayApp.setId(null);

        return customizedHolidayAppRepository.save(customizedHolidayApp);
    }

    @Transactional
    @Override
    public Iterable<CustomizedHolidayApp> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<CustomizedHolidayApp> search(_SearchDTO pageable) {
        return findAll();
    }
}