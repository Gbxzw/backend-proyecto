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
}
