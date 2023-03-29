package br.com.alkimin.apiclinicamedica.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medicos")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Embedded
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;
    public Medico (MedicoDTO medicoDTO) {
        this.nome = medicoDTO.getNome();
        this.email = medicoDTO.getEmail();
        this.telefone = medicoDTO.getTelefone();
        this.crm = medicoDTO.getCrm();
        this.especialidade = medicoDTO.getEspecialidade();
        this.endereco = medicoDTO.getEndereco();
    }
}
