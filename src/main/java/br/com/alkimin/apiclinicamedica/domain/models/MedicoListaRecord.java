package br.com.alkimin.apiclinicamedica.domain.models;

import java.util.UUID;

public record MedicoListaRecord(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public MedicoListaRecord(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
