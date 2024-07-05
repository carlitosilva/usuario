package br.jus.tjba.api.push.usuario.infra.data.reposotories;

import br.jus.tjba.api.push.usuario.domain.entities.Sistema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SistemaRepository  extends JpaRepository<Sistema, Long> {
}
