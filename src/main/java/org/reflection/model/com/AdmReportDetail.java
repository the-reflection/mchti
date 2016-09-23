package org.reflection.model.com;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(catalog = "MCHTI", name = "ADM_REPORT_DETAIL", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ADM_REPORT_ID", "ADM_PARAM_ID"})})
public class AdmReportDetail extends AbstractEntity {

    @Column(name = "SL_NO")
    private Integer slNo;
    @JoinColumn(name = "ADM_REPORT_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private AdmReport admReport;

    @JoinColumn(name = "ADM_PARAM_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private AdmParam admParam;
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
    @Column(name = "IS_MANDATORY")
    private Boolean isMandatory;
    @Column(name = "DEFAULT_VAL")
    private String defaultVal;
    @Column(name = "HELP_TEXT")
    private String helpText;

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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsMandatory() {
        return isMandatory;
    }

    public void setIsMandatory(Boolean isMandatory) {
        this.isMandatory = isMandatory;
    }

    public String getDefaultVal() {
        return defaultVal;
    }

    public void setDefaultVal(String defaultVal) {
        this.defaultVal = defaultVal;
    }

    public String getHelpText() {
        return helpText;
    }

    public void setHelpText(String helpText) {
        this.helpText = helpText;
    }

}
