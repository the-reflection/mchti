package mchti.model.acad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "Teacher")
@XmlRootElement
public class Teacher extends Person {

    @Column(precision = 10, scale = 2)
    @Range(min = 1, max = 100000)
    private Double salary;

    public Teacher() {
    }

}
