package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.DatosRegistroMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import med.voll.api.paciente.DatosRegistroPaciente;
import med.voll.api.paciente.Paciente;
import med.voll.api.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository pacienteRepository;
    @PostMapping
    public void registrarPaciente(@RequestBody @Valid DatosRegistroPaciente datosRegistroPaciente){
        System.out.println("El request llega correctamente");
        pacienteRepository.save(new Paciente(datosRegistroPaciente));
    }
}
