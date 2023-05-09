package br.com.alkimin.apiclinicamedica.domain.validacoes;

import br.com.alkimin.apiclinicamedica.domain.models.DadosAgendamentoConsulta;
import br.com.alkimin.apiclinicamedica.domain.repository.ConsultaRepository;
import br.com.alkimin.apiclinicamedica.infra.exception.ValidacaoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ValidarPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoDeConsultas{

    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta) {
        var primeiroHorario = dadosAgendamentoConsulta.data().withHour(7);
        var ultimoHorario = dadosAgendamentoConsulta.data().withHour(18);
        var pacientePossuiOutraConsultaNoDia = consultaRepository.existsByPacienteIdAndDataBetween(dadosAgendamentoConsulta.idPaciente(), primeiroHorario, ultimoHorario);
        if (pacientePossuiOutraConsultaNoDia) {
            throw new ValidacaoException("Paciente já possui outra consulta nesse dia.");
        }
    }
}
