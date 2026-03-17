package com.pacioli.cabinet.infrastructure.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import java.util.Collection;
import java.util.List;

public class JwtAuthorityConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        // On récupère le champ "role" que nous avons ajouté dans Better Auth
        String role = jwt.getClaimAsString("role");
        if (role == null) return List.of();

        // On retourne le rôle avec le préfixe ROLE_ pour Spring Security
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
    }
}