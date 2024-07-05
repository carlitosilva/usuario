package br.jus.tjba.api.push.usuario.infra.data.reposotories;

import br.jus.tjba.api.push.usuario.domain.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
