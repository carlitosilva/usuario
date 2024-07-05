package br.jus.tjba.api.push.usuario.model;

import jakarta.validation.constraints.NotNull;

public record UsuarioAtualizacaoModel(
        @NotNull
        Long id,
        String nome,
        String cpf,
        EnderecoModel endereco) {
}
