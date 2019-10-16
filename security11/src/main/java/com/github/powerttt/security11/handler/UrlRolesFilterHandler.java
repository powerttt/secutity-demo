package com.github.powerttt.security11.handler;

import com.github.powerttt.security11.dao.SysMenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * @Author tongning
 * @Date 2019/10/16 0016
 * function:<
 * <p>
 * 动态url处理器
 * >
 */
@Component
public class UrlRolesFilterHandler implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private SysMenuDao sysMenuDao;

    /**
     * 每次请求进来都会去查询此URL的角色，又增加一层查询
     * @param o
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        List<String> roleNames = sysMenuDao.selectRoleNamesByUrl(requestUrl);

        String[] names = new String[roleNames.size()];
        for (int i = 0; i < names.length; i++) {
            names[i] = roleNames.get(i);
        }
        return SecurityConfig.createList(names);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
