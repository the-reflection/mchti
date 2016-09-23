package org.reflection.model.com;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(catalog = "MCHTI", name = "ADM_PROCESS_DETAIL", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ADM_PROCESS_ID", "ADM_PARAM_ID", "ADM_ZONE_TYPE"})})
public class AdmProcessDetail extends AbstractEntity {

    @Column(name = "SL_NO")
    private Integer slNo;

    @JoinColumn(name = "ADM_PROCESS_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private AdmProcess admProcess;

    @JoinColumn(name = "ADM_PARAM_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private AdmParam admParam;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "ADM_ZONE_TYPE", length = 30)
    private AdmZoneType admZoneType;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
    @Column(name = "IS_MANDATORY")
    private Boolean isMandatory;
    @Column(name = "DEFAULT_VAL")
    private String defaultVal;
    @Column(name = "HELP_TEXT")
    private String helpText;

    public AdmProcessDetail() {
    }

    public AdmParam getAdmParam() {
        return admParam;
    }

    public void setAdmParam(AdmParam admParam) {
        this.admParam = admParam;
    }

    @Override
    public String toString() {
        return admParam.getParamName();
    }

    public Integer getSlNo() {
        return slNo;
    }

    public void setSlNo(Integer slNo) {
        this.slNo = slNo;
    }

    public AdmZoneType getAdmZoneType() {
        return admZoneType;
    }

    public void setAdmZoneType(AdmZoneType admZoneType) {
        this.admZoneType = admZoneType;
    }

    public AdmProcess getAdmProcess() {
        return admProcess;
    }

    public void setAdmProcess(AdmProcess admProcess) {
        this.admProcess = admProcess;
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
