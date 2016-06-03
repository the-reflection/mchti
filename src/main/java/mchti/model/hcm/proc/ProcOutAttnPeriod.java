package mchti.model.hcm.proc;

import mchti.model.hcm.enums.GenType;
import mchti.model.hcm.enums.AttnSts;
import mchti.model.hcm.cr.Emp;
import mchti.model.hcm.tl.Period;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.math.BigInteger; import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "PROC_OUT_ATTN_PERIOD")
@XmlRootElement
public class ProcOutAttnPeriod implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@SequenceGenerator(name="HIBERNATE_SEQUENCE", sequenceName="HIBERNATE_SEQUENCE")
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HIBERNATE_SEQUENCE")
    @Basic(optional = false)
    private BigInteger id;
    @NotNull
    @Column(name = "GEN_TYPE", length = 30)
    private GenType genType;
    @NotNull
    @Column(name = "ATTN_STS", length = 30)
    private AttnSts attnSts;
    @JoinColumn(name = "PERIOD_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Period period;
    @NotNull
    @JoinColumn(name = "EMP_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Emp emp;
    @Column(name = "day_present")
    private Integer dayPresent;
    @Column(name = "day_Absent")
    private Integer dayAbsent;
    @Column(name = "day_late")
    private Integer dayLate;
    @Column(name = "day_early_out")
    private Integer dayEarlyOut;
    @Column(name = "day_late_in_early_out")
    private Integer dayLateInEarlyOut;
    @Column(name = "day_Cl")
    private Integer dayCl;
    @Column(name = "day_sl")
    private Integer daySl;
    @Column(name = "day_el")
    private Integer dayEl;
    @Column(name = "day_ml")
    private Integer dayMl;
    @Column(name = "day_lwp")
    private Integer dayLwp;

    @Column(name = "ot_Hour_Normal")
    private Double otHourNormal;
    @Column(name = "ot_Hour_Sro")
    private Double otHourSro;
    @Column(name = "ot_Hour_Extra")
    private Double otHourExtra;
    @Column(name = "ot_Hour_Holiday")
    private Double otHourHoliday;
    @Column(name = "day_Holiday_Work")
    private Integer dayHolidayWork;
    @Column(name = "day_Holiday")
    private Integer dayHoliday;

    public GenType getGenType() {
        return genType;
    }

    public void setGenType(GenType genType) {
        this.genType = genType;
    }

    public AttnSts getAttnSts() {
        return attnSts;
    }

    public void setAttnSts(AttnSts attnSts) {
        this.attnSts = attnSts;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Emp getEmp() {
        return emp;
    }

    public void setEmp(Emp emp) {
        this.emp = emp;
    }

    public Integer getDayPresent() {
        return dayPresent;
    }

    public void setDayPresent(Integer dayPresent) {
        this.dayPresent = dayPresent;
    }

    public Integer getDayAbsent() {
        return dayAbsent;
    }

    public void setDayAbsent(Integer dayAbsent) {
        this.dayAbsent = dayAbsent;
    }

    public Integer getDayLate() {
        return dayLate;
    }

    public void setDayLate(Integer dayLate) {
        this.dayLate = dayLate;
    }

    public Integer getDayEarlyOut() {
        return dayEarlyOut;
    }

    public void setDayEarlyOut(Integer dayEarlyOut) {
        this.dayEarlyOut = dayEarlyOut;
    }

    public Integer getDayLateInEarlyOut() {
        return dayLateInEarlyOut;
    }

    public void setDayLateInEarlyOut(Integer dayLateInEarlyOut) {
        this.dayLateInEarlyOut = dayLateInEarlyOut;
    }

    public Integer getDayCl() {
        return dayCl;
    }

    public void setDayCl(Integer dayCl) {
        this.dayCl = dayCl;
    }

    public Integer getDaySl() {
        return daySl;
    }

    public void setDaySl(Integer daySl) {
        this.daySl = daySl;
    }

    public Integer getDayEl() {
        return dayEl;
    }

    public void setDayEl(Integer dayEl) {
        this.dayEl = dayEl;
    }

    public Integer getDayMl() {
        return dayMl;
    }

    public void setDayMl(Integer dayMl) {
        this.dayMl = dayMl;
    }

    public Integer getDayLwp() {
        return dayLwp;
    }

    public void setDayLwp(Integer dayLwp) {
        this.dayLwp = dayLwp;
    }

    public Double getOtHourNormal() {
        return otHourNormal;
    }

    public void setOtHourNormal(Double otHourNormal) {
        this.otHourNormal = otHourNormal;
    }

    public Double getOtHourSro() {
        return otHourSro;
    }

    public void setOtHourSro(Double otHourSro) {
        this.otHourSro = otHourSro;
    }

    public Double getOtHourExtra() {
        return otHourExtra;
    }

    public void setOtHourExtra(Double otHourExtra) {
        this.otHourExtra = otHourExtra;
    }

    public Double getOtHourHoliday() {
        return otHourHoliday;
    }

    public void setOtHourHoliday(Double otHourHoliday) {
        this.otHourHoliday = otHourHoliday;
    }

    public Integer getDayHolidayWork() {
        return dayHolidayWork;
    }

    public void setDayHolidayWork(Integer dayHolidayWork) {
        this.dayHolidayWork = dayHolidayWork;
    }

    public Integer getDayHoliday() {
        return dayHoliday;
    }

    public void setDayHoliday(Integer dayHoliday) {
        this.dayHoliday = dayHoliday;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

}
