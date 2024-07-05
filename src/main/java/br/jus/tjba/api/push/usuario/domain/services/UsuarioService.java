package br.jus.tjba.api.push.usuario.domain.services;

import br.jus.tjba.api.push.usuario.domain.entities.Endereco;
import br.jus.tjba.api.push.usuario.domain.entities.Processo;
import br.jus.tjba.api.push.usuario.domain.entities.Usuario;
import br.jus.tjba.api.push.usuario.domain.exceptions.ValidacaoException;
import br.jus.tjba.api.push.usuario.domain.validations.usuarios.ValidadorDeUsuario;
import br.jus.tjba.api.push.usuario.infra.data.reposotories.ProcessoRepository;
import br.jus.tjba.api.push.usuario.infra.data.reposotories.SistemaRepository;
import br.jus.tjba.api.push.usuario.infra.data.reposotories.UsuarioRepository;
import br.jus.tjba.api.push.usuario.model.ProcessoDetalheModel;
import br.jus.tjba.api.push.usuario.model.ProcessoModel;
import br.jus.tjba.api.push.usuario.model.UsuarioDetalheModel;
import br.jus.tjba.api.push.usuario.model.UsuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private List<ValidadorDeUsuario> validadores;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SistemaRepository sistemaRepository;

    @Autowired
    private ProcessoRepository processoRepository;

    public UsuarioDetalheModel cadastrar(UsuarioModel usuarioModel) {

        validadores.forEach(v -> v.validar(usuarioModel));

        var usuario = new Usuario(usuarioModel);
        var endereco = new Endereco(usuarioModel.endereco());
        usuario.setEndereco(endereco);
        endereco.setUsuario(usuario);
        usuarioRepository.save(usuario);
        return new UsuarioDetalheModel(usuario);

    }

    public ProcessoDetalheModel associar(ProcessoModel processoModel) {

        if (!usuarioRepository.existsById(processoModel.usuario_id())) {
            throw new ValidacaoException("Id do Usuário informado não existe!");
        }

        if (!sistemaRepository.existsById(processoModel.sistema_id())) {
            throw new ValidacaoException("Id do Sistema informado não existe!");
        }

        var usuario = usuarioRepository.getReferenceById(processoModel.usuario_id());
        var sistema = sistemaRepository.getReferenceById(processoModel.sistema_id());

        var processo = new Processo(usuario, sistema, processoModel.numeroProcesso());

        processoRepository.save(processo);
        return new ProcessoDetalheModel(
                processo.getId(),
                processo.getUsuario().getId(),
                processo.getSistema().getId(),
                processo.getNumeroProcesso());

    }
}
