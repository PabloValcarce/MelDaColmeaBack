// RoleController.java
package com.MelDaColmea.controller;

import com.MelDaColmea.model.RoleModel;
import com.MelDaColmea.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<RoleModel> getAllRoles() {
        return roleService.findAllRoles();

    }

    @GetMapping("/{id}")
    public RoleModel getRoleById(@PathVariable Long id) {
        return roleService.findRoleById(id);
    }

    @PostMapping
    public RoleModel createRole(@RequestBody RoleModel role) {
        return roleService.saveRole(role);
    }

    @PutMapping("/{id}")
    public RoleModel updateRole(@PathVariable Long id, @RequestBody RoleModel role) {
        RoleModel existingRole = roleService.findRoleById(id);
        if (existingRole != null) {
            existingRole.setRoleName(role.getRoleName());
            return roleService.saveRole(existingRole);
        } else {
            return null; // Manejar el caso de un Role no encontrado
        }
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable Long id) {
        roleService.deleteRoleById(id);
    }
}