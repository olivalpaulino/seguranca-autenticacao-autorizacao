package service;

import entity.Role;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import repository.UserRepository;

import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca o usuário pelo username no banco
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

        // Converte as roles do usuário em uma coleção de GrantedAuthority
        var authorities = user.getRoles().stream()
                .map(Role::getName)  // pega o nome da role, ex: "ROLE_ADMIN"
                .map(SimpleGrantedAuthority::new)  // converte cada nome de role em um objeto GrantedAuthority
                .collect(Collectors.toList());

        // Retorna um objeto org.springframework.security.core.userdetails.User
        // contendo username, senha e as autoridades (roles) do usuário.
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}
