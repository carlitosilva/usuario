package br.jus.tjba.api.push.usuario.model;

import br.jus.tjba.api.push.usuario.domain.entities.Endereco;
import br.jus.tjba.api.push.usuario.domain.entities.Usuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record EnderecoModel(
        @NotBlank
        String rua,
        @NotBlank
        String bairro,
        @NotBlank
        String cidade,
        @NotBlank
        String uf,
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep,
        String numero,
        UsuarioModel usuario) {
/*        public EnderecoModel(Endereco endereco){
                this(endereco.getRua(),
                        endereco.getBairro(),
                        endereco.getCidade(),
                        endereco.getUf(),
                        endereco.getCep(),
                        endereco.getNumero(),
                        new UsuarioModel(endereco.getUsuario()));
        }*/

}
