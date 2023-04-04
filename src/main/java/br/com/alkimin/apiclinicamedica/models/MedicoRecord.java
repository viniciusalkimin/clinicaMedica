package br.com.alkimin.apiclinicamedica.models;

public record MedicoRecord(String nome, String email, String crm, Especialidade especialidade, EnderecoRecord endereco) {
}
