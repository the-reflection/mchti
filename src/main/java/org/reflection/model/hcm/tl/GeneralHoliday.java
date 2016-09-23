package org.reflection.model.hcm.tl;

import org.reflection.model.hcm.enums.HolidayType;
import com.oith.annotation.MacSearchable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.NotEmpty;
import org.reflection.model.com.AbstractEntity;

@Entity
@Table(catalog = "MCHTI", name = "GENERAL_HOLIDAY")
@XmlRootElement
public class GeneralHoliday extends AbstractEntity {

    @Basic(optional = false)
    @Column(nullable = false, unique = true, length = 20)
    private String code;
    @MacSearchable
    @Basic(optional = false)
    @Column(name = "TITLE", nullable = false, length = 100)
    @NotNull
    @Size(min = 1, max = 100)
    @NotEmpty
    private String title;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "HOLIDAY_TYPE", nullable = false, length = 30)
    private HolidayType holidayType;
    @NotNull
    @Column(name = "ON_DAY", nullable = false)
    private Integer onDay;
    @NotNull
    @Column(name = "ON_MONTH", nullable = false)
    private Integer onMonth;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
    @Size(min = 0, max = 500)
    private String remarks;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HolidayType getHolidayType() {
        return holidayType;
    }

    public void setHolidayType(HolidayType holidayType) {
        this.holidayType = holidayType;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getOnDay() {
        return onDay;
    }

    public void setOnDay(Integer onDay) {
        this.onDay = onDay;
    }

    public Integer getOnMonth() {
        return onMonth;
    }

    public void setOnMonth(Integer onMonth) {
        this.onMonth = onMonth;
    }

}
