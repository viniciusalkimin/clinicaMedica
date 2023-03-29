package br.com.alkimin.apiclinicamedica.repository;

import br.com.alkimin.apiclinicamedica.models.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, UUID> {
}
