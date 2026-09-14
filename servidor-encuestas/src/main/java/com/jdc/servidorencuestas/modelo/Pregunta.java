package com.jdc.servidorencuestas.modelo;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Pregunta {
    private String id;
    private String texto;
    private List<String> opciones;
    private Map<String, AtomicInteger> conteos;

    public Pregunta(String id, String texto, List<String> opciones) {
        this.id = id;
        this.texto = texto;
        this.opciones = opciones;
        this.conteos = new HashMap<>();
        for (String opcion : opciones) {
            conteos.put(opcion, new AtomicInteger(0));
        }
    }

    // Getters y Setters
    public String getId() { return id; }
    public String getTexto() { return texto; }
    public List<String> getOpciones() { return opciones; }
    public Map<String, AtomicInteger> getConteos() { return conteos; }
}