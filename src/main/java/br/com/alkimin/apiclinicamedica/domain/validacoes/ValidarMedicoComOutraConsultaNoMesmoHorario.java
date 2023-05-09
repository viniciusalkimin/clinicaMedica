package br.com.alkimin.apiclinicamedica.domain.validacoes;

import br.com.alkimin.apiclinicamedica.domain.models.DadosAgendamentoConsulta;
import br.com.alkimin.apiclinicamedica.domain.repository.ConsultaRepository;
import br.com.alkimin.apiclinicamedica.infra.exception.ValidacaoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ValidarMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsultas{

    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta) {
        var medicoPossuiOutraConsultaNoMesmoHorario = consultaRepository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(dadosAgendamentoConsulta.idMedico(), dadosAgendamentoConsulta.data());
        if(medicoPossuiOutraConsultaNoMesmoHorario) {
            throw new ValidacaoException("Medico informado ja possui outra consulta nesse horário");
        }
    }

    }
