package br.com.alkimin.apiclinicamedica.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Endereco {
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

    public Endereco(EnderecoRecord enderecoRecord) {
        this.logradouro = enderecoRecord.logradouro();
        this.numero = enderecoRecord.numero();
        this.complemento = enderecoRecord.complemento();
        this.bairro = enderecoRecord.bairro();
        this.cidade = enderecoRecord.cidade();
        this.uf = enderecoRecord.uf();
        this.cep = enderecoRecord.cep();
    }
}
