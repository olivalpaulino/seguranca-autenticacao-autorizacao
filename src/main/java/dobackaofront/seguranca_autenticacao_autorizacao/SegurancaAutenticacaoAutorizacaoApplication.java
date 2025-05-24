package dobackaofront.seguranca_autenticacao_autorizacao;

import entity.Role;
import entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import repository.RoleRepository;
import repository.UserRepository;

import java.util.Set;

@SpringBootApplication
public class SegurancaAutenticacaoAutorizacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SegurancaAutenticacaoAutorizacaoApplication.class, args);
	}

	// Runner para inserir dados iniciais (dois roles e dois usuários)
	@Bean
	CommandLineRunner initDatabase(RoleRepository roleRepo, UserRepository userRepo, PasswordEncoder passwordEncoder) {
		return args -> {
			// Cria roles ADMIN e USER se ainda não existirem
			if (roleRepo.findByName("ROLE_ADMIN").isEmpty()) {
				roleRepo.save(new Role("ROLE_ADMIN"));
			}
			if (roleRepo.findByName("ROLE_USER").isEmpty()) {
				roleRepo.save(new Role("ROLE_USER"));
			}
			// Cria usuários admin e user com senha criptografada se ainda não existirem
			if (userRepo.findByUsername("admin").isEmpty()) {
				Role adminRole = roleRepo.findByName("ROLE_ADMIN").get();
				Role userRole = roleRepo.findByName("ROLE_USER").get();
				User admin = new User("admin", passwordEncoder.encode("1234"), "Administrador", Set.of(adminRole));
				User commonUser = new User("user", passwordEncoder.encode("1234"), "Usuário Comum", Set.of(userRole));
				userRepo.save(admin);
				userRepo.save(commonUser);
			}
		};
	}
}
