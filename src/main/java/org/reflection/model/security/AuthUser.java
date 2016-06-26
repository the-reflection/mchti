package org.reflection.model.security;

import com.oith.annotation.MacImagable;
import java.util.Arrays;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.math.BigInteger;
import java.util.Date;
import java.util.LinkedHashSet;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Past;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Entity
@Table(name = "AUTH_USER")
public class AuthUser extends User {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.TABLE)
    private BigInteger id;
    @NotEmpty
    @Column(name = "USERNAME", length = 30, unique = true, nullable = false)
    private String username;
    @NotEmpty
    @Column(name = "PASSWORD", length = 128, nullable = false)
    private String password;
    @NotEmpty
    @Column(name = "DISPLAY_NAME", length = 9, nullable = false)
    private String displayName;
    @NotEmpty
    @Column(name = "FULL_NAME", length = 50, nullable = false)
    private String fullName;
    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER", length = 6)
    private AuthGender gender;
    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    @Past
    private Date dob;
    @Column(name = "DOJ")
    @Temporal(TemporalType.DATE)
    @Past
    private Date doj;
    @Column(name = "EMAIL", length = 30)
    private String email;
    @MacImagable
    @Column(name = "PIC_FILE", length = 255)
    private String picFile;

    @NotEmpty
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "AUTH_USER_AUTH_ROLE",
            joinColumns = {
                @JoinColumn(name = "AUTH_USER_ID")},
            inverseJoinColumns = {
                @JoinColumn(name = "AUTH_ROLE_ID")})
    private Set<AuthRole> authRoles = new LinkedHashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "id.authUser")
    private Set<AuthUserAuthQuestion> authUserAuthQuestions = new LinkedHashSet<>();

    @Column(name = "ENABLED")
    private Boolean enabled=Boolean.TRUE;
    @Column(name = "ACCOUNT_NON_EXPIRED")
    private Boolean accountNonExpired=Boolean.FALSE;
    @Column(name = "ACCOUNT_NON_LOCKED")
    private Boolean accountNonLocked=Boolean.FALSE;
    @Column(name = "CREDENTIALS_NON_EXPIRED")
    private Boolean credentialsNonExpired=Boolean.FALSE;

    @Transient
    private Set<GrantedAuthority> authorities;

    @Transient
    private String completeMenu;

    public AuthUser() {
        super("NA", "NA", Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
    }

    public AuthUser(String username, String password, String role) {
        super(username, password, Arrays.asList(new SimpleGrantedAuthority(role)));
    }

    public AuthUser(String username, String password, Boolean enabled, Boolean accountNonExpired, Boolean credentialsNonExpired, Boolean accountNonLocked, Set< GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.authorities = authorities;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<AuthRole> getAuthRoles() {
        return authRoles;
    }

    public void setAuthRoles(Set<AuthRole> authRoles) {
        this.authRoles = authRoles;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public String getPicFile() {
        return picFile;
    }

    public void setPicFile(String picFile) {
        this.picFile = picFile;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AuthGender getGender() {
        return gender;
    }

    public void setGender(AuthGender gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getDoj() {
        return doj;
    }

    public void setDoj(Date doj) {
        this.doj = doj;
    }

    @Override
    public Set<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Transient
    public void setAuthorities(Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public Set<AuthUserAuthQuestion> getAuthUserAuthQuestions() {
        return authUserAuthQuestions;
    }

    public void setAuthUserAuthQuestions(Set<AuthUserAuthQuestion> authUserAuthQuestions) {
        this.authUserAuthQuestions = authUserAuthQuestions;
    }

    public String getCompleteMenu() {
        return completeMenu;
    }

    public void setCompleteMenu(String completeMenu) {
        this.completeMenu = completeMenu;
    }

    @Override
    public String toString() {
        return username;
    }
}
