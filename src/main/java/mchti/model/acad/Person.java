package mchti.model.acad;

import mchti.model.hcm.enums.Gender;
import com.oith.annotation.MacImagable;
import com.oith.annotation.MacSearchable;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.math.BigInteger; import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "Person")
@XmlRootElement

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person implements Serializable {

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
    @MacImagable
    @Column(name = "PIC_FILE", length = 255)
    private String picFile;
    @MacSearchable
    @Basic(optional = false)
    @Column(name = "full_Name", length = 100, nullable = false)
    @NotEmpty
    @Size(min = 2, max = 100)
    private String fullName;
    @MacSearchable
    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER", length = 20)
    private Gender gender;
    @Temporal(TemporalType.DATE)
    @Past
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date doj;
    @MacSearchable
    @Temporal(TemporalType.DATE)
    @Past
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dob;
    @Column(name = "EMAIL", length = 30)
    @Email
    private String email;
    @MacSearchable
    @Column(name = "ADDRESS", length = 1000)
    private String address;

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
//    @JoinColumn(name = "LOOKUP_ANY_TYPE_ID", referencedColumnName = "ID", nullable = true)
//    @ManyToOne(fetch = FetchType.LAZY, optional = true)
//    private Lookup lookupAnyTypeId;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empId", fetch = FetchType.LAZY)
//    @OrderBy(value = "slNo DESC, code ASC")
//    private List<EmpEduDtl> empEduDtlList;
    public Person() {
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

    public String getPicFile() {
        return picFile;
    }

    public void setPicFile(String picFile) {
        this.picFile = picFile;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDoj() {
        return doj;
    }

    public void setDoj(Date doj) {
        this.doj = doj;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        //return ToStringBuilder.reflectionToString(this);
        return code + "-" + fullName;
    }
}
