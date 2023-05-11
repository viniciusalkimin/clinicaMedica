package br.com.alkimin.apiclinicamedica.domain.validacoes.agendamento;

import br.com.alkimin.apiclinicamedica.domain.models.DadosAgendamentoConsulta;
import br.com.alkimin.apiclinicamedica.domain.repository.MedicoRepository;
import br.com.alkimin.apiclinicamedica.infra.exception.ValidacaoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ValidarMedicoAtivo implements ValidadorAgendamentoDeConsultas{

    private MedicoRepository medicoRepository;

    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta) {
      if(dadosAgendamentoConsulta.idMedico() == null) {
          return;
      }
      var medEstaAtivo = medicoRepository.findAtivoById(dadosAgendamentoConsulta.idMedico());
      if(!medEstaAtivo) {
          throw new ValidacaoException("O médico informado não está ativo!");
      }
    }
}
