package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findByActivoTrue(Pageable paginacion);
    @Query(value = """
        SELECT * FROM medicos m
        WHERE m.activo = true
        AND m.especialidad = :especialidad
        AND m.id NOT IN (
            SELECT c.medico_id FROM consultas c
            WHERE c.data = :fecha
        )
        ORDER BY RAND()
        LIMIT 1
        """, nativeQuery = true)
    Medico seleccionarMedicoConEspecialidadEnFecha(@Param("especialidad") Especialidad especialidad, @Param("fecha") LocalDateTime fecha);

    @Query("""
            select m.activo
            from Medico m
            where m.id=:idMedico 
            """)
    Boolean findActivoById(Long idMedico);
}
//Verificando el uso de Especialidad especialidad en lugar de String especialidad compila


