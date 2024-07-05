package br.jus.tjba.api.push.usuario.domain.validations.usuarios;

import br.jus.tjba.api.push.usuario.model.UsuarioModel;

public interface ValidadorDeUsuario {

    void validar(UsuarioModel dados);
}
