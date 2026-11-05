package com.scjp.aot;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRepositoryTests {

    @Autowired
    UserRepository repository;

    @Test
    void derivedQueryWorks() {
        List<User> result = repository.findByLastnameAndAgeGreaterThan("Turing", 30);
        assertThat(result).extracting(User::getFirstname).containsExactly("Alan");
    }

    @Test
    void annotatedQueryWorks() {
        assertThat(repository.findByEmail("grace@example.com")).isPresent();
        assertThat(repository.existsByEmail("nobody@example.com")).isFalse();
    }

    @Test
    void orderedPrefixQueryWorks() {
        assertThat(repository.findByLastnameStartingWithOrderByAgeDesc("T"))
                .extracting(User::getLastname)
                .containsExactly("Torvalds", "Turing");
    }
}
