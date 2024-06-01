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

}
