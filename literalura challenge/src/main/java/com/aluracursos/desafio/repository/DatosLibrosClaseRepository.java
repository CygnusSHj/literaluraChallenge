package com.aluracursos.desafio.repository;

import com.aluracursos.desafio.model.DatosLibrosClase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DatosLibrosClaseRepository extends JpaRepository<DatosLibrosClase,Long> {

}
