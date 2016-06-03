package mchti.model.hcm.prl;

import mchti.model.hcm.cr.*;
import com.oith.annotation.MacSearchable;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "Assignment_PRL")
@XmlRootElement
public class AssignmentPrl implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@SequenceGenerator(name="HIBERNATE_SEQUENCE", sequenceName="HIBERNATE_SEQUENCE")
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HIBERNATE_SEQUENCE")
    @Basic(optional = false)
    private BigInteger id;
    @Version
    private Integer version;
    @MacSearchable
    @Basic(optional = false)
    @Column(name = "CODE", length = 20, nullable = false)
    private String code;
    @NotNull
    @JoinColumn(name = "EMP_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Emp emp;

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
    @Column(precision = 10, scale = 2)
    @Range(min = 1, max = 100000)
    private Double basic;
    @Column(precision = 10, scale = 2)
    @Range(min = 1, max = 100000)
    private Double hr;
    @Column(precision = 10, scale = 2)
    @Range(min = 1, max = 100000)
    private Double med;
    @Column(precision = 10, scale = 2)
    @Range(min = 1, max = 100000)
    private Double conv;
    @Column(precision = 10, scale = 2)
    @Range(min = 1, max = 100000)
    private Double gross;

    public AssignmentPrl() {
    }

    public Emp getEmp() {
        return emp;
    }

    public void setEmp(Emp emp) {
        this.emp = emp;
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

    public Double getBasic() {
        return basic;
    }

    public void setBasic(Double basic) {
        this.basic = basic;
    }

    public Double getHr() {
        return hr;
    }

    public void setHr(Double hr) {
        this.hr = hr;
    }

    public Double getMed() {
        return med;
    }

    public void setMed(Double med) {
        this.med = med;
    }

    public Double getConv() {
        return conv;
    }

    public void setConv(Double conv) {
        this.conv = conv;
    }

    public Double getGross() {
        return gross;
    }

    public void setGross(Double gross) {
        this.gross = gross;
    }

}
