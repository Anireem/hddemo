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

//    public Set getRoles() {
//        if (this.roles.length() > 0)
//            return new HashSet<String>(Arrays.asList(this.roles.split(",")));
//        return new HashSet();
//    }
//
//    public void setRoles(String roles) {
//        this.roles = roles;
//
//    }


//    public User(String username, String password, String roles, String permission) {
//        this.username = username;
//        this.password = password;
//        this.active = true;
//        this.roles = roles;
//        this.permission = permission;
//    }
//
//    public List<String> getRoleList() {
//        if (this.roles.length() > 0)
//            return Arrays.asList(this.roles.split(","));
//        return new ArrayList<>();
//    }

//    public List<String> getPermissionList() {
//        if (this.permission.length() > 0)
//            return Arrays.asList(this.permission.split(","));
//        return new ArrayList<>();
//    }

}

