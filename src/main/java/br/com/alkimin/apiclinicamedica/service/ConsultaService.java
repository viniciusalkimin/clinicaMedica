package br.com.alkimin.apiclinicamedica.service;

import br.com.alkimin.apiclinicamedica.domain.models.DadosAgendamentoConsulta;
import br.com.alkimin.apiclinicamedica.domain.models.Consulta;
import br.com.alkimin.apiclinicamedica.domain.models.DadosCancelamentoConsulta;
import br.com.alkimin.apiclinicamedica.domain.models.Medico;
import br.com.alkimin.apiclinicamedica.domain.repository.ConsultaRepository;
import br.com.alkimin.apiclinicamedica.domain.repository.MedicoRepository;
import br.com.alkimin.apiclinicamedica.domain.repository.PacienteRepository;
import br.com.alkimin.apiclinicamedica.domain.validacoes.ValidadorAgendamentoDeConsultas;
import br.com.alkimin.apiclinicamedica.infra.exception.ValidacaoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@AllArgsConstructor
public class ConsultaService {

    private ConsultaRepository consultaRepository;

    private MedicoRepository medicoRepository;

    private PacienteRepository pacienteRepository;

    private List<ValidadorAgendamentoDeConsultas> validadores;

    public void agendar(DadosAgendamentoConsulta dados) {

        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("Id do médico informado não existe!");
        }
        if(!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Id do paciente informado não existe!");
        }

        validadores.forEach(v -> v.validar(dados));
        var medico = escolherMedico(dados);
        var paciente = pacienteRepository.findById(dados.idPaciente()).get();

        var consulta = new Consulta(null, medico, paciente, dados.data(), true, null);
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

    public void cancelar(DadosCancelamentoConsulta dados) {
        var consulta = consultaRepository.findById(dados.idConsulta()).get();
        var horarioAgendado = consulta.getData();
        var agora = LocalDateTime.now();
        var intervalo = agora.until(horarioAgendado, ChronoUnit.HOURS);
        if(intervalo < 24L) {
            throw new ValidacaoException("Não é possivel cancelar consulta com menos de 24 horas!");
        }
        if(dados.motivoCancelamento() == null) {
            throw new ValidacaoException("Não é possivel cancelar consulta sem informar o motivo!");
        }
        consultaRepository.cancelar(dados.idConsulta(), dados.motivoCancelamento().toString());
    }
}
