package br.com.alkimin.apiclinicamedica.controller;

import br.com.alkimin.apiclinicamedica.models.Medico;
import br.com.alkimin.apiclinicamedica.models.MedicoRecord;
import br.com.alkimin.apiclinicamedica.service.MedicoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
@AllArgsConstructor
public class medicoController {

    private MedicoService service;

    @GetMapping
    public String helloMedicos() {
        return "Hello Medicos!";
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> criarMedico(@RequestBody @Valid MedicoRecord medicoRecord) {
        service.salvarMedico(new Medico(medicoRecord));
        return ResponseEntity.ok().build();
    }
}
