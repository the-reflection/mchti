package org.reflection.service;

import org.reflection.model.com.AdmReportDetail;
import org.reflection.exception.AdmReportDetailNotFoundException;
import org.reflection.dto._SearchDTO;
import java.math.BigInteger;

public interface AdmReportDetailService {



    public AdmReportDetail findById(BigInteger id);
    
    public AdmReportDetail create(AdmReportDetail admReportDetail);
    
    public AdmReportDetail update(AdmReportDetail admReportDetail) throws AdmReportDetailNotFoundException;
    
    public AdmReportDetail copy(AdmReportDetail admReportDetail);
    
    public AdmReportDetail delete(BigInteger id) throws AdmReportDetailNotFoundException;
   
    public Iterable<AdmReportDetail> search(_SearchDTO pageable);
    
    public Iterable<AdmReportDetail> findAll(_SearchDTO pageable);
    
    public Iterable<AdmReportDetail> findAll();
}