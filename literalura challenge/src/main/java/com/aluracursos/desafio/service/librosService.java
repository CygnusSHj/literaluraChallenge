package com.aluracursos.desafio.service;

import com.aluracursos.desafio.model.DatosLibrosClase;
import com.aluracursos.desafio.repository.DatosLibrosClaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class librosService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<DatosLibrosClase> obtenerTodosLosLibros() {
        return entityManager.createQuery("SELECT l FROM libros l", DatosLibrosClase.class)
                .getResultList();
    }

    // Otros métodos del servicio según sea necesario
}