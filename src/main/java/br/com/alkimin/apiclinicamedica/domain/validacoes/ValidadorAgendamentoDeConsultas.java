package br.com.alkimin.apiclinicamedica.domain.validacoes;

import br.com.alkimin.apiclinicamedica.domain.models.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoDeConsultas {

    void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta);
}
