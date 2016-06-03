package mchti.model.sample;

import com.oith.annotation.MacCodable;
import com.oith.annotation.MacFile;
import com.oith.annotation.MacImagable;
import com.oith.annotation.MacPassword;
import com.oith.annotation.MacSearchable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

@Entity
@Table(name = "ZX_EMP", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"FULL_NAME"})})
@XmlRootElement
@MacCodable(id = "id", code = "code", caption = "fullName")
public class ZxEmp implements Serializable {

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
    @MacSearchable
    @Basic(optional = false)
    @Column(name = "CODE", length = 20, nullable = false)
    private String code;
    @MacImagable
    @Column(name = "PIC_FILE", length = 255)
    private String picFile;
    @MacSearchable
    @Basic(optional = false)
    @Column(name = "FULL_NAME", length = 100, nullable = false)
    @NotEmpty
    @Size(min = 2, max = 100)
    private String fullName;
    @MacSearchable
    @Enumerated(EnumType.STRING)
    @Column(name = "ZX_GENDER", length = 20)
    private ZxGender zxGender;
    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    @Future
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date startDate;
    @MacSearchable
    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    @Past
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dob;
    @Column(precision = 10, scale = 2)
    @Range(min = 1, max = 100000)
    private BigInteger sal;
    @Digits(integer = 8, fraction = 2)
    @Column(name = "TAX_PAID")
    private BigDecimal taxPaid;
    @Column(name = "EMAIL", length = 30)
    @Email
    private String email;
    @MacPassword
    @Column(name = "PIN", length = 30)
    private String pin;
    @Column(name = "WEB_ADDRESS", length = 20)
    @URL
    private String webAddress;
    @MacFile(min = 1, max = 1000000, extention = "doc,pdf,docx")
    @Column(name = "DOC_FILE", length = 255)
    private String docFile;
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
    @MacSearchable
    @Column(name = "REMARKS", length = 2000)
    private String remarks;
    @Transient
    @Column(name = "CREATION_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;
    @Transient
    @Column(name = "MODIFICATION_TIME")
    private Date modificationTime;
    @JoinColumn(name = "ZX_LOOKUP_BLOOD_GROUP_ID", referencedColumnName = "ID", nullable = true)
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private ZxLookup zxLookupBloodGroup;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zxEmp", fetch = FetchType.LAZY)
    @OrderBy(value = "slNo DESC, code ASC")
    private List<ZxEmpEduDtl> zxEmpEduDtls;

    public ZxEmp() {
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public BigInteger getSal() {
        return sal;
    }

    public void setSal(BigInteger sal) {
        this.sal = sal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getWebAddress() {
        return webAddress;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    public String getDocFile() {
        return docFile;
    }

    public void setDocFile(String docFile) {
        this.docFile = docFile;
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

    public BigDecimal getTaxPaid() {
        return taxPaid;
    }

    public void setTaxPaid(BigDecimal taxPaid) {
        this.taxPaid = taxPaid;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(Date modificationTime) {
        this.modificationTime = modificationTime;
    }

    public ZxGender getZxGender() {
        return zxGender;
    }

    public void setZxGender(ZxGender zxGender) {
        this.zxGender = zxGender;
    }

    public ZxLookup getZxLookupBloodGroup() {
        return zxLookupBloodGroup;
    }

    public void setZxLookupBloodGroup(ZxLookup zxLookupBloodGroup) {
        this.zxLookupBloodGroup = zxLookupBloodGroup;
    }

    public List<ZxEmpEduDtl> getZxEmpEduDtls() {
        return zxEmpEduDtls;
    }

    public void setZxEmpEduDtls(List<ZxEmpEduDtl> zxEmpEduDtls) {
        this.zxEmpEduDtls = zxEmpEduDtls;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        //return ToStringBuilder.reflectionToString(this);
        return code + "-" + fullName;
    }

}
