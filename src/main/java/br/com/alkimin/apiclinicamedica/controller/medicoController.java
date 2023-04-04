package br.com.alkimin.apiclinicamedica.controller;

import br.com.alkimin.apiclinicamedica.models.Medico;
import br.com.alkimin.apiclinicamedica.models.MedicoEditarRecord;
import br.com.alkimin.apiclinicamedica.models.MedicoListaRecord;
import br.com.alkimin.apiclinicamedica.models.MedicoRecord;
import br.com.alkimin.apiclinicamedica.service.MedicoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/medicos")
@AllArgsConstructor
public class medicoController {

    private MedicoService service;

    @GetMapping
    public ResponseEntity<Page<MedicoListaRecord>> helloMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return ResponseEntity.ok().body(service.listarMedicos(paginacao).map(MedicoListaRecord::new));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> criarMedico(@RequestBody @Valid MedicoRecord medicoRecord) {
        service.salvarMedico(new Medico(medicoRecord));
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> editarMedico(@RequestBody @Valid MedicoEditarRecord medicoEditarRecord) {
        service.editarMedico(medicoEditarRecord);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public void desativarMedico(@PathVariable UUID id) {
        service.desativarMedico(id);
    }
}
