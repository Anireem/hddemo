package com.example.hddemo.models;

        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;

        import javax.persistence.*;
        import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "userid"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public HashMap<Role, Boolean> getAllRolesLeftJoinUserRoles() {
        HashMap<Role, Boolean> rolesMap = new HashMap<>();
        for (Role value : Role.values())
            rolesMap.put(value, roles.contains(value));
        return rolesMap;
    }

    public boolean hasRole(Role role) {
        return roles.contains(role);
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public void removeRole(Role role) {
        roles.remove(role);
    }

}

