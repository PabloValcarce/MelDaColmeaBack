// UserModelService.java
package com.MelDaColmea.service;

import com.MelDaColmea.model.UserModel;
import com.MelDaColmea.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userModelRepository;

    /**
     * Encuentra todos los usuarios.
     */
    public List<UserModel> findAllUsers() {
        return userModelRepository.findAll();
    }

    /**
     * Encuentra un usuario por su ID.
     */
    public UserModel findUserById(Long id) {
        Optional<UserModel> optionalUser = userModelRepository.findById(id);
        return optionalUser.orElse(null); // O lanzar una excepción personalizada
    }

    /**
     * Guarda un nuevo usuario en la base de datos.
     *
     * @param user El usuario a guardar
     * @return El usuario guardado
     */
    @Transactional
    public UserModel saveUser(UserModel user) {
        // Validaciones adicionales pueden ser necesarias
        validateUser(user);
        return userModelRepository.save(user);
    }

    /**
     * Elimina un usuario por su ID.
     *
     * @param id El ID del usuario a eliminar
     */
    @Transactional
    public void deleteUserById(Long id) {
        userModelRepository.deleteById(id);
    }

    /**
     * Actualiza un usuario existente.
     *
     * @param id El ID del usuario a actualizar
     * @param user Los nuevos datos del usuario
     * @return El usuario actualizado
     */
    @Transactional
    public UserModel updateUser(Long id, UserModel user) {
        UserModel existingUser = findUserById(id);
        if (existingUser != null) {
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            existingUser.setEmail(user.getEmail());
            return userModelRepository.save(existingUser);
        } else {
            return null; // O lanzar una excepción personalizada
        }
    }

    /**
     * Valida un usuario.
     *
     * @param user El usuario a validar
     */
    private void validateUser(UserModel user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("El correo electrónico no puede estar vacío");
        }
        // Agregar más validaciones si es necesario
    }
}