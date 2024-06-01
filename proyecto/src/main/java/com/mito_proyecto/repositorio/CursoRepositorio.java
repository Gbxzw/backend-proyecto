package com.mito_proyecto.repositorio;

import com.mito_proyecto.entidad.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface CursoRepositorio extends JpaRepository<Curso,Long> {

}
