package com.github.powerttt.security14.config;

import com.github.powerttt.security14.dao.SysUserDao;
import com.github.powerttt.security14.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @Author tongning
 * @Date 2019/10/19 0019
 * function:<
 * <p>
 * >
 */
@Component
public class UserLoginService implements UserDetailsService {

    @Autowired
    private SysUserDao sysUserDao;

    /**
     * 根据用户名找到用户。在实际的实现中，搜索可能区分大小写，或者不区分大小写，
     * 具体取决于实现实例的配置方式。在这种情况下，返回的<code> UserDetails
     * </ code> 对象的用户名可能与实际请求的不同。
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)){
            throw new UsernameNotFoundException("用户名为空");
        }

        SysUser sysUser = sysUserDao.getByUserName(username);
        if (sysUser==null){
            throw new UsernameNotFoundException("用户名为空");
        }
        return sysUser;
    }
}
