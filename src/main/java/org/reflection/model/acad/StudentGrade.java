package org.reflection.model.acad;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "STUDENT_GRADE")
@XmlRootElement
public class StudentGrade extends Student {

    public StudentGrade() {
    }

}
