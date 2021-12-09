package com.crud.test.model.user;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements UserDetails {
    @Column(name = "USER_IDX")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "USER_ID")
    private String id;

    @Column(name = "USER_PASSWORD")
    private String pw;

    @Column(name = "USER_NAME")
    private String name;

    @Column(name = "USER_NUMBER")
    private String userNumber;

    @Column(name = "RENTAL_ABLE_USER", columnDefinition = "boolean default true")
    private boolean rentalAbleUser;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    @Column(name="ROLE")
    private List<Role> roles = new ArrayList<>();

    public void setRentalAbleUser(Boolean ableUser){
        this.rentalAbleUser = ableUser;
    }

    public void update(String id, String pw){
        this.id = id;
        this.pw = pw;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> rolesConvertString = this.roles.stream().map(Enum::name).collect(Collectors.toList());
        return rolesConvertString.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }


    @Override
    public String getPassword() {
        return this.pw;
    }

    @Override
    public String getUsername() {
        return this.id;
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
