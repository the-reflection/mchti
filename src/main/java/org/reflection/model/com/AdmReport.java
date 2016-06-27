package org.reflection.model.com;

import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ADM_REPORT")
//@CompoundIndexes({
//    @CompoundIndex(unique = true, name = "client_code_idx", def = "{'client': 1, 'code': 1}"),
//    @CompoundIndex(unique = true, name = "client_fileName_idx", def = "{'client': 1, 'fileName': 1}"),
//    @CompoundIndex(unique = true, name = "client_module_title_idx", def = "{'client': 1, 'module': 1, 'title': 1}")
//})
public class AdmReport extends AbstractEntity {

    @NotNull
    @Size(min = 2, max = 4)
    @Column(name = "code", unique = true)
    private String code;
//    @NotNull
//    private Module module;
    @NotNull
    @Size(min = 3, max = 50)
    private String title;
    @NotNull
    @Size(min = 3, max = 30)
    @Column(name = "FILE_NAME", unique = true)
    private String fileName;
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
    @Column(name = "SL_NO")
    private Integer slNo;

//    private Set<ReportFormat> supportFormats;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "admReport", fetch = FetchType.EAGER)
    @OrderBy(value = "slNo DESC")
    private Set<AdmReportDetail> admReportDetails;

    @Size(max = 500)
    private String remarks;

    public AdmReport() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @Override
    public String toString() {
        //return ToStringBuilder.reflectionToString(this);
        return title;
    }
}
