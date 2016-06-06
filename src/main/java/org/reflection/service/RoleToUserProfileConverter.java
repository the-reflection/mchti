package org.reflection.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.reflection.model.security.AuthRole;

/**
 * A converter class used in views to map id's to actual userProfile objects.
 */
@Component
public class RoleToUserProfileConverter implements Converter<Object, AuthRole> {

    @Autowired
    private AuthRoleService userProfileService;

    /**
     * Gets AuthRole by Id
     *
     * @see
     * org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    public AuthRole convert(Object element) {
        Long id = Long.parseLong((String) element);
        AuthRole profile = userProfileService.findById(id);
        return profile;
    }

}
