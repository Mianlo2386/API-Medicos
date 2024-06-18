package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import med.voll.api.domain.medico.MedicoRepository;


public class MedicoActivo implements ValidadorDeConsultas {
    private MedicoRepository medicoRepository;
    public void validar(DatosAgendarConsulta datos){
        if(datos.idMedico() == null){
            return;
        }
        var medicoActivo = medicoRepository.findActivoById(datos.idMedico());
        if (!medicoActivo){
            throw new ValidationException("No se permite agendar consultas con médicos inactivos.");
        }
    }
}
