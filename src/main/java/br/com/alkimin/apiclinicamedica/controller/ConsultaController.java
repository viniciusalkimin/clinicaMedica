package br.com.alkimin.apiclinicamedica.controller;

import br.com.alkimin.apiclinicamedica.domain.models.Consulta;
import br.com.alkimin.apiclinicamedica.domain.models.DadosAgendamentoConsulta;
import br.com.alkimin.apiclinicamedica.domain.models.DadosCancelamentoConsulta;
import br.com.alkimin.apiclinicamedica.service.ConsultaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/consulta")
@AllArgsConstructor
public class ConsultaController {

    private ConsultaService consultaService;

    @GetMapping
    public ResponseEntity<List<Consulta>> listarConsultas(){
        return ResponseEntity.ok().body(consultaService.listarTodas());
    }

   @PostMapping("/agendar")
   @SecurityRequirement(name = "bearer-key")
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
       var consulta = consultaService.agendar(dados);
       return ResponseEntity.ok().body(consulta);
    }

    @DeleteMapping("/cancelar")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
       consultaService.cancelar(dados);
       return ResponseEntity.noContent().build();
    }
}
