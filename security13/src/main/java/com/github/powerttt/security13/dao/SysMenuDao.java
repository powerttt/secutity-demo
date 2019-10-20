package com.github.powerttt.security13.dao;

import java.util.List;

/**
 * @Author tongning
 * @Date 2019/10/16 0016
 * function:<
 * <p>
 * >
 */
public interface SysMenuDao {

    List<String> selectRoleNamesByUrl(String url);


}
