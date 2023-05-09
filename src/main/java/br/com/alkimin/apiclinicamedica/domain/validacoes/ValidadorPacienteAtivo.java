package br.com.alkimin.apiclinicamedica.domain.validacoes;


import br.com.alkimin.apiclinicamedica.domain.models.DadosAgendamentoConsulta;
import br.com.alkimin.apiclinicamedica.domain.repository.PacienteRepository;
import br.com.alkimin.apiclinicamedica.infra.exception.ValidacaoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsultas {


    private PacienteRepository pacienteRepository;

    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta) {
        var pacienteEstaAtivo = pacienteRepository.findAtivoById(dadosAgendamentoConsulta.idPaciente());
        if (!pacienteEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com paciente excluído");
        }
    }
}
