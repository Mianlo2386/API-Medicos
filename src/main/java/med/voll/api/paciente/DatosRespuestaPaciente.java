package med.voll.api.paciente;

import med.voll.api.domain.direccion.DatosDireccion;

public record DatosRespuestaPaciente(
        Long id,
        String nombre,
        String email,
        String telefono,
        DatosDireccion direccion,
        String documento) {

}
