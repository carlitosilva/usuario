package br.jus.tjba.api.push.usuario.model;

import br.jus.tjba.api.push.usuario.domain.entities.Usuario;

public record UsuarioListagemModel(Long id, String nome, String cpf, String login) {
    public UsuarioListagemModel(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getCpf(), usuario.getLogin());
    }
}