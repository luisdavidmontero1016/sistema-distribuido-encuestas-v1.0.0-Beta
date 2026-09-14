package com.jdc.servidorencuestas.modelo;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Encuesta {
    private String id;
    private String titulo;
    private String descripcion;
    private List<Pregunta> preguntas;
    private String tema;

    public Encuesta() {
    }

    public Encuesta(String id, String titulo, String descripcion, String tema, List<Pregunta> preguntas) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tema = tema;
        this.preguntas = preguntas;
    }

    // Getters y Setters
    public String getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescripcion() { return descripcion; }
    public String getTema() { return tema; }
    public List<Pregunta> getPreguntas() { return preguntas; }
}