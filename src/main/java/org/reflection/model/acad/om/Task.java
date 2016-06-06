package org.reflection.model.acad.om;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Task")
@XmlRootElement
public class Task extends ObjectType {
    
}