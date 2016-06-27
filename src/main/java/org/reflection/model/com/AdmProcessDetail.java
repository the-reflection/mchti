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
@Table(name = "ADM_PROCESS_DETAIL", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ADM_PROCESS_ID", "ADM_PARAM_ID", "ADM_ZONE_TYPE"})})
public class AdmProcessDetail extends AbstractEntity {

    @Column(name = "SL_NO")
    private Integer slNo;

    @JoinColumn(name = "ADM_PROCESS_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AdmProcess admProcess;

    @JoinColumn(name = "ADM_PARAM_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AdmParam admParam;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "ADM_ZONE_TYPE", length = 30)
    private AdmZoneType admZoneType;

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
}
