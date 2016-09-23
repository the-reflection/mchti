package org.reflection.model.com;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(catalog = "MCHTI", name = "ADM_PROCESS")
//@CompoundIndexes({
//    @CompoundIndex(unique = true, name = "client_code_idx", def = "{'client': 1, 'code': 1}"),
//    @CompoundIndex(unique = true, name = "client_module_title_idx", def = "{'client': 1, 'module': 1, 'title': 1}")
//})
public class AdmProcess extends AbstractCodeableEntity {

    @JoinColumn(name = "ADM_MODULE_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private AdmModule admModule;
    @NotNull
    @Size(min = 3, max = 2000)
    private String cmd;
    @Size(max = 2000)
    private String query;
    @Column(name = "QUERY_ALIAS", length = 500)
    private String queryAlias;
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
    @Column(name = "SL_NO")
    private Integer slNo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "admProcess", fetch = FetchType.EAGER)
    @OrderBy(value = "slNo DESC")
    private Set<AdmProcessDetail> admProcessDetails;

    @Column(name = "PROCESS_BTNS", length = 1000)
    private String processBtns;

    @Size(max = 500)
    private String remarks;

    public AdmProcess() {
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

    public Set<AdmProcessDetail> getAdmProcessDetails() {
        return admProcessDetails;
    }

    public void setAdmProcessDetails(Set<AdmProcessDetail> admProcessDetails) {
        this.admProcessDetails = admProcessDetails;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQueryAlias() {
        return queryAlias;
    }

    public void setQueryAlias(String queryAlias) {
        this.queryAlias = queryAlias;
    }

    public String getProcessBtns() {
        return processBtns;
    }

    public void setProcessBtns(String processBtns) {
        this.processBtns = processBtns;
    }

    public AdmModule getAdmModule() {
        return admModule;
    }

    public void setAdmModule(AdmModule admModule) {
        this.admModule = admModule;
    }
}
