package org.reflection.model.hcm.proc;

import com.oith.annotation.MacCareless;
import org.reflection.model.hcm.enums.HolidayType;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EmbeddedId;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@MacCareless
@Entity
@Table(catalog = "MCHTI", name = "PROC_OUT_CALENDER")
@XmlRootElement
public class ProcOutCalender implements Serializable {

    @EmbeddedId
    private ProcOutCalenderPK procOutCalenderPK;
    @Column(name = "HOLIDAY_TYPE", length = 30)
    @Enumerated(EnumType.STRING)
    private HolidayType holidayType;
    @Column(name = "IS_APPLICABLE")
    private Boolean isApplicable;
    @Column(name = "REMARKS", length = 500)
    private String remarks;

    public ProcOutCalender() {
    }

    public ProcOutCalender(ProcOutCalenderPK procOutCalenderPK) {
        this.procOutCalenderPK = procOutCalenderPK;
    }

    public HolidayType getHolidayType() {
        return holidayType;
    }

    public void setHolidayType(HolidayType holidayType) {
        this.holidayType = holidayType;
    }

    public Boolean getIsApplicable() {
        return isApplicable;
    }

    public void setIsApplicable(Boolean isApplicable) {
        this.isApplicable = isApplicable;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public ProcOutCalenderPK getProcOutCalenderPK() {
        return procOutCalenderPK;
    }

    public void setProcOutCalenderPK(ProcOutCalenderPK procOutCalenderPK) {
        this.procOutCalenderPK = procOutCalenderPK;
    }

}
