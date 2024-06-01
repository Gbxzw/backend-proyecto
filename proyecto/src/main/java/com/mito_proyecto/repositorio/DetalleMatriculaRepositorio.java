package com.mito_proyecto.repositorio;

import com.mito_proyecto.entidad.DetalleMatricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleMatriculaRepositorio extends JpaRepository<DetalleMatricula,Integer> {
}
