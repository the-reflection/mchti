package org.reflection.model.acad;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "STUDENT_UNDER_GRADE")
@XmlRootElement
public class StudentUnderGrade extends Student {

    public StudentUnderGrade() {
    }

}
