package com.onekg.quotegenerator.config;

import com.onekg.quotegenerator.model.User;
import com.onekg.quotegenerator.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            createTestUser();
        }
    }

    // TODO: turn off this method when project will deploy at production
    private void createTestUser() {
        User testUser = new User();

        testUser.setUsername("demo");
        testUser.setEmail("demo@example.com");
        testUser.setPassword(passwordEncoder.encode("demo123"));
        testUser.setEnabled(true);

        userRepository.save(testUser);

        String message = """
                ==========================================
                Создан тестовый пользователь
                Имя пользователя: demo
                Пароль: demo123
                Email: demo@example.com
                ==========================================
                """;

        System.out.println(message);
    }
}
