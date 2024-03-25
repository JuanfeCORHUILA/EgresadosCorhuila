package com.corhuila.egresadoscorhuila.service.impl;

import com.corhuila.egresadoscorhuila.entity.CreateUsers;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;


@AllArgsConstructor
public class UserPrincipal implements UserDetails {

    private Long noIdentificacion;
    private String emailInstitucional;
    private String password;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public static UserPrincipal build(CreateUsers users){
        Collection<GrantedAuthority> authorities =
                users.getRol().stream().map(rol -> new SimpleGrantedAuthority(rol.name())).collect(Collectors.toList());

        return new UserPrincipal(users.getNoIdentificacion(), users.getEmailInstitucional(), users.getPassword(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return noIdentificacion.toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getEmailInstitucional() {
        return emailInstitucional;
    }
}
