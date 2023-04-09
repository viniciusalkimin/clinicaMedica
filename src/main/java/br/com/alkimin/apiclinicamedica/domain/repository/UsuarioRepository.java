package br.com.alkimin.apiclinicamedica.domain.repository;

import br.com.alkimin.apiclinicamedica.domain.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    public UserDetails findByLogin(String login);
}
