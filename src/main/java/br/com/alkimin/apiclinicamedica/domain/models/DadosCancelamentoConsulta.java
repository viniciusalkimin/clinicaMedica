package br.com.alkimin.apiclinicamedica.domain.models;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoConsulta(@NotNull Long idConsulta, @NotNull MotivoCancelamento motivoCancelamento) {
}
