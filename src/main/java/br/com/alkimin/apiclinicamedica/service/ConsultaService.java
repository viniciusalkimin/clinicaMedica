package br.com.alkimin.apiclinicamedica.service;

import br.com.alkimin.apiclinicamedica.domain.models.*;
import br.com.alkimin.apiclinicamedica.domain.repository.ConsultaRepository;
import br.com.alkimin.apiclinicamedica.domain.repository.MedicoRepository;
import br.com.alkimin.apiclinicamedica.domain.repository.PacienteRepository;
import br.com.alkimin.apiclinicamedica.domain.validacoes.agendamento.ValidadorAgendamentoDeConsultas;
import br.com.alkimin.apiclinicamedica.domain.validacoes.cancelamento.ValidadorCancelamentoDeConsulta;
import br.com.alkimin.apiclinicamedica.infra.exception.ValidacaoException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConsultaService {

    private ConsultaRepository consultaRepository;

    private MedicoRepository medicoRepository;

    private PacienteRepository pacienteRepository;

    private List<ValidadorAgendamentoDeConsultas> validadorAgendamentoDeConsultas;

    private List<ValidadorCancelamentoDeConsulta> validadorCancelamentoDeConsultaLid;

    public ConsultaListaRecord agendar(DadosAgendamentoConsulta dados) {

        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("Id do médico informado não existe!");
        }
        if(!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Id do paciente informado não existe!");
        }

        validadorAgendamentoDeConsultas.forEach(v -> v.validar(dados));
        var medico = escolherMedico(dados);
        if (medico == null) {
            throw new ValidacaoException("Não existe médico disponível na data selecionada.");
        }
        var paciente = pacienteRepository.findById(dados.idPaciente()).get();

        var consulta = new Consulta(null, medico, paciente, dados.data(), null);
        consultaRepository.save(consulta);
        return new ConsultaListaRecord(consulta);
    }

    public void cancelar(DadosCancelamentoConsulta dados) {

        if(!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidacaoException("Id de consulta não existe;");
        }

        validadorCancelamentoDeConsultaLid.forEach( v -> v.validar(dados));

        var consultaCancelar = consultaRepository.findById(dados.idConsulta());

        consultaCancelar.get().cancelar(dados.motivoCancelamento());
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


    public List<Consulta> listarTodas() {
        return consultaRepository.findAll();
    }
}
