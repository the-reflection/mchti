package mchti.model.acad;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Student_Grade")
@XmlRootElement
public class StudentGrade extends Student {

    public StudentGrade() {
    }

}
