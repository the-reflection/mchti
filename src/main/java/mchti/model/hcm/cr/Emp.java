package mchti.model.hcm.cr;

import mchti.model.hcm.enums.Gender;
import com.oith.annotation.MacCodable;
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
import java.math.BigInteger;
import javax.persistence.Id;
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
@Table(name = "EMP")
@XmlRootElement
@MacCodable(id = "id", code = "code", caption = "fullName")
public class Emp implements Serializable {

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
    @Column(name = "CODE", unique = true, length = 20, nullable = false)
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
    @Column(name = "GENDER", length = 6)
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

    public Emp() {
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
