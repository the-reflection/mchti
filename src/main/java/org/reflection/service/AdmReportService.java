package org.reflection.service;

import org.reflection.model.com.AdmReport;
import org.reflection.exception.AdmReportNotFoundException;
import org.reflection.dto._SearchDTO;
import java.math.BigInteger;

public interface AdmReportService {

    public AdmReport findById(BigInteger id);

    public AdmReport findByCode(String code);

    public AdmReport create(AdmReport admReport);

    public AdmReport update(AdmReport admReport) throws AdmReportNotFoundException;

    public AdmReport copy(AdmReport admReport);

    public AdmReport delete(BigInteger id) throws AdmReportNotFoundException;

    public Iterable<AdmReport> search(_SearchDTO pageable);

    public Iterable<AdmReport> findAll(_SearchDTO pageable);

    public Iterable<AdmReport> findAll();
}
