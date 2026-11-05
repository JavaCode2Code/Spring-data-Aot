package com.scjp.aot;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
class UserController {

    private final UserRepository repository;

    UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    List<User> all() {
        return repository.findAll();
    }

    @GetMapping("/search")
    List<User> search(@RequestParam String lastname, @RequestParam(defaultValue = "0") int minAge) {
        return repository.findByLastnameAndAgeGreaterThan(lastname, minAge);
    }

    @GetMapping("/by-email/{email}")
    ResponseEntity<User> byEmail(@PathVariable String email) {
        return repository.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
