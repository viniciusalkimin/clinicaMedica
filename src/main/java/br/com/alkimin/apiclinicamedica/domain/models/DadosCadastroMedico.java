package br.com.alkimin.apiclinicamedica.domain.models;

public record DadosCadastroMedico(
        String nome,
        String email,
        String telefone,
        String crm,
        Especialidade especialidade,DadosEndereco endereco) {
}
