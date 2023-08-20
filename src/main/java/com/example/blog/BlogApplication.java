package com.example.blog;

import com.example.blog.Auth.AuthenticationService;
import com.example.blog.Auth.RegistererRequest;
import com.example.blog.Entity.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(AuthenticationService service)
	{
		return args -> {
			var admin = RegistererRequest.builder().firstname("Admin")
					.lastname("Admin")
					.email("admin@mail.com")
					.password("password")
					.role(Role.ADMIN)
					.build();
			System.out.println("Admin token: "+ service.register(admin).getAccessToken());
			var manager = RegistererRequest.builder().firstname("Manager")
					.lastname("Manager")
					.email("manager@mail.com")
					.password("password")
					.role(Role.MANAGER)
					.build();
			System.out.println("Manager token: "+ service.register(manager).getAccessToken());

		};
	}
}
