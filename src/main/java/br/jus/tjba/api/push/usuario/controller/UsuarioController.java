package br.jus.tjba.api.push.usuario.controller;

import br.jus.tjba.api.push.usuario.domain.entities.Endereco;
import br.jus.tjba.api.push.usuario.domain.entities.Processo;
import br.jus.tjba.api.push.usuario.domain.entities.Usuario;
import br.jus.tjba.api.push.usuario.domain.services.UsuarioService;
import br.jus.tjba.api.push.usuario.infra.data.reposotories.ProcessoRepository;
import br.jus.tjba.api.push.usuario.infra.data.reposotories.SistemaRepository;
import br.jus.tjba.api.push.usuario.infra.data.reposotories.UsuarioRepository;
import br.jus.tjba.api.push.usuario.model.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {


    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private SistemaRepository sistemaRepository;
    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid UsuarioModel usuarioModel, UriComponentsBuilder uriBuilder) {

        var usuarioDetalheModel = usuarioService.cadastrar(usuarioModel);

        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuarioDetalheModel.id()).toUri();

        return ResponseEntity.created(uri).body(usuarioDetalheModel);
    }


    @GetMapping
    public ResponseEntity<Page<UsuarioListagemModel>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = usuarioRepository.findAll(paginacao).
                map(UsuarioListagemModel::new);
        return ResponseEntity.ok(page);

    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var usuario = usuarioRepository.getReferenceById(id);
        return ResponseEntity.ok(new UsuarioDetalheModel(usuario));

    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid UsuarioAtualizacaoModel usuarioAtualizacao) {

        var usuario = usuarioRepository.getReferenceById(usuarioAtualizacao.id());

        usuario.atualizar(usuarioAtualizacao);
        return ResponseEntity.ok(new UsuarioDetalheModel(usuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {

        usuarioRepository.deleteById(id);
        //var usuario = usuarioRepository.getReferenceById(id);
        //usuario.excluir();
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/associar-processo")
    @Transactional
    public ResponseEntity associar(@RequestBody @Valid ProcessoModel processoModel, UriComponentsBuilder uriBuilder) {
/*
        var usuario = usuarioRepository.getReferenceById(processoModel.usuario_id());
        var sistema = sistemaRepository.getReferenceById(processoModel.sistema_id());

        var processo = new Processo(usuario,sistema,processoModel.numeroProcesso());

        processoRepository.save(processo);

        var uri = uriBuilder.path("/usuarios/buscar-associados-processo").buildAndExpand(processo.getId()).toUri();

        return ResponseEntity.created(uri).body(processoModel);*/

        var processo = usuarioService.associar(processoModel);

        var uri = uriBuilder.path("/usuarios/buscar-associados-processo").buildAndExpand(processo.id()).toUri();

        return ResponseEntity.created(uri).body(processoModel);
    }

    @DeleteMapping("/desassociar-processo/{id}")
    @Transactional
    public ResponseEntity desassociar(@PathVariable Long id) {

        processoRepository.deleteById(id);
        //var usuario = usuarioRepository.getReferenceById(id);
        //usuario.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar-associados-processo")
    public ResponseEntity<Page<ProcessoListagemModel>> listarAssociados(@RequestBody @Valid ProcessoParametroModel processoModel, @PageableDefault Pageable paginacao) {
        var page = processoRepository.findAllByNumeroProcessoAndSiglaSistema(processoModel.numeroProcesso(), processoModel.siglaSistema(), paginacao).
                map(p -> new ProcessoListagemModel(p.getId(), p.getNumeroProcesso(), new UsuarioListagemModel(p.getUsuario()), new SistemaListagemModel(p.getSistema().getSigla())));
        return ResponseEntity.ok(page);
    }

    @GetMapping("/obter-lista-publicacao")
    public ResponseEntity<List<ProcessoListagemModel>> listarPublicaveis(@RequestParam("numeroProcesso") String numeroProcesso, @RequestParam("siglaSistema") String siglaSistema, @PageableDefault Pageable paginacao) {
        var processosList = processoRepository.findAllByNumeroProcessoAndSiglaSistema(numeroProcesso, siglaSistema, paginacao).stream().
                map(p -> new ProcessoListagemModel(p)).collect(Collectors.toList());
        return ResponseEntity.ok(processosList);

/*        var page = processoRepository.findAllByNumeroProcessoAndSiglaSistema(numeroProcesso, siglaSistema, paginacao).
                map(p -> new ProcessoListagemModel(p.getId(), p.getNumeroProcesso(), new UsuarioListagemModel(p.getUsuario()), new SistemaListagemModel(p.getSistema().getSigla())));
        return ResponseEntity.ok(page.stream().toList());*/
    }
}
