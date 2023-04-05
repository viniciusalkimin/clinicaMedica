package br.com.alkimin.apiclinicamedica.domain.models;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record MedicoEditarRecord(
        @NotNull
        UUID id,
        String nome,
        String telefone,
        EnderecoRecord enderecoRecord) {
}
