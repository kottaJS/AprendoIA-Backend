package cl.duoc.aprendoia.backend.repository;

import cl.duoc.aprendoia.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmailIgnoreCase(String email);

    Optional<Usuario> findByAliasIgnoreCase(String alias);

    boolean existsByEmailIgnoreCase(String email);
}
