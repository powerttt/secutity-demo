package com.github.powerttt.security08.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * @Author tongning
 * @Date 2019/10/11 0011
 * function:<
 * <p>
 * >
 */
public class SysRole implements GrantedAuthority {

    private Long id;

    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
