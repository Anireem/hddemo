package com.example.hddemo.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getAllRolesLeftJoinUserRoles() {
        User user = new User();
        HashSet<Role> roles = new HashSet<>();
        roles.add(Role.ADMIN);
        user.setRoles(roles);

        Map<Role, Boolean> userRolesMap = user.getAllRolesLeftJoinUserRoles();
        assertEquals(true, userRolesMap.get(Role.ADMIN));
        assertEquals(true, userRolesMap.containsKey(Role.USER));
        assertEquals(false, userRolesMap.get(Role.USER));
    }

    @Test
    void hasRole() {
        User user = new User();
        HashSet<Role> roles = new HashSet<>();
        roles.add(Role.ADMIN);
        user.setRoles(roles);

        assertEquals(true, user.hasRole(Role.ADMIN));
        assertEquals(false, user.hasRole(Role.USER));
    }
}