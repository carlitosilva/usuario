package br.jus.tjba.api.push.usuario.infra.data.reposotories;
import br.jus.tjba.api.push.usuario.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByLogin(String login);
}