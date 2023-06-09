package br.com.alkimin.apiclinicamedica.domain.models;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;
    private Boolean ativo;
    public Medico (MedicoRecord medicoRecord) {
        this.nome = medicoRecord.nome();
        this.email = medicoRecord.email();
        this.telefone = medicoRecord.telefone();
        this.crm = medicoRecord.crm();
        this.especialidade = medicoRecord.especialidade();
        this.endereco = new Endereco(medicoRecord.endereco());
        this.ativo = true;
    }

    public Medico(DadosCadastroMedico dadosCadastroMedico) {
        this.ativo = true;
        this.nome = dadosCadastroMedico.nome();
        this.email = dadosCadastroMedico.email();
        this.telefone = dadosCadastroMedico.telefone();
        this.crm = dadosCadastroMedico.crm();
        this.especialidade = dadosCadastroMedico.especialidade();
        this.endereco = new Endereco(dadosCadastroMedico.endereco());
    }

    public void editarMedico(MedicoEditarRecord medicoEditarRecord) {
        if(medicoEditarRecord.nome() != null) {
            this.nome = medicoEditarRecord.nome();
        }
        if(medicoEditarRecord.telefone() != null) {
            this.telefone = medicoEditarRecord.telefone();
        }
        if(medicoEditarRecord.enderecoRecord() != null) {
            this.endereco = new Endereco(medicoEditarRecord.enderecoRecord());
        }
    }

    public void desativar(){
        this.ativo = false;
    }
}
