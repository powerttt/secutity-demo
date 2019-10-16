package com.github.powerttt.security10.dao;

import com.github.powerttt.security10.model.SysUser;

/**
 * @Author tongning
 * @Date 2019/10/11 0011
 * function:<
 * <p>
 * >
 */
public interface SysUserDao {


    SysUser getByUserName(String userName);


}
