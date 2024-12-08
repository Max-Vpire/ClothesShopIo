package com.vpire.ClothesShopIo.user;

import com.vpire.ClothesShopIo.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public User createUser(RegisterRequest user) {
        User newUser = User.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .active(Active.ACTIVE)
                .gender(user.getGender())
                .registeredAt(LocalDate.now())
                .role(Role.USER)
                .build();

        return userRepository.save(newUser);
    }

    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    public ResponseEntity<Optional<User>> getUserById(String id) {
        return ResponseEntity.ok(userRepository.findById(id));
    }

    public ResponseEntity<Optional<User>> getUserByEmail(String email) {
        return ResponseEntity.ok(userRepository.findByEmail(email));
    }

    public ResponseEntity<List<User>> searchUserByFirstnameAndLastname(String value) {
        return ResponseEntity.ok(userRepository.searchByFirstnameAndLastname(value));
    }

    public ResponseEntity<String> updateUser(String id, UpdateUserRequest newUser) {
        User checkUser = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));

        checkUser.setFirstname(newUser.getFirstname());
        checkUser.setLastname(newUser.getLastname());

        return ResponseEntity.ok("User edited");
    }

    public ResponseEntity<String> editRole(String id, Role newRole) {
        User checkUser = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
        checkUser.setRole(newRole);

        userRepository.save(checkUser);
        return ResponseEntity.ok("User's role edited");
    }

    public ResponseEntity<String> editActivation(String id, Active newActive) {
        User checkUser = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
        checkUser.setActive(newActive);
        userRepository.save(checkUser);
        return ResponseEntity.ok("User activation successfully edited!");
    }

    public ResponseEntity<String> deleteUser(String id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok("User with id: " + id + " deleted.");
    }
}
