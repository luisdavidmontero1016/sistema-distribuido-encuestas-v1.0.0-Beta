package com.jdc.servidorencuestas.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdc.servidorencuestas.modelo.Encuesta;
import com.jdc.servidorencuestas.modelo.EstadisticasDTO;
import com.jdc.servidorencuestas.modelo.Pregunta;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Controller
public class EstadisticasWebController {

    private final Map<String, Encuesta> encuestas;
    private final ObjectMapper objectMapper;

    public EstadisticasWebController(EncuestaController encuestaController, ObjectMapper objectMapper) {
        this.encuestas = encuestaController.getEncuestas();
        this.objectMapper = objectMapper;
    }

    @GetMapping("/")
    public String inicio() {
        return "redirect:/estadisticas";
    }
    @GetMapping("/estadisticas")
    public String verEstadisticas(Model model) {
        Map<String, List<EstadisticasDTO>> encuestasPorTema = new LinkedHashMap<>();
        encuestasPorTema.put("Hábitos Digitales", new ArrayList<>());
        encuestasPorTema.put("Tecnología", new ArrayList<>());
        encuestasPorTema.put("Entretenimiento", new ArrayList<>());
        AtomicInteger contadorId = new AtomicInteger(1);
        encuestas.forEach((id, encuesta) -> {
            String tema = obtenerTema(encuesta.getTema());
            for (Pregunta pregunta : encuesta.getPreguntas()) {
                Map<String, Integer> resultados = convertirConteos(pregunta.getConteos());
                String tendencia = obtenerTendencia(pregunta.getConteos());
                Map<String, Double> porcentajes = calcularPorcentajes(resultados);
                EstadisticasDTO dto = new EstadisticasDTO(
                        contadorId.getAndIncrement(),
                        encuesta.getTitulo(),
                        pregunta.getTexto(),
                        resultados,
                        tendencia,
                        porcentajes,
                        tema
                );
                encuestasPorTema.get(tema).add(dto);
            }
        });
        List<EstadisticasDTO> listaCompleta = encuestasPorTema.values()
                .stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        String estadisticasJson = "[]"; // Valor por defecto por si hay error
        try {
            ObjectMapper mapper = new ObjectMapper();
            estadisticasJson = mapper.writeValueAsString(listaCompleta);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(estadisticasJson);
        model.addAttribute("encuestasPorTema", encuestasPorTema);
        model.addAttribute("estadisticasJson", estadisticasJson);
        return "estadisticas";
    }


    private String obtenerTema(String temaCodigo) {
        return switch (temaCodigo) {
            case "contenido" -> "Hábitos Digitales";
            case "tecnologia" -> "Tecnología";
            case "entretenimiento" -> "Entretenimiento";
            default -> "Otros";
        };
    }

    private Map<String, Integer> convertirConteos(Map<String, AtomicInteger> conteos) {
        return conteos.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().get()
                ));
    }

    private String obtenerTendencia(Map<String, AtomicInteger> conteos) {
        return conteos.entrySet().stream()
                .max(Comparator.comparingInt(e -> e.getValue().get()))
                .map(entry -> entry.getKey() + " (" + entry.getValue().get() + " votos)")
                .orElse("Sin votos aún");
    }

    private Map<String, Double> calcularPorcentajes(Map<String, Integer> resultados) {
        int total = resultados.values().stream().mapToInt(i -> i).sum();
        return resultados.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> total > 0 ? Math.round((e.getValue() * 100.0 / total) * 10) / 10.0 : 0
                ));
    }
}
