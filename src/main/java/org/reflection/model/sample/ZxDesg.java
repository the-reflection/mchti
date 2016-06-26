package org.reflection.model.sample;

import com.oith.annotation.MacSearchable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.NotEmpty;
import org.reflection.model.com.AbstractEntity;

@Entity
@Table(name = "ZX_DESG")
@XmlRootElement
public class ZxDesg extends AbstractEntity {

    @Basic(optional = false)
    @Column(nullable = false, unique = true, length = 20)
    private String code;
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
    @Column(name = "SL_NO")
    private Integer slNo;
    @MacSearchable
    @Basic(optional = false)
    @Column(name = "TITLE", nullable = false, length = 100)
    @NotNull
    @Size(min = 1, max = 100)
    @NotEmpty
    private String title;
    @Column(name = "TITLE_BNG", length = 100)
    private String titleBng;
    @MacSearchable
    @Column(name = "REMARKS", length = 500)
    private String remarks;

    public ZxDesg() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleBng() {
        return titleBng;
    }

    public void setTitleBng(String titleBng) {
        this.titleBng = titleBng;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return title;
    }
}
