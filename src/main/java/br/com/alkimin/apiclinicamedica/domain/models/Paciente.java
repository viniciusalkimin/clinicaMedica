package br.com.alkimin.apiclinicamedica.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "pacientes")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;

    private String telefone;

    private String cpf;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Paciente(PacienteRecord pacienteRecord) {
        this.nome = pacienteRecord.nome();
        this.email = pacienteRecord.email();
        this.cpf = pacienteRecord.cpf();
        this.endereco = new Endereco(pacienteRecord.enderecoRecord());
        this.ativo = pacienteRecord.ativo();
    }
}
