package org.reflection.model.acad;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.reflection.model.com.Employee;

@Entity
@Table(name = "TEACHER")
@XmlRootElement
public class Teacher extends Employee {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy(value = "code ASC")
    private Set<Subject> subject;

    public Teacher() {
    }

    public Set<Subject> getSubject() {
        return subject;
    }

    public void setSubject(Set<Subject> subject) {
        this.subject = subject;
    }

}
