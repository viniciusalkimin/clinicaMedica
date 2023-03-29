package br.com.alkimin.apiclinicamedica.models;

import br.com.alkimin.apiclinicamedica.models.Endereco;
import br.com.alkimin.apiclinicamedica.models.Especialidade;
import br.com.alkimin.apiclinicamedica.models.Medico;
import lombok.Data;

import java.util.UUID;

@Data
public class MedicoDTO {

    private UUID id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    private Especialidade especialidade;
    private Endereco endereco;

    public MedicoDTO (Medico medico) {
        this.nome = medico.getNome();
        this.email = medico.getEmail();
        this.telefone = medico.getTelefone();
        this.crm = medico.getCrm();
        this.especialidade = medico.getEspecialidade();
        this.endereco = medico.getEndereco();

    }
}
