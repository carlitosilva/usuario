package br.jus.tjba.api.push.usuario.domain.validations.usuarios;

import br.jus.tjba.api.push.usuario.domain.exceptions.ValidacaoException;
import br.jus.tjba.api.push.usuario.model.UsuarioModel;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ValidarFormacaoCEPEndereco implements ValidadorDeUsuario{
    @Override
    public void validar(UsuarioModel dados) {

        Pattern pattern = Pattern.compile("(\\d{8})");
        Matcher matcher = pattern.matcher(dados.endereco().cep());

        if (!matcher.find()) {
            throw new ValidacaoException("CEP com formatação incorreta!");
        }
    }
}
