// RoleModelService.java
package com.MelDaColmea.service;

import com.MelDaColmea.model.RoleModel;
import com.MelDaColmea.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    /**
     * Encuentra todos los roles.
     */
    public List<RoleModel> findAllRoles() {
        return roleRepository.findAll();
    }

    /**
     * Encuentra un rol por su ID.
     */
    public RoleModel findRoleById(Long id) {
        Optional<RoleModel> optionalRole = roleRepository.findById(id);
        return optionalRole.orElse(null); // O lanzar una excepción personalizada
    }

    /**
     * Guarda un nuevo rol en la base de datos.
     *
     * @param role El rol a guardar
     * @return El rol guardado
     */
    @Transactional
    public RoleModel saveRole(RoleModel role) {
        // Validaciones adicionales pueden ser necesarias
        validateRole(role);
        return roleRepository.save(role);
    }

    /**
     * Elimina un rol por su ID.
     *
     * @param id El ID del rol a eliminar
     */
    @Transactional
    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id);
    }

    /**
     * Actualiza un rol existente.
     *
     * @param id El ID del rol a actualizar
     * @param role Los nuevos datos del rol
     * @return El rol actualizado
     */
    @Transactional
    public RoleModel updateRole(Long id, RoleModel role) {
        RoleModel existingRole = findRoleById(id);
        if (existingRole != null) {
            existingRole.setRoleName(role.getRoleName());
            return roleRepository.save(existingRole);
        } else {
            return null; // O lanzar una excepción personalizada
        }
    }

    /**
     * Valida un rol.
     *
     * @param role El rol a validar
     */
    private void validateRole(RoleModel role) {
        if (role.getRoleName() == null || role.getRoleName().isEmpty()) {
            throw new IllegalArgumentException("El nombre del rol no puede estar vacío");
        }
        // Agregar más validaciones si es necesario
    }
}