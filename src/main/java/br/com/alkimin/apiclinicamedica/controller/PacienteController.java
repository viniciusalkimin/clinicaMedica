package br.com.alkimin.apiclinicamedica.controller;

import br.com.alkimin.apiclinicamedica.domain.models.*;
import br.com.alkimin.apiclinicamedica.service.PacienteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/paciente")
@AllArgsConstructor
public class PacienteController {

    public PacienteService pacienteService;

    @PostMapping
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<?> criarPaciente(@RequestBody @Valid PacienteRecord pacienteRecord, UriComponentsBuilder uriBuilder) {
        var pac = new Paciente(pacienteRecord);
        pacienteService.salvarPaciente(pac);
        var uri = uriBuilder.path("/paciente/{id}").buildAndExpand(pac.getId()).toUri();
        return ResponseEntity.created(uri).body(new PacienteListRecord(pac));
    }
}
