package com.amasy.gtbackend;

import com.amasy.gtbackend.config.AppConstants;
import com.amasy.gtbackend.entities.Role;
import com.amasy.gtbackend.repositories.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class GramtarangBackendApplication implements CommandLineRunner {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(GramtarangBackendApplication.class, args);
	}

	@Bean
    public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	@Override
	public void run(String... args) {
		System.out.println(this.passwordEncoder.encode("12345"));
		try{
			Role role = new Role();
			role.setId(AppConstants.SRC_USER);
			role.setName("SRC_USER");

			Role role1 = new Role();
			role1.setId(AppConstants.TP_USER);
			role1.setName("TP_USER");

			List<Role> roles = List.of(role, role1);
			List<Role> result = this.roleRepo.saveAll(roles);
			result.forEach(r -> System.out.println(r.getName()));
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
