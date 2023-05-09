package br.com.alkimin.apiclinicamedica.controller;

import br.com.alkimin.apiclinicamedica.domain.models.DadosAgendamentoConsulta;
import br.com.alkimin.apiclinicamedica.domain.models.DadosCancelamentoConsulta;
import br.com.alkimin.apiclinicamedica.service.ConsultaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consulta")
@AllArgsConstructor
public class ConsultaController {

    private ConsultaService consultaService;

   @PostMapping("/agendar")
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        consultaService.agendar(dados);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/cancelar")
    public ResponseEntity cancelar(@RequestBody DadosCancelamentoConsulta dados) {
       consultaService.cancelar(dados);
       return ResponseEntity.ok().build();
    }
}
