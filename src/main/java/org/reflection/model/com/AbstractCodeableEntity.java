package org.reflection.model.com;

import com.oith.annotation.MacSearchable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(catalog = "MCHTI", name = "ABSTRACT_CODEABLE_ENTITY")
@XmlRootElement
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractCodeableEntity extends AbstractEntity implements ICodeable {

    @Size(min = 2, max = 6)
    @Column(name = "CODE", unique = true, nullable = false)
    private String code;
    @MacSearchable
    @Column(name = "FULL_NAME", length = 100, nullable = false)
    @NotEmpty
    @Size(min = 2, max = 100)
    private String fullName;

    public AbstractCodeableEntity() {
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return getFullName();
    }
}
