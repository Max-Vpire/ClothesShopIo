package com.vpire.ClothesShopIo.user;

import com.vpire.ClothesShopIo.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody RegisterRequest user) {
        return userService.createUser(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Optional<User>> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/search/{value}")
    public ResponseEntity<List<User>> getUsersByName(@PathVariable String value) {
        return userService.searchUserByFirstnameAndLastname(value);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody UpdateUserRequest user) {
        return userService.updateUser(id, user);
    }

    @PutMapping("/role/{role}/{id}")
    public ResponseEntity<String> editRole(
            @PathVariable Role role,
            @PathVariable String id
    ) {
        return userService.editRole(id, role);
    }

    @PutMapping("/active/{active}/{id}")
    public ResponseEntity<String> editActivation(@PathVariable String id, @PathVariable Active active) {
        return userService.editActivation(id, active);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }

}
