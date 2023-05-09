package br.com.alkimin.apiclinicamedica.domain.repository;

import br.com.alkimin.apiclinicamedica.domain.models.Especialidade;
import br.com.alkimin.apiclinicamedica.domain.models.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    public Page<Medico> findAllByativoTrue(Pageable paginacao);

    @Query("""
                select m from Medico m
                where
                m.ativo = 1
                and
                m.especialidade = :especialidade
                and
                m.id not in(
                        select c.medico.id from Consulta c
                        where
                        c.data = :data
                )
                order by rand()
                limit 1
                """)
    public Medico buscarMedicoAleatorioPorEspecialidade(Especialidade especialidade, LocalDateTime data);

    @Query("""
            select m.ativo
            from Medico m
            where
            m.id = :id
            """)
    Boolean findAtivoById(Long id);

}
