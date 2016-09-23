package org.reflection.model.com;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(catalog = "MCHTI", name = "ADM_REPORT")
//@CompoundIndexes({
//    @CompoundIndex(unique = true, name = "client_code_idx", def = "{'client': 1, 'code': 1}"),
//    @CompoundIndex(unique = true, name = "client_fileName_idx", def = "{'client': 1, 'fileName': 1}"),
//    @CompoundIndex(unique = true, name = "client_module_title_idx", def = "{'client': 1, 'module': 1, 'title': 1}")
//})
public class AdmReport extends AbstractCodeableEntity {

    @JoinColumn(name = "ADM_MODULE_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private AdmModule admModule;
    @NotNull
    @Size(min = 3, max = 30)
    @Column(name = "FILE_NAME", unique = true)
    private String fileName;
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
    @Column(name = "SL_NO")
    private Integer slNo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "admReport", fetch = FetchType.EAGER)
    @OrderBy(value = "slNo ASC")
    private Set<AdmReportDetail> admReportDetails = new LinkedHashSet<>();

    @ElementCollection(targetClass = AdmReportFormat.class, fetch = FetchType.EAGER)
    @JoinTable(catalog = "MCHTI",
            name = "ADM_REPORT_SUPPORT_FORMAT",
            joinColumns = @JoinColumn(name = "ADM_REPORT_ID"))
    @Column(name = "SUPPORT_FORMAT", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<AdmReportFormat> supportFormats;

    @Size(max = 500)
    private String remarks;

    public AdmReport() {
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Set<AdmReportDetail> getAdmReportDetails() {
        return admReportDetails;
    }

    public void setAdmReportDetails(Set<AdmReportDetail> admReportDetails) {
        this.admReportDetails = admReportDetails;
    }

    public Set<AdmReportFormat> getSupportFormats() {
        return supportFormats;
    }

    public void setSupportFormats(Set<AdmReportFormat> supportFormats) {
        this.supportFormats = supportFormats;
    }

    public AdmModule getAdmModule() {
        return admModule;
    }

    public void setAdmModule(AdmModule admModule) {
        this.admModule = admModule;
    }

}
