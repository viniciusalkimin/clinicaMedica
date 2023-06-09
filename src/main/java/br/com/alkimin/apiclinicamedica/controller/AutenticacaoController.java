package br.com.alkimin.apiclinicamedica.controller;

import br.com.alkimin.apiclinicamedica.domain.models.DadosAutenticacao;
import br.com.alkimin.apiclinicamedica.domain.models.DadosToken;
import br.com.alkimin.apiclinicamedica.domain.models.Usuario;
import br.com.alkimin.apiclinicamedica.infra.security.TokenService;
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
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var auth = authenticationManager.authenticate(token);
        var jwtToken = tokenService.gerarToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new DadosToken(jwtToken));
    }


}
