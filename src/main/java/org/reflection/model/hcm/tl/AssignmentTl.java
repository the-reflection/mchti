package org.reflection.model.hcm.tl;

import org.reflection.model.com.Employee;
import org.reflection.model.hcm.enums.Day;
import com.oith.annotation.MacSearchable;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.math.BigInteger;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "ASSIGNMENT_TL")
@XmlRootElement
public class AssignmentTl implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Basic(optional = false)
    private BigInteger id;
    @Version
    private Integer version;
    @MacSearchable
    @Basic(optional = false)
    @Column(name = "CODE", length = 20, nullable = false)
    private String code;
    @NotNull
    @JoinColumn(name = "EMPLOYEE_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Employee employee;

    @MacSearchable
    @Column(name = "START_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date startDate;
    @MacSearchable
    @Column(name = "END_DATE")
    @Temporal(TemporalType.DATE)
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date endDate;
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "WEEKEND_SHIFT_OFF_DAY", length = 9, nullable = false)
    private Day weekendShiftOffDay;
    @JoinColumn(name = "SHIFT_ID", nullable = true)
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private Shift shift;
    @JoinColumn(name = "ROSTER_ID", nullable = true)
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private Roster roster;
    @Column(name = "IS_OVERTIME")
    private Boolean isOvertime;

    public AssignmentTl() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public Roster getRoster() {
        return roster;
    }

    public void setRoster(Roster roster) {
        this.roster = roster;
    }

    public Boolean getIsOvertime() {
        return isOvertime;
    }

    public void setIsOvertime(Boolean isOvertime) {
        this.isOvertime = isOvertime;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Day getWeekendShiftOffDay() {
        return weekendShiftOffDay;
    }

    public void setWeekendShiftOffDay(Day weekendShiftOffDay) {
        this.weekendShiftOffDay = weekendShiftOffDay;
    }

}
