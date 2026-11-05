package com.scjp.aot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AotReposDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AotReposDemoApplication.class, args);
    }

    @Bean
    CommandLineRunner seed(UserRepository repository) {
        return args -> {
            repository.save(new User("Ada", "Lovelace", "ada@example.com", 36));
            repository.save(new User("Alan", "Turing", "alan@example.com", 41));
            repository.save(new User("Grace", "Hopper", "grace@example.com", 85));
            repository.save(new User("Linus", "Torvalds", "linus@example.com", 56));
        };
    }
}
