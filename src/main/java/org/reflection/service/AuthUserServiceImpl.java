package org.reflection.service;

import org.reflection.model.security.AuthUser;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.AuthUserNotFoundException;
import org.reflection.repositories.AuthUserRepository;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("authUserService")
@Transactional(readOnly = true)
public class AuthUserServiceImpl implements AuthUserService {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Transactional
    @Override
    public AuthUser create(AuthUser lookup) {
        return authUserRepository.save(lookup);
    }

    @Override
    @Transactional
    public AuthUser findById(BigInteger id) {

        //Hibernate.initialize(lookup.getHrTlShftDtlId());
        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return authUserRepository.findOne(id);
    }

    @Override
    @Transactional(rollbackFor = AuthUserNotFoundException.class)
    public AuthUser delete(BigInteger id) throws AuthUserNotFoundException {

        AuthUser authUser = authUserRepository.findOne(id);

        if (authUser == null) {
            throw new AuthUserNotFoundException();
        }
        authUserRepository.delete(id);
        return authUser;
    }

    @Override
    @Transactional
    public Iterable<AuthUser> findAll() {
        return authUserRepository.findAll();
    }

    @Transactional(rollbackFor = AuthUserNotFoundException.class)
    @Override
    public AuthUser update(AuthUser updated) throws AuthUserNotFoundException {

        AuthUser authUser = authUserRepository.findOne(updated.getId());

        if (authUser == null) {
            throw new AuthUserNotFoundException();
        }

        BeanUtils.copyProperties(updated, authUser);
        return authUserRepository.save(authUser);
    }

    @Transactional(rollbackFor = AuthUserNotFoundException.class)
    @Override
    public AuthUser copy(final AuthUser copied) {

        AuthUser authUser = new AuthUser();
        BeanUtils.copyProperties(copied, authUser);
        authUser.setId(null);
        //PropertyUtils.copyProperties(lookup, updated);

        return authUserRepository.save(authUser);
    }

    @Transactional
    @Override
    public Iterable<AuthUser> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<AuthUser> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    public AuthUser findByUsername(String username) throws UsernameNotFoundException {
        return authUserRepository.findByUsername(username);
    }

    @Override
    public void deleteByUsername(String username) {
        authUserRepository.deleteByUsername(username);
    }

    @Override
    public boolean isUsernameUnique(BigInteger id, String username) {
        AuthUser user = findByUsername(username);
        return (user == null || ((id != null) && (Objects.equals(user.getId(), id))));
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AuthUser user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found");
        }

        Set<GrantedAuthority> authorities = new HashSet<>();

        for (org.reflection.model.security.AuthRole authRole : user.getAuthRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + authRole.getAuthority()));
        }

        user.setAuthorities(authorities);
        return user;
    }
}
