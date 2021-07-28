package com.example.hddemo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private boolean active;
    private String roles = "";
    private String permission = "";
//    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "userid"))
//    @Enumerated(EnumType.STRING)
//    private Set<Role> roles;ee


    public User(String username, String password, String roles, String permission) {
        this.username = username;
        this.password = password;
        this.active = true;
        this.roles = roles;
        this.permission = permission;
    }

    public List<String> getRoleList() {
        if (this.roles.length() > 0)
            return Arrays.asList(this.roles.split(","));
        return new ArrayList<>();
    }

    public List<String> getPermissionList() {
        if (this.permission.length() > 0)
            return Arrays.asList(this.permission.split(","));
        return new ArrayList<>();
    }

}
