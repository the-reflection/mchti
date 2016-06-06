package org.reflection.model.acad.om;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Job")
@XmlRootElement
public class Job extends ObjectType {
    
}