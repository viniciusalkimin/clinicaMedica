package br.com.alkimin.apiclinicamedica.domain.validacoes.agendamento;

import br.com.alkimin.apiclinicamedica.domain.models.DadosAgendamentoConsulta;
import br.com.alkimin.apiclinicamedica.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
@Component
public class ValidarHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsultas{

    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta) {

        var dataConsulta = dadosAgendamentoConsulta.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAbertura = dataConsulta.getHour() < 7;
        var depoisDoFechamento = dataConsulta.getHour() > 18;
        if(domingo || antesDaAbertura || depoisDoFechamento) {
            throw new ValidacaoException("Horário inválido! A clinica funciona de Seg a Sab, das 7 as 19h.");
        }
    }
}
