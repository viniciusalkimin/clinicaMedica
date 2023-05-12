package br.com.alkimin.apiclinicamedica.domain.models;

public record DadosEndereco(
                            String logradouro,
                            String bairro,
                            String cep,
                            String cidade,
                            String uf,
                            String complemento,
                            String numero) {
}
