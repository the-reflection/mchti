package org.reflection.model.com;

import com.oith.annotation.MacImagable;
import com.oith.annotation.MacSearchable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Embedded;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(catalog = "MCHTI", name = "PERSON")
@XmlRootElement
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person extends AbstractCodeableEntity {

    @MacImagable
    @Column(name = "PIC_FILE", length = 255)
    private String picFile;
    @MacSearchable
    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER", length = 6)
    private Gender gender;
    @MacSearchable
    @Temporal(TemporalType.DATE)
    @Past
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dob;
    @Column(name = "EMAIL", length = 30)
    @Email
    private String email;
    @Embedded
    private Address address;

    public Person() {
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        //return ToStringBuilder.reflectionToString(this);
        return getCode() + "-" + getFullName();
    }
}
