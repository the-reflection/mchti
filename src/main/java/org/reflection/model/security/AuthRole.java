package org.reflection.model.security;

import com.oith.annotation.MacCareless;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.reflection.model.com.AbstractEntity;

@Entity
@Table(name = "AUTH_ROLE")
@XmlRootElement
public class AuthRole extends AbstractEntity {

    @Column(name = "authority", length = 128, unique = true)
    private String authority;
    @MacCareless
    @ManyToMany(mappedBy = "authRoles")
    private Set<AuthUser> authUsers = new HashSet();

    public AuthRole() {
    }

    public AuthRole(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Set<AuthUser> getAuthUsers() {
        return authUsers;
    }

    public void setAuthUsers(Set<AuthUser> authUsers) {
        this.authUsers = authUsers;
    }

    @Override
    public String toString() {
        return authority;
    }

}
