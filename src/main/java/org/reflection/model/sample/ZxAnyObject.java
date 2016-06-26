package org.reflection.model.sample;

import com.oith.annotation.MacSearchable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.NotEmpty;
import org.reflection.model.com.AbstractEntity;

/**
 *
 * @author mbadiuzzaman
 */
@Entity
@Table(name = "ZX_ANY_OBJECT", catalog = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"TITLE"})})
@XmlRootElement
public class ZxAnyObject extends AbstractEntity {

    @Basic(optional = false)
    @Column(nullable = false, unique = true, length = 20)
    private String code;
    @MacSearchable
    @Basic(optional = false)
    @Column(name = "TITLE", nullable = false, length = 100)
    @NotNull
    @Size(min = 1, max = 100)
    @NotEmpty
    @Pattern(regexp = "[A-Za-z ]*", message = "must contain only letters and spaces")
    private String title;
    @MacSearchable
    @Column(name = "REMARKS", length = 500)
    private String remarks;
    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date startDate;
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
    @Column(name = "SL_NO")
    private Integer slNo;

    public ZxAnyObject() {
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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

    @Override
    public String toString() {
        return title;
    }
}
