package br.com.alkimin.apiclinicamedica.controller;

import br.com.alkimin.apiclinicamedica.models.MedicoRecord;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class medicoController {

    @GetMapping
    public String helloMedicos() {
        return "Hello Medicos!";
    }

    public void criarMedico(@RequestBody MedicoRecord medicoRecord) {

    }
}
