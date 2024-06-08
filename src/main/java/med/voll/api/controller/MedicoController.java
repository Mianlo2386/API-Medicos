package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.direccion.DatosDireccion;
import med.voll.api.medico.*;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository medicoRepository;


    @PostMapping
    public ResponseEntity registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico, UriComponentsBuilder uriComponentsBuilder){
        Medico medico = medicoRepository.save(new Medico(datosRegistroMedico));
        DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(
                medico.getId(),
                medico.getNombre(),
                medico.getEmail(),
                medico.getTelefono(),
                medico.getEspecialidad().toString(),
                new DatosDireccion(
                        medico.getDireccion().getCalle(),
                        medico.getDireccion().getDistrito(),
                        medico.getDireccion().getCiudad(),
                        medico.getDireccion().getNumero(),
                        medico.getDireccion().getComplemento()
                ),
                medico.getDocumento()
        );
        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaMedico);
    }


    @GetMapping
   public ResponseEntity<Page<DatosListadoMedico>> listadoMedicos(@PageableDefault (sort = "nombre") Pageable paginacion){
    return ResponseEntity.ok(medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedico::new)) ;
   }
   @PutMapping
   @Transactional
    public ResponseEntity actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico){
       Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id());
       medico.actualizarDatos(datosActualizarMedico);
       return ResponseEntity.ok(new DatosRespuestaMedico(
               medico.getId(),
               medico.getNombre(),
               medico.getEmail(),
               medico.getTelefono(),
               medico.getEspecialidad().toString(),
               new DatosDireccion(
                       medico.getDireccion().getCalle(),
                       medico.getDireccion().getDistrito(),
                       medico.getDireccion().getCiudad(),
                       medico.getDireccion().getNumero(),
                       medico.getDireccion().getComplemento()
               ),
               medico.getDocumento()
       ));
   }

// DELETE LOGICO o UPDATE
@GetMapping("/{id}")
@Transactional
public ResponseEntity<DatosRespuestaMedico> retornaDatosMedico(@PathVariable Long id) {
    Medico medico = medicoRepository.getReferenceById(id);
    var datosMedico = new DatosRespuestaMedico(
            medico.getId(),
            medico.getNombre(),
            medico.getEmail(),
            medico.getTelefono(),
            medico.getEspecialidad().toString(),
            new DatosDireccion(
                    medico.getDireccion().getCalle(),
                    medico.getDireccion().getDistrito(),
                    medico.getDireccion().getCiudad(),
                    medico.getDireccion().getNumero(),
                    medico.getDireccion().getComplemento()),
            medico.getDocumento()
    );
    return ResponseEntity.ok(datosMedico);
}

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
        return ResponseEntity.noContent().build();
    }

}


//   DELETE COMPLETO
//   @DeleteMapping("/{id}")
//   @Transactional
//   public void eliminarMedico(@PathVariable Long id){
//       Medico medico = medicoRepository.getReferenceById(id);
//       medicoRepository.delete(medico);
//   }