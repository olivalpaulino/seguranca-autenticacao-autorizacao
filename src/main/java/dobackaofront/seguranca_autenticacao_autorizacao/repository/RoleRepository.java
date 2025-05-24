package dobackaofront.seguranca_autenticacao_autorizacao.repository;

import dobackaofront.seguranca_autenticacao_autorizacao.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
