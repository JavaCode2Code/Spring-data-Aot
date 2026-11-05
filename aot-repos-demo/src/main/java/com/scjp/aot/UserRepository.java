package com.scjp.aot;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    // Derived query: resolved at build time with AOT repositories
    List<User> findByLastnameAndAgeGreaterThan(String lastname, int age);

    List<User> findByLastnameStartingWithOrderByAgeDesc(String prefix);

    boolean existsByEmail(String email);

    // Annotated query: also processed at build time
    @Query("select u from User u where u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);
}
