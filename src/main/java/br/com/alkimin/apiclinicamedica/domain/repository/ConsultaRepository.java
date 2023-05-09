package br.com.alkimin.apiclinicamedica.domain.repository;

import br.com.alkimin.apiclinicamedica.domain.models.Consulta;
import br.com.alkimin.apiclinicamedica.domain.models.MotivoCancelamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    @Transactional
    @Query(value = "UPDATE consultas SET ativa = false, motivocancelamento = :motivoCancelamento WHERE id = :idConsulta", nativeQuery = true)
    void cancelar(Long idConsulta, String motivoCancelamento);

    Boolean existsByPacienteIdAndDataBetween(Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

    Boolean existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(Long idMedico, LocalDateTime data);
}
/*    @Query("""
            UPDATE consultas c
            SET c.motivocancelamento = :motivoCancelamento
            and c.ativa = 1
            where c.id = :idConsulta
            """)*/