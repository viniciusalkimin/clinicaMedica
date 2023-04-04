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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;
    public Medico (MedicoRecord medicoRecord) {
        this.nome = medicoRecord.nome();
        this.email = medicoRecord.email();
        this.telefone = medicoRecord.telefone();
        this.crm = medicoRecord.crm();
        this.especialidade = medicoRecord.especialidade();
        this.endereco = new Endereco(medicoRecord.endereco());
    }
}
