package br.jus.tjba.api.push.usuario.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProcessoId {
    //@Column(name = "usuario_id")
    private Long usuarioId;

    //@Column(name = "sistema_id")
    private Long sistemaId;
}
