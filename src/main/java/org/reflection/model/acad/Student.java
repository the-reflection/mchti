package org.reflection.model.acad;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.reflection.model.com.Client;

@Entity
@Table(name = "STUDENT")
@XmlRootElement
public abstract class Student extends Client {

    @JoinColumn(name = "GARDIAN_ID", nullable = true)
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private Gardian gardian;

    public Student() {
    }

    public Gardian getGardian() {
        return gardian;
    }

    public void setGardian(Gardian gardian) {
        this.gardian = gardian;
    }

}
