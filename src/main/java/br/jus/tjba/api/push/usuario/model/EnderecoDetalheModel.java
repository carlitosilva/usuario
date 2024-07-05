package br.jus.tjba.api.push.usuario.model;

import br.jus.tjba.api.push.usuario.domain.entities.Endereco;

public record EnderecoDetalheModel(
        String rua,
        String bairro,
        String cidade,
        String uf,
        String cep,
        String numero
) {
    public EnderecoDetalheModel(Endereco end) {
        this(end.getRua(), end.getBairro(), end.getCidade(), end.getUf(), end.getCep(), end.getNumero());
    }
}
