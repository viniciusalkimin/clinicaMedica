package br.com.alkimin.apiclinicamedica.service;

import br.com.alkimin.apiclinicamedica.controller.DadosAgendamentoConsulta;
import br.com.alkimin.apiclinicamedica.domain.models.Consulta;
import br.com.alkimin.apiclinicamedica.domain.models.Medico;
import br.com.alkimin.apiclinicamedica.domain.repository.ConsultaRepository;
import br.com.alkimin.apiclinicamedica.domain.repository.MedicoRepository;
import br.com.alkimin.apiclinicamedica.domain.repository.PacienteRepository;
import br.com.alkimin.apiclinicamedica.infra.exception.ValidacaoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ConsultaService {

    private ConsultaRepository consultaRepository;

    private MedicoRepository medicoRepository;

    private PacienteRepository pacienteRepository;

    public void agendar(DadosAgendamentoConsulta dados) {

        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("Id do médico informado não existe!");
        }
        if(!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Id do paciente informado não existe!");
        }
        var medico = escolherMedico(dados);
        var paciente = pacienteRepository.findById(dados.idPaciente()).get();

        var consulta = new Consulta(null, medico, paciente, dados.data());
        consultaRepository.save(consulta);

    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if(dados.idMedico() != null) {
            return  medicoRepository.getReferenceById(dados.idMedico());
        }
        if(dados.especialidade() == null) {
            throw  new ValidacaoException("Especialidade é obrigatório se o médico não for escolhido.");
        }
        return medicoRepository.buscarMedicoAleatorioPorEspecialidade(dados.especialidade(), dados.data());
    }


}
