package br.com.alkimin.apiclinicamedica.domain.validacoes.cancelamento;

import br.com.alkimin.apiclinicamedica.domain.models.DadosAgendamentoConsulta;
import br.com.alkimin.apiclinicamedica.domain.models.DadosCancelamentoConsulta;
import br.com.alkimin.apiclinicamedica.domain.repository.ConsultaRepository;
import br.com.alkimin.apiclinicamedica.infra.exception.ValidacaoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
@AllArgsConstructor
public class ValidarAntecedenciaCancelamento implements ValidadorCancelamentoDeConsulta{

    private ConsultaRepository consultaRepository;

    public void validar(DadosCancelamentoConsulta dadosCancelamentoConsulta) {
        var consulta = consultaRepository.getReferenceById(dadosCancelamentoConsulta.idConsulta());
        var horarioAgendado = consulta.getData();
        var agora = LocalDateTime.now();
        var intervalo = agora.until(horarioAgendado, ChronoUnit.HOURS);
        if(intervalo < 24L) {
            throw new ValidacaoException("Não é possivel cancelar consulta com menos de 24 horas!");
        }
    }
}
