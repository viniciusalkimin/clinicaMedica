package br.com.alkimin.apiclinicamedica.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public record MedicoEditarRecord(
        @NotNull
        UUID id,
        String nome,
        String telefone,
        EnderecoRecord enderecoRecord) {
}
