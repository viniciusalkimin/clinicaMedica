package br.com.alkimin.apiclinicamedica.service;

import br.com.alkimin.apiclinicamedica.domain.models.Paciente;
import br.com.alkimin.apiclinicamedica.domain.repository.PacienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PacienteService {

    private PacienteRepository pacienteRepository;
    public void salvarPaciente(Paciente pac) {
        pacienteRepository.save(pac);
    }
}
