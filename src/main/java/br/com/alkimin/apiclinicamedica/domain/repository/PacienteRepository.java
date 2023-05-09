package br.com.alkimin.apiclinicamedica.domain.repository;

import br.com.alkimin.apiclinicamedica.domain.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository  extends JpaRepository<Paciente, Long> {

    @Query("""
            select p.ativo
            from Paciente p
            where
            p.id = :id
            """)
    Boolean findAtivoById(Long id);


}
