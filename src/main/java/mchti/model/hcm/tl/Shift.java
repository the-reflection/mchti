package mchti.model.hcm.tl;

import mchti.model.hcm.enums.ShiftType;
import com.oith.annotation.MacSearchable;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.math.BigInteger;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "SHIFT")
@XmlRootElement
public class Shift implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Basic(optional = false)
    //    @SequenceGenerator(name = "HIBERNATE_SEQUENCE", sequenceName = "HIBERNATE_SEQUENCE")
    //    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HIBERNATE_SEQUENCE")
    //    @GeneratedValue(strategy = GenerationType.TABLE)
    //    @GeneratedValue(strategy = GenerationType.AUTO)
    //    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private BigInteger id;
    @Version
    private Integer version;
    @Basic(optional = false)
    @Column(nullable = false, unique = true, length = 20)
    private String code;
    @MacSearchable
    @Basic(optional = false)
    @Column(name = "TITLE", nullable = false, length = 100)
    @NotNull
    @Size(min = 1, max = 100)
    @NotEmpty
    @Pattern(regexp = "[A-Za-z ]*", message = "Must contain only letters and spaces")
    private String title;
    @Column(name = "TITLE_BNG", length = 500)
    private String titleBng;

    @Column(name = "START_HR", nullable = false)
    @Range(min = 0, max = 23)
    private Integer startHr;
    @Column(name = "END_HR", nullable = false)
    @Range(min = 0, max = 23)
    private Integer endHr;
    @Column(name = "START_MIN")
    @Range(min = 0, max = 59)
    private Integer startMin;
    @Column(name = "END_MIN")
    @Range(min = 0, max = 59)
    private Integer endMin;

    @Column(name = "START_BUF_MIN")
    @Range(min = 0, max = 60)
    private Integer startBufMin;
    @Column(name = "END_BUF_MIN")
    @Range(min = 0, max = 60)
    private Integer endBufMin;
    @Column(name = "ORIENTATION_MIN")
    @Range(min = 0, max = 60)
    private Integer orientationMin;
    @Enumerated(EnumType.STRING)
    @Column(name = "SHIFT_TYPE", length = 30)
    private ShiftType shiftType;
    @MacSearchable
    @Column(name = "REMARKS", length = 500)
    private String remarks;
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
    @Column(name = "SL_NO")
    private Integer slNo;

    public Shift() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getStartHr() {
        return startHr;
    }

    public void setStartHr(Integer startHr) {
        this.startHr = startHr;
    }

    public Integer getEndHr() {
        return endHr;
    }

    public void setEndHr(Integer endHr) {
        this.endHr = endHr;
    }

    public Integer getStartMin() {
        return startMin;
    }

    public void setStartMin(Integer startMin) {
        this.startMin = startMin;
    }

    public Integer getEndMin() {
        return endMin;
    }

    public void setEndMin(Integer endMin) {
        this.endMin = endMin;
    }

    public Integer getStartBufMin() {
        return startBufMin;
    }

    public void setStartBufMin(Integer startBufMin) {
        this.startBufMin = startBufMin;
    }

    public Integer getEndBufMin() {
        return endBufMin;
    }

    public void setEndBufMin(Integer endBufMin) {
        this.endBufMin = endBufMin;
    }

    public Integer getOrientationMin() {
        return orientationMin;
    }

    public void setOrientationMin(Integer orientationMin) {
        this.orientationMin = orientationMin;
    }

    public ShiftType getShiftType() {
        return shiftType;
    }

    public void setShiftType(ShiftType shiftType) {
        this.shiftType = shiftType;
    }

    @Override
    public String toString() {
        return title;
    }
}
