package br.com.alkimin.apiclinicamedica.service;

import br.com.alkimin.apiclinicamedica.models.Medico;
import br.com.alkimin.apiclinicamedica.models.MedicoRecord;
import br.com.alkimin.apiclinicamedica.repository.MedicoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MedicoService {

    private MedicoRepository repository;

    public void salvarMedico(Medico medico) {
        repository.save(medico);
    }

}
