package org.reflection.model.com;

import org.reflection.model.hcm.enums.LookupKeyword;
import com.oith.annotation.MacSearchable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(catalog = "MCHTI", name = "LOOKUP", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"KEYWORD", "FULL_NAME"})})
@XmlRootElement
public class Lookup extends AbstractCodeableEntity {

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
    @Column(name = "SL_NO")
    private Integer slNo;
    @Column(name = "FULL_NAME_BNG", length = 500)
    private String fullNameBng;
    @MacSearchable
    @Column(name = "REMARKS", length = 500)
    private String remarks;
    @Enumerated(EnumType.STRING)
    @Column(name = "KEYWORD", length = 30)
    private LookupKeyword keyword;

    public Lookup() {
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

    public LookupKeyword getKeyword() {
        return keyword;
    }

    public void setKeyword(LookupKeyword keyword) {
        this.keyword = keyword;
    }

    public String getFullNameBng() {
        return fullNameBng;
    }

    public void setFullNameBng(String fullNameBng) {
        this.fullNameBng = fullNameBng;
    }

}
