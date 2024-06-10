package med.voll.api.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.direccion.DatosDireccion;

public record DatosActualizarPaciente(@NotNull Long id, String nombre, String documento, @Valid DatosDireccion direccion) {
}
