package br.com.alkimin.apiclinicamedica.domain.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PacienteListRecord(

        Long id,

        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{10,11}")
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String cpf,
        @NotNull
        EnderecoRecord enderecoRecord,
        @NotNull
        @Valid
        Boolean ativo) {

    public PacienteListRecord(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(),paciente.getCpf(),new EnderecoRecord(paciente.getEndereco()), paciente.getAtivo());
    }
}
