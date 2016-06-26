package org.reflection.service;

import org.reflection.model.security.AuthMenu;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.AuthMenuNotFoundException;
import org.reflection.repositories.AuthMenuRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("authMenuService")
@Transactional(readOnly = true)
public class AuthMenuServiceImpl implements AuthMenuService {

    @Autowired
    private AuthMenuRepository authMenuRepository;



    @Transactional
    @Override
    public AuthMenu create(AuthMenu lookup) {
        return authMenuRepository.save(lookup);
    }

    @Override
    @Transactional
    public AuthMenu findById(BigInteger id) {
        AuthMenu authMenu=authMenuRepository.findOne(id);
            Hibernate.initialize(authMenu.getChilds());

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return authMenu;
    }

    @Override
    @Transactional(rollbackFor = AuthMenuNotFoundException.class)
    public AuthMenu delete(BigInteger id) throws AuthMenuNotFoundException {

        AuthMenu authMenu = authMenuRepository.findOne(id);

        if (authMenu == null) {
            throw new AuthMenuNotFoundException();
        }
        authMenuRepository.delete(id);
        return authMenu;
    }

    @Override
    @Transactional
    public Iterable<AuthMenu> findAll() {
        Iterable<AuthMenu> authMenus=authMenuRepository.findAll();
        
        for (AuthMenu authMenu : authMenus) {
            Hibernate.initialize(authMenu.getChilds());

        //Hibernate.initialize(authMenu.getA());
        //Hibernate.initialize(authMenu.getZs());
        }
        
        return authMenus;
    }

    @Transactional(rollbackFor = AuthMenuNotFoundException.class)
    @Override
    public AuthMenu update(AuthMenu updated) throws AuthMenuNotFoundException {

        AuthMenu authMenu = authMenuRepository.findOne(updated.getId());

        if (authMenu == null) {
            throw new AuthMenuNotFoundException();
        }

        BeanUtils.copyProperties(updated, authMenu);
        return authMenuRepository.save(authMenu);
    }

    @Transactional(rollbackFor = AuthMenuNotFoundException.class)
    @Override
    public AuthMenu copy(final AuthMenu copied) {

        AuthMenu authMenu = new AuthMenu();
        BeanUtils.copyProperties(copied, authMenu);
        authMenu.setId(null);

        return authMenuRepository.save(authMenu);
    }

    @Transactional
    @Override
    public Iterable<AuthMenu> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<AuthMenu> search(_SearchDTO pageable) {
        return findAll();
    }
}