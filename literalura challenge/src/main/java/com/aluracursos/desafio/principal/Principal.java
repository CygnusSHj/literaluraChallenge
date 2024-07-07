package com.aluracursos.desafio.principal;

import com.aluracursos.desafio.model.Datos;
import com.aluracursos.desafio.model.DatosLibros;
import com.aluracursos.desafio.model.DatosLibrosClase;
import com.aluracursos.desafio.repository.DatosLibrosClaseRepository;
import com.aluracursos.desafio.service.ConsumoAPI;
import com.aluracursos.desafio.service.ConvierteDatos;
import com.aluracursos.desafio.service.librosService;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);
    private DatosLibrosClaseRepository Repositorio;
    public Principal(DatosLibrosClaseRepository repositorio) {
        this.Repositorio = repositorio;
    }

    public void muestraElMenu(){

        int op = 0;

        while(op != 9){

            System.out.println("menu de opciones, digite el numero de la opcion que prefiera");
            System.out.println("opcion 1: buscar un libro por nombre");
            System.out.println("opcion 2: listar libros registrados");
            System.out.println("opcion 3: listar autores registrados");
            System.out.println("opcion 4: listar autores vivos en un anio determinado");
            System.out.println("opcion 5: listar libros por idioma");
            System.out.println("opcion 9: Salir");

            op = teclado.nextInt();

            switch (op){
                case 1: bucarLibroPorNombre();
                break;
                case 2: listarLibros();
                break;
                case 3: listarAutores();
                break;
                case 4: listarAutoresPorFecha();
                break;
                case 5: listarLibrosPorIdioma();
                break;
                case 9: op = 9;
                break;
                default:
                    System.out.println("Opcion no Valida");
            }
            System.out.println("FINALIZO LA EJECUCION , GRACIAS POR SU PREFERENCIA :)");


        }

    }
    public void bucarLibroPorNombre(){
        teclado.nextLine();
        System.out.println("Ingrese el nombre del libro que desea buscar");
        var tituloLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE+"?search=" + tituloLibro.replace(" ","+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();

        if(libroBuscado.isPresent()){
            System.out.println("Libro Encontrado ");
            DatosLibrosClase libro = new DatosLibrosClase(libroBuscado.get());
            Repositorio.save(libro);
            System.out.println(libroBuscado.get());

        }else {
            System.out.println("Libro no encontrado");
        }
        System.out.println("*****************************************");
        System.out.println("*****************************************");
    }
    public void listarLibros(){
        List<DatosLibrosClase> libro= Repositorio.findAll();
        System.out.println("*******************************");
        System.out.println("TODOS LOS LIBROS REGISTRADOS");
        System.out.println("*******************************");
        libro.forEach(System.out::println);
        System.out.println("*****************************************");
        System.out.println("*****************************************");
    }

    public void listarAutores(){
        List<DatosLibrosClase> libro= Repositorio.findAll();
        System.out.println("*******************************");
        System.out.println("TODOS LOS AUTORES REGISTRADOS");
        System.out.println("*******************************");
        libro.stream().forEach(e -> System.out.println(e.getNombre()));
        System.out.println("*****************************************");
        System.out.println("*****************************************");
    }

    public void listarAutoresPorFecha(){

        System.out.println("Ingrese la fecha");
        var fecha = teclado.nextInt();
        List<DatosLibrosClase> libro= Repositorio.findAll();
        System.out.println("*****************************************");
        System.out.println("AUTORES NACIDOS EN UNA FECHA DETERMINADA");
        System.out.println("*****************************************");
        for(var x : libro){
            if(x.getFechaDeNacimiento() > fecha && fecha + 50 >= x.getFechaDeNacimiento()){
                System.out.println(x.getNombre());
            }else if(x.getFechaDeNacimiento() < fecha && x.getFechaDeNacimiento() + 50 >= fecha){
                System.out.println(x.getNombre());
            }
        }
        System.out.println("*****************************************");
        System.out.println("*****************************************");


    }
    public void listarLibrosPorIdioma(){
        teclado.nextLine();
        System.out.println("Ingresa el idioma");
        System.out.println("Opciones");
        System.out.println("ES - Espaniol");
        System.out.println("EN - Ingles");
        System.out.println("FR - frances");
        System.out.println("PT - Portugues");
        String opcion = teclado.nextLine();
        System.out.println("*****************************************");
        System.out.println("LIBROS DEL IDIOMA SELECCIONADO");
        System.out.println("*****************************************");
        List<DatosLibrosClase> libro= Repositorio.findAll();
        libro.stream()
                .filter(palabra -> palabra.getIdiomas().toUpperCase().contains(opcion.toUpperCase()))
                .forEach(System.out::println);
        System.out.println("*****************************************");
        System.out.println("*****************************************");
    }

}
