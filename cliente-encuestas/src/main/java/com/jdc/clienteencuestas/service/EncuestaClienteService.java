package com.jdc.clienteencuestas.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class EncuestaClienteService {
    private final RestTemplate restTemplate;
    //Cambiar la URL con la red de los 3 dispositivos
    private final String baseUrl = "http://localhost:8082/encuesta";

    public EncuestaClienteService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public List<Map<String, Object>> obtenerEncuestas() {
        return List.of(Objects.requireNonNull(
                restTemplate.getForObject(baseUrl + "/todas", Map[].class)));
    }

    public void enviarVoto(String idEncuesta, String idPregunta, String opcion) {
        restTemplate.postForEntity(
                baseUrl + "/votar/" + idEncuesta + "/" + idPregunta,
                opcion,
                String.class);
    }

    public Map<String, Integer> obtenerResultados(String idEncuesta, String idPregunta) {
        return restTemplate.getForObject(
                baseUrl + "/resultados/" + idEncuesta + "/" + idPregunta,
                Map.class);
    }

    public String obtenerTendencia(String idEncuesta, String idPregunta) {
        return restTemplate.getForObject(
                baseUrl + "/tendencia/" + idEncuesta + "/" + idPregunta,
                String.class);
    }
}