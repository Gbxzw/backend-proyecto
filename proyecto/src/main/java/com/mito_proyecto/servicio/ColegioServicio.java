package com.mito_proyecto.servicio;

import com.mito_proyecto.entidad.Curso;
import com.mito_proyecto.entidad.Estudiante;
import com.mito_proyecto.repositorio.CursoRepositorio;
import com.mito_proyecto.repositorio.DetalleMatriculaRepositorio;
import com.mito_proyecto.repositorio.EstudianteRepositorio;
import com.mito_proyecto.repositorio.RegistrarMatriculaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ColegioServicio {

  @Autowired
    private CursoRepositorio cursoRepository;

    @Autowired
    private DetalleMatriculaRepositorio detalleMatriculaRepository;

    @Autowired
    private EstudianteRepositorio estudianteRepository;

    @Autowired
    private RegistrarMatriculaRepositorio registrarMatriculaRepository;



    //agregar nuevo estudiante

public ResponseEntity<String> agregarNuevoEstudiante(Estudiante nuevoEstudiante){
    //existe el estudiante
    List<Estudiante> estudiantes =estudianteRepository.findByName(nuevoEstudiante.getNombres());

    if(estudiantes!= null && !estudiantes.isEmpty()){

        System.out.println("la alcaldia ya existe en la db");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el estudiante ya se encuentra registrado");
    }else{
        estudianteRepository.save(nuevoEstudiante);
        return ResponseEntity.ok("estudiante agregado");

    }

}
//agregar nuevo curso

public ResponseEntity<String> agregarNuevoCurso(Curso nuevoCurso){
    List<Curso> cursos = cursoRepository.findByName(nuevoCurso.getNombre());

    if(cursos!=null && !cursos.isEmpty()){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("curso no encontrado");
    }else{
        cursoRepository.save(nuevoCurso);
        return ResponseEntity.ok("curso agregado");
    }

}

    public List<Estudiante> ordenarEdad(List<Estudiante> estudiantes){
        return estudianteRepository.findAll().stream().sorted((e1,e2) -> Integer.compare(e1.getEdad() , e2.getEdad()))
                .collect(Collectors.toList());
    }

    public List<Estudiante> getAll(){
        return estudianteRepository.findAll();
    }

    public Map<String, List<String>> getCursoYalumno() {
        try {
            List<Curso> cursos = cursoRepository.findAll();

            return cursos.stream()
                    .collect(Collectors.toMap(
                            Curso::getNombre,
                            curso -> curso.getEstudiantes().stream()
                                    .map(estudiante -> estudiante.getNombres() + " " + estudiante.getApellidos())
                                    .collect(Collectors.toList())
                    ));
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyMap();
        }
    }

    //eliminar estudiante por nombre

    public void deleteEstudiantePorNombre(String nombre){
        List<Estudiante> estudiantes =estudianteRepository.findByName(nombre);

        //filtramos los nombres de estudiante
        for(Estudiante estudiante : estudiantes){
            try{
                estudianteRepository.delete(estudiante);
                System.out.println("estudiante elimiando : " + estudiante);
            }catch (Exception e){
                System.out.println("error");
                throw e;
            }
        }

    }

    //actualizar nombre del alumno
    //optional para manejar valores que podrian ser nulos poder usar metodos como isPresent(), orElse
    public Estudiante updateNombreEstudiante(Long id , String nombreNuevo){
        Optional<Estudiante> optionalEstudiante = estudianteRepository.findById(id);

        if(optionalEstudiante.isPresent()){
            Estudiante  estudiante = optionalEstudiante.get();
            estudiante.setNombres(nombreNuevo);

            return estudianteRepository.save(estudiante);
        }else{
            throw new RuntimeException("estudiante no encontrado");
        }
    }

    //actualizar nombre del curso

    public Curso updateNombreCurso (Long id , String nuevoNombre){
            Curso curso = cursoRepository.findById(id).orElse(null);

            if(curso != null){
                curso.setNombre(nuevoNombre);
                return cursoRepository.save(curso);
            }else{
                throw new RuntimeException("estudiante no encontrado con el id : " + id);
            }
    }

}
