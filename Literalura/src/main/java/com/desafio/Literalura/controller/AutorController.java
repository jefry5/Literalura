package com.desafio.Literalura.controller;

import com.desafio.Literalura.service.AutorService;

public class AutorController {
    private AutorService servicio;

    public AutorController(){ this.servicio = new AutorService(); }

    public void listarAutores() {
        try{
            servicio.listarAutores();
        } catch (Exception e){
            System.out.println("Ocurrio un error inesperado: " + e.getMessage());
        }
    }

    public void listarAutoresVivosDuranteAnio() {
        try{
            servicio.listarAutoresVivosDuranteAnio();
        } catch (Exception e){
            System.out.println("Ocurrio un error inesperado: " + e.getMessage());
        }
    }
}
