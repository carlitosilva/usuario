package br.jus.tjba.api.push.usuario.infra.data.reposotories;

import br.jus.tjba.api.push.usuario.domain.entities.Processo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/*public interface ProcessoRepository  extends JpaRepository<Processo, ProcessoId> {
}*/
public interface ProcessoRepository  extends JpaRepository<Processo, Long> {
    @Query("""
            select p
            from Processo p
            join Usuario u on u.id = p.usuario.id
            join Sistema s on s.id = p.sistema.id
            where
            p.numeroProcesso = :numeroProcesso and 
            s.sigla = :siglaSistema
            """)
    Page<Processo> findAllByNumeroProcessoAndSiglaSistema(String numeroProcesso, String siglaSistema, Pageable paginacao);

    @Query("""
            select p
            from Processo p
            join Usuario u on u.id = p.usuario.id
            join Sistema s on s.id = p.sistema.id
            where
            p.dataPublicacao is null
            order by s.sigla, u.id
            """)
    List<Processo> findAllByDataPublicacaoIsNull();
}