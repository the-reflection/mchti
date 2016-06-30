package org.reflection.model.hcm.tl;

import com.oith.annotation.MacSearchable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.reflection.model.com.AbstractEntity;
import org.reflection.model.hcm.enums.HolidayType;

@Entity
@Table(name = "CUSTOMIZED_HOLIDAY_APP")
@XmlRootElement
public class CustomizedHolidayApp extends AbstractEntity {

    @MacSearchable
    @Basic(optional = false)
    @Column(name = "CODE", length = 20, unique = true, nullable = false)
    private String code;
    @MacSearchable
    @Column(name = "APP_DATE")
    @Temporal(TemporalType.DATE)
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date appDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "HOLIDAY_TYPE", length = 30, nullable = false)
    private HolidayType holidayType;
    @MacSearchable
    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date startDate;
    @MacSearchable
    @Column(name = "END_DATE")
    @Temporal(TemporalType.DATE)
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date endDate;

    @Column(name = "REMARKS", nullable = true, length = 500)
    private String remarks;

    public CustomizedHolidayApp() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getAppDate() {
        return appDate;
    }

    public void setAppDate(Date appDate) {
        this.appDate = appDate;
    }

    public HolidayType getHolidayType() {
        return holidayType;
    }

    public void setHolidayType(HolidayType holidayType) {
        this.holidayType = holidayType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
