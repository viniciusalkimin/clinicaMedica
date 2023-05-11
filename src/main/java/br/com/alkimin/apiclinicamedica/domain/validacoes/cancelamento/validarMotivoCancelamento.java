package br.com.alkimin.apiclinicamedica.domain.validacoes.cancelamento;

import br.com.alkimin.apiclinicamedica.domain.models.DadosCancelamentoConsulta;
import br.com.alkimin.apiclinicamedica.infra.exception.ValidacaoException;

public class validarMotivoCancelamento implements ValidadorCancelamentoDeConsulta {
    @Override
    public void validar(DadosCancelamentoConsulta dadosCancelamentoConsulta) {
        if(dadosCancelamentoConsulta.motivoCancelamento() == null) {
            throw new ValidacaoException("Não é possivel cancelar consulta sem informar o motivo!");
        }
    }
}
