package med.voll.api.controller;

import jakarta.validation.Valid;

import med.voll.api.domain.consulta.AgendaDeConsultaService;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import med.voll.api.domain.consulta.DatosDetalleConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultaService service;

//    @Autowired
//    private ConsultaRepository consultaRepository;
//
//    @Autowired
//    private MedicoRepository medicoRepository;
//
//    @Autowired
//    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DatosAgendarConsulta datos) throws ValidacionDeIntegridad {

//       var paciente = pacienteRepository.findActivoById(datos.idPaciente()).get();//revisar
//       var medico = medicoRepository.findActivoById(datos.idMedico()).get();//revisar

        var response = service.agendar(datos);

        return ResponseEntity.ok(response);//revisar
    }
}