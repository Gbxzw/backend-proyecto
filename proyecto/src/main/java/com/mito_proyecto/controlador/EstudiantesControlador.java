package com.mito_proyecto.controlador;

import com.mito_proyecto.entidad.Estudiante;
import com.mito_proyecto.servicio.ColegioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("v1")
public class EstudiantesControlador {

     @Autowired
    private ColegioServicio colegioServicio;



    @GetMapping("/ordenar")
    public List<Estudiante> listarEstudiantesOrden(){

        List<Estudiante> estudiantes = colegioServicio.getAll();
        return colegioServicio.ordenarEdad(estudiantes);
    }

    @GetMapping("/getCursoEstudiante")
    public Map<String, List<String>> listarCursoYEstudiante(){
        return colegioServicio.getCursoYalumno();
    }


    //endpoint para eliminar estudiante por nombre
    @DeleteMapping("/eliminarPorNombre")
    public ResponseEntity<String> eliminarEstudiantePorNombre(@RequestParam String nombre){
        try{
            colegioServicio.deleteEstudiantePorNombre(nombre);
            return new ResponseEntity<>("estudiante eliminado", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("ERROR AL ELIMINAR",HttpStatus.BAD_REQUEST);
        }
    }

    //Actualizar nombre del estudiante

    @PutMapping("/updateNombreEstudiante/id/{id}")
    public ResponseEntity<String> actulizarNombreEstudiante(@PathVariable Long id , @RequestParam String nombre ){
        try{
            colegioServicio.updateNombreEstudiante(id, nombre);
            return ResponseEntity.ok("nombre actualizado");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error al actualizar el nombre");
        }
    }
    //actualizar nombre del curso
    @PutMapping("/updateNombreCurso/id/{id}")
    public ResponseEntity<String> actulizarNombreCurso(@PathVariable Long id , @RequestParam String nombre ){
        try{
            colegioServicio.updateNombreCurso(id, nombre);
            return ResponseEntity.ok("nombre del curso actualizado");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error al actualizar el nombre del curso");
        }
    }


    //agregar nuevo estudiante
    public ResponseEntity<String> agregarEstudiante(@RequestBody Estudiante estudiante){
        colegioServicio.agregarNuevoEstudiante(estudiante);
        return ResponseEntity.ok("estudiante agregado");
    }

    //agregar nuevo curso
    public ResponseEntity<String> agregarNuevoCurso(@RequestBody Curso curso){
        colegioServicio.agregarNuevoCurso(curso);
        return ResponseEntity.ok("curso agregado");
    }
}
