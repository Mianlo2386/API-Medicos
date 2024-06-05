package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository medicoRepository;
   @PostMapping
    public void registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico){
       System.out.println("El request llega correctamente");
       medicoRepository.save(new Medico(datosRegistroMedico));
   }
   @GetMapping
   public Page<DatosListadoMedico> listadoMedicos(@PageableDefault (sort = "nombre") Pageable paginacion){
    return medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedico::new);
   }
   @PutMapping
   @Transactional
    public void actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico){
       Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id());
       medico.actualizarDatos(datosActualizarMedico);
   }
//   DELETE
//   @DeleteMapping("/{id}")
//   @Transactional
//   public void eliminarMedico(@PathVariable Long id){
//       Medico medico = medicoRepository.getReferenceById(id);
//       medicoRepository.delete(medico);
//   }

// DELETE LOGICO o UPDATE
@DeleteMapping("/{id}")
@Transactional
public void eliminarMedico(@PathVariable Long id){
    Medico medico = medicoRepository.getReferenceById(id);
    medico.desactivarMedico();
}

}
