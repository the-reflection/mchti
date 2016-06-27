package org.reflection.service;

import org.reflection.model.com.AdmReport;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmReportNotFoundException;
import org.reflection.repositories.AdmReportRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("admReportService")
@Transactional(readOnly = true)
public class AdmReportServiceImpl implements AdmReportService {

    @Autowired
    private AdmReportRepository admReportRepository;



    @Transactional
    @Override
    public AdmReport create(AdmReport lookup) {
        return admReportRepository.save(lookup);
    }

    @Override
    @Transactional
    public AdmReport findById(BigInteger id) {
        AdmReport admReport=admReportRepository.findOne(id);

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return admReport;
    }

    @Override
    @Transactional(rollbackFor = AdmReportNotFoundException.class)
    public AdmReport delete(BigInteger id) throws AdmReportNotFoundException {

        AdmReport admReport = admReportRepository.findOne(id);

        if (admReport == null) {
            throw new AdmReportNotFoundException();
        }
        admReportRepository.delete(id);
        return admReport;
    }

    @Override
    @Transactional
    public Iterable<AdmReport> findAll() {
        Iterable<AdmReport> admReports=admReportRepository.findAll();
        
        for (AdmReport admReport : admReports) {

        //Hibernate.initialize(admReport.getA());
        //Hibernate.initialize(admReport.getZs());
        }
        
        return admReports;
    }

    @Transactional(rollbackFor = AdmReportNotFoundException.class)
    @Override
    public AdmReport update(AdmReport updated) throws AdmReportNotFoundException {

        AdmReport admReport = admReportRepository.findOne(updated.getId());

        if (admReport == null) {
            throw new AdmReportNotFoundException();
        }

        BeanUtils.copyProperties(updated, admReport);
        return admReportRepository.save(admReport);
    }

    @Transactional(rollbackFor = AdmReportNotFoundException.class)
    @Override
    public AdmReport copy(final AdmReport copied) {

        AdmReport admReport = new AdmReport();
        BeanUtils.copyProperties(copied, admReport);
        admReport.setId(null);

        return admReportRepository.save(admReport);
    }

    @Transactional
    @Override
    public Iterable<AdmReport> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<AdmReport> search(_SearchDTO pageable) {
        return findAll();
    }
}