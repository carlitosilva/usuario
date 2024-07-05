package br.jus.tjba.api.push.usuario.model;

import br.jus.tjba.api.push.usuario.domain.entities.Endereco;
import br.jus.tjba.api.push.usuario.domain.entities.Usuario;

public record UsuarioDetalheModel(Long id, String nome, String cpf, String login, EnderecoDetalheModel endereco) {

    public UsuarioDetalheModel(Usuario usuario) {
        this(usuario.getId(),
                usuario.getNome(),
                usuario.getCpf(),
                usuario.getLogin(),
                new EnderecoDetalheModel(usuario.getEndereco())
        );
    }
}
