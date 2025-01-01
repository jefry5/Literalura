package com.desafio.Literalura.controller;

import com.desafio.Literalura.service.LibroService;

public class LibroController {
    private LibroService servicio;

    public LibroController(){
        servicio = new LibroService();
    }

    public void buscarLibroPorTitulo() {
        try{
            servicio.buscarLibroPorTitulo();
        } catch (Exception e){
            System.out.println("Ocurrio un error inesperado: " + e.getMessage());
        }
    }

    public void listarLibros() {
        try{
            servicio.listarLibros();
        } catch (Exception e){
            System.out.println("Ocurrio un error inesperado: " + e.getMessage());
        }
    }

    public void listarLibrosPorLenguaje() {
        try{
            servicio.listarLibrosPorLenguaje();
        } catch (Exception e){
            System.out.println("Ocurrio un error inesperado: " + e.getMessage());
        }
    }
}
