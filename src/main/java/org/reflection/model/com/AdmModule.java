package org.reflection.model.com;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(catalog = "MCHTI", name = "ADM_MODULE")
public class AdmModule extends AbstractCodeableEntity {

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
    @Column(name = "SL_NO")
    private Integer slNo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "admModule", fetch = FetchType.EAGER)
    @OrderBy(value = "slNo DESC")
    private Set<AdmProcess> admProcesss;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "admModule", fetch = FetchType.EAGER)
    @OrderBy(value = "slNo DESC")
    private Set<AdmReport> admReports;

    @Size(max = 500)
    private String remarks;

    public AdmModule() {
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Integer getSlNo() {
        return slNo;
    }

    public void setSlNo(Integer slNo) {
        this.slNo = slNo;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Set<AdmProcess> getAdmProcesss() {
        return admProcesss;
    }

    public void setAdmProcesss(Set<AdmProcess> admProcesss) {
        this.admProcesss = admProcesss;
    }

    public Set<AdmReport> getAdmReports() {
        return admReports;
    }

    public void setAdmReports(Set<AdmReport> admReports) {
        this.admReports = admReports;
    }

}
