package br.com.alkimin.apiclinicamedica.domain.models;

import java.util.UUID;

public record MedicoDetalhar(UUID id, String nome, String telefone, String crm, String email, EnderecoRecord enderecoRecord, Especialidade especialidade) {
    public MedicoDetalhar(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getTelefone(), medico.getEmail(), medico.getCrm(), new EnderecoRecord(medico.getEndereco()), medico.getEspecialidade());
    }
}
