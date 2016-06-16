package org.reflection.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.reflection.model.security.AuthRole;
import java.math.BigInteger;

/**
 * A converter class used in views to map id's to actual userProfile objects.
 */
@Component
public class RoleToUserProfileConverter implements Converter<Object, AuthRole> {

    @Autowired
    private AuthRoleService authRoleService;

    @Override
    public AuthRole convert(Object element) {
        BigInteger id = new BigInteger((String) element);
        AuthRole profile = authRoleService.findById(id);
        return profile;
    }

}
