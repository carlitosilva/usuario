package br.jus.tjba.api.push.usuario.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProcessoModel(
        Long id,
        @NotNull
        Long usuario_id,
        @NotNull
        Long sistema_id,
        @NotBlank
        String numeroProcesso
) {
}
