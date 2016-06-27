package org.reflection.model.com;

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
@Table(name = "ADM_PROCESS")
//@CompoundIndexes({
//    @CompoundIndex(unique = true, name = "client_code_idx", def = "{'client': 1, 'code': 1}"),
//    @CompoundIndex(unique = true, name = "client_module_title_idx", def = "{'client': 1, 'module': 1, 'title': 1}")
//})
public class AdmProcess extends AbstractEntity {

    @NotNull
    @Size(min = 2, max = 6)
    @Column(name = "code", unique = true)
    private String code;
    // @NotNull
    //private AllEnum.Module module;
    @NotNull
    @Size(min = 3, max = 50)
    private String title;
    @NotNull
    @Size(min = 3, max = 2000)
    private String cmd;
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
    @Column(name = "SL_NO")
    private Integer slNo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "admProcess", fetch = FetchType.EAGER)
    @OrderBy(value = "slNo DESC")
    private Set<AdmProcessDetail> admProcessDetails;

    @Size(max = 500)
    private String remarks;

    public AdmProcess() {
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

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        //return ToStringBuilder.reflectionToString(this);
        return title;
    }

    public Set<AdmProcessDetail> getAdmProcessDetails() {
        return admProcessDetails;
    }

    public void setAdmProcessDetails(Set<AdmProcessDetail> admProcessDetails) {
        this.admProcessDetails = admProcessDetails;
    }
}
