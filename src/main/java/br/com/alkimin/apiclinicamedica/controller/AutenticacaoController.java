package br.com.alkimin.apiclinicamedica.controller;

import br.com.alkimin.apiclinicamedica.domain.models.DadosAtutenticacao;
import br.com.alkimin.apiclinicamedica.domain.models.Usuario;
import br.com.alkimin.apiclinicamedica.infra.security.TokenService;
import br.com.alkimin.apiclinicamedica.service.AutenticacaoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/login")
public class AutenticacaoController {

    private AuthenticationManager authenticationManager;
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAtutenticacao dados) {
        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var auth = authenticationManager.authenticate(token);
        return ResponseEntity.ok(tokenService.gerarToken((Usuario) auth.getPrincipal()));
    }


}
