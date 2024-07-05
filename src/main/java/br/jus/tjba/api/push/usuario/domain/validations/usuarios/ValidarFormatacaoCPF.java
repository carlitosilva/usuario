package br.jus.tjba.api.push.usuario.domain.validations.usuarios;

import br.jus.tjba.api.push.usuario.domain.exceptions.ValidacaoException;
import br.jus.tjba.api.push.usuario.model.UsuarioModel;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ValidarFormatacaoCPF implements ValidadorDeUsuario{
    @Override
    public void validar(UsuarioModel dados) {

        Pattern pattern = Pattern.compile("(\\d{3})\\.(\\d{3})\\.(\\d{3})\\-(\\d{2})");
        Matcher matcher = pattern.matcher(dados.cpf());

        if (!matcher.find()) {
            throw new ValidacaoException("CPF com formatação incorreta!");
        }
    }
}
