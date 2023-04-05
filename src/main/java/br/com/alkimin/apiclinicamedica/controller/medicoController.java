package br.com.alkimin.apiclinicamedica.controller;

import br.com.alkimin.apiclinicamedica.domain.models.*;
import br.com.alkimin.apiclinicamedica.domain.models.*;
import br.com.alkimin.apiclinicamedica.service.MedicoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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

    @GetMapping("/{id}")
    public ResponseEntity detalharMedico(@PathVariable UUID id) {
        var med = service.detalharMedico(id).get();
        return ResponseEntity.ok().body(new MedicoDetalhar(med));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> criarMedico(@RequestBody @Valid MedicoRecord medicoRecord, UriComponentsBuilder uriBuilder) {
        var med = new Medico(medicoRecord);
        service.salvarMedico(med);
        var uri = uriBuilder.path("/medidos/{id}").buildAndExpand(med.getId()).toUri();
        return ResponseEntity.created(uri).body(new MedicoListaRecord(med));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> editarMedico(@RequestBody @Valid MedicoEditarRecord medicoEditarRecord) {
        service.editarMedico(medicoEditarRecord);
        var med = service.medicoById(medicoEditarRecord.id());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MedicoListaRecord(med.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> desativarMedico(@PathVariable UUID id) {
        service.desativarMedico(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
