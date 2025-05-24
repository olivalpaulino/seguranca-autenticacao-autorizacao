package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    // 1. PasswordEncoder Bean (BCrypt)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. Autenticacao: Authentication Provider (opcional, para vincular UserDetailsService e PasswordEncoder)
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    // 3. Configuração da cadeia de filtros de segurança (autorização de URLs, página de login, etc.)
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Desativando CSRF para simplificar este exemplo (não fazer em produção sem avaliar!)
        http.csrf(csrf -> csrf.disable());

        // Autorizações de requisições
        http.authorizeHttpRequests(authorize -> authorize
                // Permite acesso público a recursos estáticos e determinadas URLs
                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                .requestMatchers("/", "/home", "/login", "/h2-console/**").permitAll()
                // Define restrição de acesso por role
                .requestMatchers("/admin/**").hasRole("ADMIN")   // acessível apenas para ROLE_ADMIN
                .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN") // acessível para ROLE_USER ou ROLE_ADMIN
                // Qualquer outra requisição requer autenticação
                .anyRequest().authenticated()
        );

        // Formulário de login personalizado
        http.formLogin(form -> form
                .loginPage("/login")            // caminho da página de login customizada
                .permitAll()                    // permite a todos ver a página de login
                .usernameParameter("username")  // nome do campo de username no form (padrão "username")
                .passwordParameter("password")  // nome do campo de senha no form (padrão "password")
                .defaultSuccessUrl("/", true)   // para onde redirecionar após login bem-sucedido (true = sempre vai para home)
                .failureUrl("/login?error=true")// para onde redirecionar se falha no login (com um parâmetro para feedback)
        );

        // Configuração de logout
        http.logout(logout -> logout
                .logoutUrl("/logout")               // URL para logout (por padrão é /logout mesmo)
                .logoutSuccessUrl("/login?logout")  // redireciona para login apos logout, com parâmetro de logout
                .permitAll()
        );

        // Configuração de acesso negado (403) - página personalizada
        http.exceptionHandling(exception -> exception
                .accessDeniedPage("/access-denied")
        );

        // ** Extra: permitir H2 console (frames) se estiver usando H2 web console **
        http.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

        // Registrar nosso auth provider no HttpSecurity
        http.authenticationProvider(authenticationProvider());

        return http.build();
    }
}
