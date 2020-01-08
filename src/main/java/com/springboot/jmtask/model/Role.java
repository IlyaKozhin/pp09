package com.springboot.jmtask.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "role")
    private String role;

    @ManyToOne
    @JoinColumn(name = "username")
    @JsonIgnore
    private User user;

    public Role() {
    }

    public Role(String role, User user) {
        this.role = role;
        this.user = user;
    }

    @Override
    public String getAuthority() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return role;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
