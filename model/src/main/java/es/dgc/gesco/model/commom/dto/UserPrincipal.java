package es.dgc.gesco.model.commom.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class UserPrincipal implements UserDetails {
    private static final long serialVersionUID = 1L;
    private String petitionId;
    private String relayId;
    private Long userId;
    private String docNum;
    private String firstName;
    private String surnames;
    private Set<String> roles = new HashSet<>();
    private Set<String> scopes = new HashSet<>();
    private List<GrantedAuthority> authorities = new ArrayList<>();


    @Autowired
    public UserPrincipal(String petitionId, String relayId, Long userId, String docNum, String firstName,
                         String surnames, Set<String> roles, Set<String> scopes) {
        this.petitionId = petitionId;
        this.relayId = relayId;
        this.userId = userId;
        this.docNum = docNum;
        this.firstName = firstName;
        this.surnames = surnames;
        this.roles = roles;
        this.scopes = scopes;
        this.authorities = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.docNum;
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

}

