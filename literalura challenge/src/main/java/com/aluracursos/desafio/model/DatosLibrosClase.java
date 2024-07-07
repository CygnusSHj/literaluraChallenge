package com.aluracursos.desafio.model;


import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class DatosLibrosClase {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLibro;  // Id de la entidad
    private String titulo;
    private String nombre  = null;
    private long fechaDeNacimiento = 0;
    private String idiomas = null;
    private Double numeroDeDescargas;

    public DatosLibrosClase() {
    }

    public DatosLibrosClase( DatosLibros datos) {

        this.titulo = datos.titulo();

        if(!datos.autor().isEmpty()){
            this.nombre = datos.autor().get(0).nombre();
            this.fechaDeNacimiento = Long.parseLong(datos.autor().get(0).fechaDeNacimiento());
        }
        if(!datos.idiomas().isEmpty()){
            this.idiomas = String.join(" ", datos.idiomas());
        }
        this.numeroDeDescargas = datos.numeroDeDescargas();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = Long.parseLong(fechaDeNacimiento);
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    @Override
    public String toString() {
        return "DatosLibrosConvertido{" +
                "titulo='" + titulo + '\'' +
                ", autor=" + nombre +
                ", idiomas=" + idiomas +
                ", numeroDeDescargas=" + numeroDeDescargas +
                '}';
    }

    public Long getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Long idLibro) {
        this.idLibro = idLibro;
    }
}
