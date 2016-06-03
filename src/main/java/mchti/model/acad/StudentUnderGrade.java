package mchti.model.acad;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Student_Under_Grade")
@XmlRootElement
public class StudentUnderGrade extends Student {

    public StudentUnderGrade() {
    }

}
