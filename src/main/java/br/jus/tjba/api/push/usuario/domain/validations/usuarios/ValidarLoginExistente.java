package br.jus.tjba.api.push.usuario.domain.validations.usuarios;

import br.jus.tjba.api.push.usuario.domain.exceptions.ValidacaoException;
import br.jus.tjba.api.push.usuario.infra.data.reposotories.UsuarioRepository;
import br.jus.tjba.api.push.usuario.model.UsuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarLoginExistente implements ValidadorDeUsuario{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void validar(UsuarioModel dados) {

        var usuarioExiste = usuarioRepository.findByLogin(dados.login());
        if (usuarioExiste != null) {
            throw new ValidacaoException("Login do usuário informado já existe!");
        }
    }
}
