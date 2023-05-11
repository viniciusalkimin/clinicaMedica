package br.com.alkimin.apiclinicamedica.domain.models;

import java.time.LocalDateTime;

public record ConsultaListaRecord(Long id, Long medicoId, Long pacienteId, LocalDateTime data) {
    public ConsultaListaRecord(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData() );
    }
}
