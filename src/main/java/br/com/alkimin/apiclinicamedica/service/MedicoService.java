package br.com.alkimin.apiclinicamedica.service;

import br.com.alkimin.apiclinicamedica.models.Medico;
import br.com.alkimin.apiclinicamedica.models.MedicoEditarRecord;
import br.com.alkimin.apiclinicamedica.repository.MedicoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MedicoService {

    private MedicoRepository repository;

    public void salvarMedico(Medico medico) {
        repository.save(medico);
    }

    public Optional<Medico> medicoById(UUID id) {
        return repository.findById(id);
    }


    public Page<Medico> listarMedicos(Pageable paginacao) {
        return repository.findAllByestaAtivoTrue(paginacao);
    }

    public void editarMedico(MedicoEditarRecord medicoEditarRecord) {
        Optional<Medico> medico = repository.findById(medicoEditarRecord.id());
        if(medico.isPresent()){
            medico.get().editarMedico(medicoEditarRecord);
            repository.save(medico.get());
        }
    }

    public void desativarMedico(UUID id) {
        var medico = repository.findById(id);
        if(medico.isPresent()) {
            medico.get().desativar();
            repository.save(medico.get());
        }
    }
}
