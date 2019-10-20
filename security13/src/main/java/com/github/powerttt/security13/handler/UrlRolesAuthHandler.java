package com.github.powerttt.security13.handler;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @Author tongning
 * @Date 2019/10/16 0016
 * function:<
 * 指示类负责对授权决策进行投票
 * >
 */
public class UrlRolesAuthHandler implements AccessDecisionVoter<Object> {
    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return configAttribute == null ? false : true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    /**
     * ACCESS_GRANTED – 同意
     * ACCESS_DENIED – 拒绝
     * ACCESS_ABSTAIN – 弃权
     */
    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
        if (authentication == null) {
            return ACCESS_DENIED;
        }
        int result = ACCESS_ABSTAIN;
        Collection<? extends GrantedAuthority> userRoles = authentication.getAuthorities();

        for (ConfigAttribute attribute : attributes) {
            if (supports(attribute)) {

                for (GrantedAuthority userRole : userRoles) {
                    if (attribute.getAttribute().equals(userRole.getAuthority())) {
                        return ACCESS_GRANTED;
                    }
                }
            }
        }


        return result;
    }
}
