package uz.test.quiz.component;
//lord

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.test.quiz.entity.Role;
import uz.test.quiz.entity.User;
import uz.test.quiz.entity.enums.RoleName;
import uz.test.quiz.repository.RoleRepository;
import uz.test.quiz.repository.UserRepository;


import java.util.Collections;

@Component
@NoArgsConstructor
public class DataLoader implements CommandLineRunner {
    //test
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;



    //    @Value("${spring.datasource.initialization-mode}")
//    private String mode;

    @Override
    public void run(String... args){
//        if (mode.equals("always")) {
        try {
            Role SUPER_ADMIN = roleRepository.save(new Role(RoleName.SUPER_ADMIN));
            Role ADMIN = roleRepository.save(new Role(RoleName.ADMIN));
            Role USER = roleRepository.save(new Role(RoleName.USER));
            userRepository.save(
                    User
                            .builder()
                            .active(true)
                            .email("super")
                            .password(passwordEncoder.encode("super"))
                            .fio("super")
                            .phone("+998993793877")
                            .roles(Collections.singleton(SUPER_ADMIN))
                            .username("super")
                            .build()
            );

            userRepository.save(
                    User
                            .builder()
                            .active(true)
                            .email("admin")
                            .password(passwordEncoder.encode("admin"))
                            .fio("admin")
                            .phone("+998993793877")
                            .roles(Collections.singleton(ADMIN))
                            .username("admin")
                            .build()
            );

            userRepository.save(
                    User
                            .builder()
                            .active(true)
                            .email("user")
                            .password(passwordEncoder.encode("user"))
                            .fio("user")
                            .phone("+998993793877")
                            .roles(Collections.singleton(USER))
                            .username("user")
                            .build()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


