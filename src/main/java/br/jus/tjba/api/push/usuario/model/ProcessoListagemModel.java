package br.jus.tjba.api.push.usuario.model;

import br.jus.tjba.api.push.usuario.domain.entities.Processo;
public record ProcessoListagemModel(Long id, String numeroProcesso, UsuarioListagemModel usuario,
                                    SistemaListagemModel sistema) {

    public ProcessoListagemModel(Processo p) {
        this(p.getId(),
                p.getNumeroProcesso(),
                new UsuarioListagemModel(p.getUsuario()),
                        new SistemaListagemModel(p.getSistema().getSigla()));
    }
}
