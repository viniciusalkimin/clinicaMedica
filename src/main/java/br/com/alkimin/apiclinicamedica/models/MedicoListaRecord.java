package br.com.alkimin.apiclinicamedica.models;

public record MedicoListaRecord(String nome, String email, String crm, Especialidade especialidade) {

    public MedicoListaRecord(Medico medico) {
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
