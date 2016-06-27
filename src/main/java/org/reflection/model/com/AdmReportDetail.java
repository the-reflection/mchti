package org.reflection.model.com;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "ADM_REPORT_DETAIL", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ADM_REPORT_ID", "ADM_PARAM_ID"})})
public class AdmReportDetail extends AbstractEntity {

    @Column(name = "SL_NO")
    private Integer slNo;
    @JoinColumn(name = "ADM_REPORT_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AdmReport admReport;

    @JoinColumn(name = "ADM_PARAM_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AdmParam admParam;

    public AdmReportDetail() {
    }

    public AdmParam getAdmParam() {
        return admParam;
    }

    public void setAdmParam(AdmParam admParam) {
        this.admParam = admParam;
    }

    public Integer getSlNo() {
        return slNo;
    }

    public void setSlNo(Integer slNo) {
        this.slNo = slNo;
    }

    public AdmReport getAdmReport() {
        return admReport;
    }

    public void setAdmReport(AdmReport admReport) {
        this.admReport = admReport;
    }

    @Override
    public String toString() {
        return admParam.getParamName();
    }
}
