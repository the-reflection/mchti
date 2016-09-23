package org.reflection.model.com;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.reflection.model.security.AuthUser;

@Entity
@Table(catalog = "MCHTI", name = "ABSTRACT_AUDITABLE_ENTITY")
@XmlRootElement
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractAuditableEntity extends AbstractEntity implements IAuditable {

    @JoinColumn(name = "ENTRY_BY_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuthUser entryBy;

    @Column(name = "ENTRY_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date entryDate;

    @JoinColumn(name = "EDIT_BY_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuthUser editBy;

    @Column(name = "EDIT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date editDate;

    @Override
    public AuthUser getEntryBy() {
        return entryBy;
    }

    @Override
    public void setEntryBy(AuthUser entryBy) {
        this.entryBy = entryBy;
    }

    @Override
    public Date getEntryDate() {
        return entryDate;
    }

    @Override
    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    @Override
    public AuthUser getEditBy() {
        return editBy;
    }

    @Override
    public void setEditBy(AuthUser editBy) {
        this.editBy = editBy;
    }

    @Override
    public Date getEditDate() {
        return editDate;
    }

    @Override
    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }
}
