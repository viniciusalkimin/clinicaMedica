package br.com.alkimin.apiclinicamedica.domain.models;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(@NotNull Long idPaciente, Long idMedico, @NotNull@Future LocalDateTime data, Especialidade especialidade) {
}
