package br.jus.tjba.api.push.usuario.model;

import br.jus.tjba.api.push.usuario.domain.entities.Endereco;
import br.jus.tjba.api.push.usuario.domain.entities.Usuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UsuarioModel(

        @NotBlank
        String nome,
        @NotBlank
        String login,
        @NotBlank
        String senha,
        @NotBlank
        @Pattern(regexp = "(\\d{3})\\.(\\d{3})\\.(\\d{3})\\-(\\d{2})")
        String cpf,
        @NotNull
        @Valid
        EnderecoModel endereco) {
    /*public UsuarioModel(Usuario usuario) {
        this(usuario.getNome(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getCpf(),
                new EnderecoModel(usuario.getEndereco()));
    }*/
}
