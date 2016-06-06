package org.reflection.model.acad;

import com.oith.annotation.MacSearchable;
import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.Digits;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "FEES")
@XmlRootElement
public class Fees implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.TABLE)
//    @GeneratedValue(strategy = GenerationType.AUTO)
    //@SequenceGenerator(name="HIBERNATE_SEQUENCE", sequenceName="HIBERNATE_SEQUENCE")
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HIBERNATE_SEQUENCE")
    @Basic(optional = false)
    private BigInteger id;
    @Version
    private Integer version;
    @MacSearchable
    @Basic(optional = false)
    @Column(name = "CODE", unique = true, length = 20, nullable = false)
    private String code;

    @JoinColumn(name = "STUDENT_ID", nullable = true)
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private Student student;

    @MacSearchable
    @Temporal(TemporalType.DATE)
    @Column(name = "TRANS_DATE", nullable = false)
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date transDate;

    @Digits(integer = 8, fraction = 2)
    @Column(name = "AMOUNT")
    private BigDecimal amount;

//    @MacPassword
//    @Column(name = "PIN", length = 30)
//    private String pin;
//    @Column(name = "WEB_ADDRESS", length = 20)
//    @URL
//    private String webAddress;
//    @Digits(integer = 8, fraction = 2)
//    @Column(name = "TAX_PAID")
//    private BigDecimal taxPaid;
//    @MacFile(min = 1, max = 1000000, extention = "doc,pdf,docx")
//    @Column(name = "DOC_FILE", length = 255)
//    private String docFile;
//    @Column(name = "IS_ACTIVE")
//    private Boolean isActive;
//    @MacSearchable
//    @Column(name = "REMARKS", length = 2000)
//    private String remarks;
//    @Transient
//    @Column(name = "CREATION_TIME")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date creationTime;
//    @Transient
//    @Column(name = "MODIFICATION_TIME")
//    private Date modificationTime;
//    @JoinColumn(name = "LOOKUP_ANY_TYPE_ID",  nullable = true)
//    @ManyToOne(fetch = FetchType.LAZY, optional = true)
//    private Lookup lookupAnyTypeId;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empId", fetch = FetchType.LAZY)
//    @OrderBy(value = "slNo DESC, code ASC")
//    private List<EmpEduDtl> empEduDtlList;
    public Fees() {
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
