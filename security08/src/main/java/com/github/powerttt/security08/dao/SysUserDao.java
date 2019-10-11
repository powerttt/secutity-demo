package com.github.powerttt.security08.dao;

import com.github.powerttt.security08.model.SysUser;
import org.springframework.web.bind.annotation.Mapping;

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
