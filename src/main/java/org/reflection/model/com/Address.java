package org.reflection.model.com;

import com.oith.annotation.MacImagable;
import com.oith.annotation.MacSearchable;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Embeddable
public class Address implements Serializable {

    @MacSearchable
    @Column(name ="LINE_ONE", length = 50)
    private String lineOne;
    @MacSearchable
    @Column(name = "LINE_TWO", length = 50)
    private String lineTwo;
    @MacSearchable
    @Column(length = 30)
    private String street;
    @MacImagable
    @Column(length = 30)
    private String city;
    @MacSearchable
    @Column(length = 10)
    private String zip;

    public Address() {
    }

    public String getLineOne() {
        return lineOne;
    }

    public void setLineOne(String lineOne) {
        this.lineOne = lineOne;
    }

    public String getLineTwo() {
        return lineTwo;
    }

    public void setLineTwo(String lineTwo) {
        this.lineTwo = lineTwo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

}
