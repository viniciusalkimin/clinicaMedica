package br.com.alkimin.apiclinicamedica.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class medicoController {

    @GetMapping
    public String helloMedicos() {
        return "Hello Medicos!";
    }
}
