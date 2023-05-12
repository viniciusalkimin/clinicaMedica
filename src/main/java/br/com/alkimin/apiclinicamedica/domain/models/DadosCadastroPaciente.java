package br.com.alkimin.apiclinicamedica.domain.models;

public record DadosCadastroPaciente(
        String nome,
        String email,
        String telefone,
        String cpf,DadosEndereco endereco) {
}
