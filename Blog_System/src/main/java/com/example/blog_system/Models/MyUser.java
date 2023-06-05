package com.example.blog_system.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@AllArgsConstructor
@Entity
@Getter
@Setter
@NoArgsConstructor
public class MyUser implements UserDetails {
    @Id
    @GeneratedValue(generator = "id_user_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id", sequenceName = "id_user_seq", initialValue = 1, allocationSize = 1)
    private Integer id;

    @NotEmpty(message = "userName must be not empty")
    @Column(columnDefinition = "varchar(100) unique")
    private String username;

    @NotEmpty(message = "password must be not empty")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).{8,}$", message = "password must have characters and digits")
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "myUser")
    private Set<Blog> blogs;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
