package com.mito_proyecto.repositorio;

import com.mito_proyecto.entidad.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstudianteRepositorio extends JpaRepository<Estudiante,Long> {

    @Override
    List<Estudiante> findAll();

    List<Estudiante> ordenarPorEdad(List<Estudiante> estudiantes);

}
