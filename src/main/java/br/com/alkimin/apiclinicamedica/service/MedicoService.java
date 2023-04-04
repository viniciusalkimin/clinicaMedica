package br.com.alkimin.apiclinicamedica.service;

import br.com.alkimin.apiclinicamedica.models.Medico;
import br.com.alkimin.apiclinicamedica.repository.MedicoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MedicoService {

    private MedicoRepository repository;

    public void salvarMedico(Medico medico) {
        repository.save(medico);
    }

    public Page<Medico> listarMedicos(Pageable paginacao) {
        return repository.findAll(paginacao);
    }
}
