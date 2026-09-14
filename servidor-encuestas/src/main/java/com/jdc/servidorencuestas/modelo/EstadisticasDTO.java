package com.jdc.servidorencuestas.modelo;

import java.util.Map;

public class EstadisticasDTO {

    private final int id;
    private String tituloEncuesta;
    private String pregunta;
    private Map<String, Integer> resultados;
    private String tendencia;
    private Map<String, Double> porcentajes;
    private String tema;

    public EstadisticasDTO(int id,
                           String tituloEncuesta,
                           String pregunta,
                           Map<String, Integer> resultados,
                           String tendencia,
                           Map<String, Double> porcentajes,
                           String tema) {
        this.id = id;
        this.tituloEncuesta = tituloEncuesta;
        this.pregunta = pregunta;
        this.resultados = resultados;
        this.tendencia = tendencia;
        this.porcentajes = porcentajes;
        this.tema = tema;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTituloEncuesta() {
        return tituloEncuesta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public Map<String, Integer> getResultados() {
        return resultados;
    }

    public String getTendencia() {
        return tendencia;
    }

    public Map<String, Double> getPorcentajes() {
        return porcentajes;
    }

    public String getTema() {
        return tema;
    }
}
