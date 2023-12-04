package com.example.ClassService;

import com.example.ClassService.models.Role;
import com.example.ClassService.models.User;
import com.example.ClassService.repository.RoleRepository;
import com.example.ClassService.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ClassServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClassServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncode){
		return args ->{
			if(roleRepository.findByAuthority("ADMIN").isPresent()) return;
			Role adminRole = roleRepository.save(new Role(0L, "ADMIN"));
			roleRepository.save(new Role(0L, "USER"));

			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);

			User admin = new User(0L, "admin", passwordEncode.encode("password"), roles);

			userRepository.save(admin);
		};
	}

}
