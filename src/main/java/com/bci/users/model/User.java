package com.bci.users.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder
@Entity
@Table(name = "USERS")
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @Column(name = "ID", nullable = false)
    private UUID id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "CREATED_DATE", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "UPDATED_DATE", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @Column(name = "LAST_LOGIN", nullable = false)
    @CreationTimestamp
    private LocalDateTime lastLogin;

    @Column(name = "INACTIVE")
    private Boolean inactive;

    @Column(name = "TOKEN", nullable = false)
    private String token;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Phone> phoneList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
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
