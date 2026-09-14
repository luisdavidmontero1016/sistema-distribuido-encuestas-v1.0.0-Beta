package com.jdc.servidorencuestas.controller;

import com.jdc.servidorencuestas.modelo.Encuesta;
import com.jdc.servidorencuestas.modelo.Pregunta;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/encuesta")
public class EncuestaController {
    private final Map<String, Encuesta> encuestas = new HashMap<>();



    @PostConstruct
    public void init() {
        // Encuestas de Hábitos Digitales (3 encuestas)
        crearEncuestasContenidoDigital();

        // Encuestas de Tecnología (3 encuestas)
        crearEncuestasTecnologia();

        // Encuestas de Entretenimiento (3 encuestas)
        crearEncuestasEntretenimiento();
    }

    private void crearEncuestasContenidoDigital() {
        // Encuesta 1: Consumo de Redes Sociales
        List<Pregunta> preguntasRedesSociales = Arrays.asList(
                new Pregunta("redes1", "¿Qué red social usas con más frecuencia?",
                        List.of("Facebook", "Instagram", "Twitter/X", "TikTok", "LinkedIn")),
                new Pregunta("redes2", "¿Cuántas horas al día pasas en redes sociales?",
                        List.of("Menos de 1 hora", "1-2 horas", "2-4 horas", "4-6 horas", "Más de 6 horas")),
                new Pregunta("redes3", "¿Qué tipo de contenido consumes más en redes?",
                        List.of("Noticias", "Entretenimiento", "Educativo", "Deportes", "Otros")),
                new Pregunta("redes4", "¿Publicas contenido regularmente?",
                        List.of("Sí, diariamente", "Sí, semanalmente", "Ocasionalmente", "Casi nunca", "Nunca")),
                new Pregunta("redes5", "¿Qué dispositivo usas principalmente para redes?",
                        List.of("Smartphone", "Tablet", "Laptop", "PC de escritorio", "Todos por igual"))
        );
        encuestas.put("contenido1", new Encuesta("contenido1", "Consumo de Redes Sociales",
                "Encuesta sobre hábitos en redes sociales", "contenido", preguntasRedesSociales));

        // Encuesta 2: Consumo de Video
        List<Pregunta> preguntasConsumoVideo = Arrays.asList(
                new Pregunta("video1", "¿Qué plataforma usas para ver videos?",
                        List.of("YouTube", "Netflix", "TikTok", "Twitch", "Otras")),
                new Pregunta("video2", "¿Prefieres videos cortos o largos?",
                        List.of("Cortos (menos de 5 min)", "Medianos (5-20 min)", "Largos (+20 min)", "Depende del contenido", "No tengo preferencia")),
                new Pregunta("video3", "¿Qué tipo de videos prefieres?",
                        List.of("Tutoriales", "Entretenimiento", "Noticias", "Educativos", "Otros")),
                new Pregunta("video4", "¿Compartes videos que te gustan?",
                        List.of("Sí, frecuentemente", "Ocasionalmente", "Raramente", "Casi nunca", "Nunca")),
                new Pregunta("video5", "¿Pagas por contenido premium de video?",
                        List.of("Sí, varias plataformas", "Sí, una plataforma", "No, pero usaría gratis", "No me interesa", "Prefiero contenido gratis"))
        );
        encuestas.put("contenido2", new Encuesta("contenido2", "Consumo de Video Online",
                "Encuesta sobre hábitos de visualización de video", "contenido", preguntasConsumoVideo));

        // Encuesta 3: Noticias y Blogs
        List<Pregunta> preguntasNoticias = Arrays.asList(
                new Pregunta("noticias1", "¿Cómo consumes noticias principalmente?",
                        List.of("Redes sociales", "Sitios web", "Apps de noticias", "TV/Radio", "Periódicos impresos")),
                new Pregunta("noticias2", "¿Cuántas fuentes de noticias diferentes usas?",
                        List.of("1-2", "3-5", "6-10", "Más de 10", "No sigo noticias")),
                new Pregunta("noticias3", "¿Qué tipo de noticias prefieres?",
                        List.of("Nacionales", "Internacionales", "Tecnología", "Deportes", "Entretenimiento")),
                new Pregunta("noticias4", "¿Pagas por contenido de noticias?",
                        List.of("Sí, suscripciones", "Sí, artículos individuales", "No, solo contenido gratis", "No consumo noticias", "Usaría si fuera más barato")),
                new Pregunta("noticias5", "¿Qué tan importante es para ti la veracidad?",
                        List.of("Muy importante", "Importante", "Neutral", "Poco importante", "No me importa"))
        );
        encuestas.put("contenido3", new Encuesta("contenido3", "Consumo de Noticias Digitales",
                "Encuesta sobre hábitos de consumo de noticias", "contenido", preguntasNoticias));
    }

    private void crearEncuestasTecnologia() {
        // Encuesta 1: Dispositivos Móviles
        List<Pregunta> preguntasMoviles = Arrays.asList(
                new Pregunta("movil1", "¿Qué marca de smartphone prefieres?",
                        List.of("Apple", "Samsung", "Xiaomi", "Oppo/Realme", "Otra")),
                new Pregunta("movil2", "¿Con qué frecuencia actualizas tu móvil?",
                        List.of("Cada año", "Cada 2 años", "Cada 3-4 años", "Cuando se rompe", "Nunca he cambiado")),
                new Pregunta("movil3", "¿Qué característica valoras más?",
                        List.of("Cámara", "Batería", "Rendimiento", "Diseño", "Precio")),
                new Pregunta("movil4", "¿Usas wearables (relojes/pulseras)?",
                        List.of("Sí, reloj inteligente", "Sí, pulsera fitness", "No pero me interesan", "No me interesan", "No sé qué son")),
                new Pregunta("movil5", "¿Qué sistema operativo prefieres?",
                        List.of("iOS", "Android", "No tengo preferencia", "Otro", "No sé la diferencia"))
        );
        encuestas.put("tecnologia1", new Encuesta("tecnologia1", "Uso de Smartphones",
                "Encuesta sobre preferencias de dispositivos móviles", "tecnologia", preguntasMoviles));

        // Encuesta 2: Computación
        List<Pregunta> preguntasComputacion = Arrays.asList(
                new Pregunta("comp1", "¿Qué tipo de computadora usas?",
                        List.of("Laptop", "PC de escritorio", "Tablet", "Todo en uno", "No uso computadora")),
                new Pregunta("comp2", "¿Con qué frecuencia actualizas tu equipo?",
                        List.of("Cada 1-2 años", "Cada 3-4 años", "Cada 5+ años", "Cuando falla", "Nunca he actualizado")),
                new Pregunta("comp3", "¿Para qué usas principalmente tu computadora?",
                        List.of("Trabajo", "Estudio", "Juegos", "Entretenimiento", "Otros")),
                new Pregunta("comp4", "¿Qué sistema operativo usas?",
                        List.of("Windows", "macOS", "Linux", "Chrome OS", "No sé")),
                new Pregunta("comp5", "¿Cómo calificas tus habilidades técnicas?",
                        List.of("Avanzado", "Intermedio", "Básico", "Principiante", "Ninguna"))
        );
        encuestas.put("tecnologia2", new Encuesta("tecnologia2", "Hábitos de Computación",
                "Encuesta sobre uso de computadoras", "tecnologia", preguntasComputacion));

        // Encuesta 3: Internet y Nube
        List<Pregunta> preguntasInternet = Arrays.asList(
                new Pregunta("net1", "¿Qué tan importante es Internet para ti?",
                        List.of("Esencial", "Muy importante", "Importante", "Poco importante", "No importante")),
                new Pregunta("net2", "¿Qué velocidad de Internet tienes?",
                        List.of("Menos de 10 Mbps", "10-50 Mbps", "50-100 Mbps", "100-500 Mbps", "500+ Mbps")),
                new Pregunta("net3", "¿Qué servicios en la nube usas?",
                        List.of("Google Drive", "Dropbox", "iCloud", "OneDrive", "Ninguno")),
                new Pregunta("net4", "¿Cuánto pagas mensualmente por Internet?",
                        List.of("Menos de $20", "$20-$40", "$40-$60", "$60-$100", "Más de $100")),
                new Pregunta("net5", "¿Qué tan satisfecho estás con tu proveedor?",
                        List.of("Muy satisfecho", "Satisfecho", "Neutral", "Insatisfecho", "Muy insatisfecho"))
        );
        encuestas.put("tecnologia3", new Encuesta("tecnologia3", "Uso de Internet y Nube",
                "Encuesta sobre hábitos de conectividad", "tecnologia", preguntasInternet));
    }

    private void crearEncuestasEntretenimiento() {
        // Encuesta 1: Streaming de Video
        List<Pregunta> preguntasStreaming = Arrays.asList(
                new Pregunta("stream1", "¿Qué servicio de streaming usas?",
                        List.of("Netflix", "Disney+", "Amazon Prime", "HBO Max", "Ninguno")),
                new Pregunta("stream2", "¿Cuánto gastas mensualmente en streaming?",
                        List.of("Nada", "$5-$10", "$10-$20", "$20-$30", "Más de $30")),
                new Pregunta("stream3", "¿Qué tipo de contenido prefieres?",
                        List.of("Películas", "Series", "Documentales", "Anime", "Otros")),
                new Pregunta("stream4", "¿Compartes tu cuenta con otros?",
                        List.of("Sí, con familia", "Sí, con amigos", "No, solo yo", "No tengo cuenta", "No sabría cómo")),
                new Pregunta("stream5", "¿Qué te haría cancelar una suscripción?",
                        List.of("Precio alto", "Contenido pobre", "Mala calidad", "Nada, lo mantendría", "No estoy suscrito"))
        );
        encuestas.put("entretenimiento1", new Encuesta("entretenimiento1", "Streaming de Video",
                "Encuesta sobre servicios de streaming", "entretenimiento", preguntasStreaming));

        // Encuesta 2: Videojuegos
        List<Pregunta> preguntasVideojuegos = Arrays.asList(
                new Pregunta("game1", "¿En qué plataforma juegas más?",
                        List.of("PC", "PlayStation", "Xbox", "Nintendo", "Móvil")),
                new Pregunta("game2", "¿Cuántas horas juegas semanalmente?",
                        List.of("Menos de 5", "5-10", "10-20", "20-40", "Más de 40")),
                new Pregunta("game3", "¿Qué tipo de juegos prefieres?",
                        List.of("Aventura", "Deportes", "Estrategia", "Shooter", "Otros")),
                new Pregunta("game4", "¿Cuánto gastas al año en juegos?",
                        List.of("Nada", "$1-$50", "$50-$100", "$100-$200", "Más de $200")),
                new Pregunta("game5", "¿Juegas online con otros?",
                        List.of("Sí, frecuentemente", "Ocasionalmente", "Solo campaña", "No juego", "Prefiero solo local"))
        );
        encuestas.put("entretenimiento2", new Encuesta("entretenimiento2", "Hábitos de Videojuegos",
                "Encuesta sobre preferencias de gaming", "entretenimiento", preguntasVideojuegos));

        // Encuesta 3: Música y Podcasts
        List<Pregunta> preguntasMusica = Arrays.asList(
                new Pregunta("music1", "¿Cómo escuchas música principalmente?",
                        List.of("Spotify", "YouTube Music", "Apple Music", "Amazon Music", "Otros")),
                new Pregunta("music2", "¿Pagas por servicio de música?",
                        List.of("Sí, premium", "No, uso gratis", "No escucho música", "Compro álbumes", "No sabría cómo")),
                new Pregunta("music3", "¿Qué género musical prefieres?",
                        List.of("Pop", "Rock", "Electrónica", "Hip Hop", "Otros")),
                new Pregunta("music4", "¿Escuchas podcasts?",
                        List.of("Sí, diariamente", "Sí, semanalmente", "Ocasionalmente", "Casi nunca", "Nunca")),
                new Pregunta("music5", "¿Qué temas prefieres en podcasts?",
                        List.of("Noticias", "Entretenimiento", "Educación", "Negocios", "No escucho"))
        );
        encuestas.put("entretenimiento3", new Encuesta("entretenimiento3", "Música y Podcasts",
                "Encuesta sobre hábitos de audio digital", "entretenimiento", preguntasMusica));
    }

    @GetMapping("/todas")
    public Collection<Encuesta> listar() {
        return encuestas.values();
    }

    @PostMapping("/votar/{idEncuesta}/{idPregunta}")
    public ResponseEntity<?> votar(
            @PathVariable String idEncuesta,
            @PathVariable String idPregunta,
            @RequestBody String opcion) {

        Encuesta encuesta = encuestas.get(idEncuesta);
        if (encuesta != null) {
            Optional<Pregunta> pregunta = encuesta.getPreguntas().stream()
                    .filter(p -> p.getId().equals(idPregunta))
                    .findFirst();

            if (pregunta.isPresent() && pregunta.get().getConteos().containsKey(opcion)) {
                pregunta.get().getConteos().get(opcion).incrementAndGet();
                return ResponseEntity.ok("Voto registrado");
            }
        }
        return ResponseEntity.badRequest().body("Encuesta, pregunta u opción inválida");
    }

    @GetMapping("/tendencia/{idEncuesta}/{idPregunta}")
    public String obtenerTendencia(
            @PathVariable String idEncuesta,
            @PathVariable String idPregunta) {

        Encuesta e = encuestas.get(idEncuesta);
        if (e == null) return "Encuesta no encontrada"; 

        Optional<Pregunta> pregunta = e.getPreguntas().stream()
                .filter(p -> p.getId().equals(idPregunta))
                .findFirst();

        if (!pregunta.isPresent()) return "Pregunta no encontrada";

        return pregunta.get().getConteos().entrySet().stream()
                .max(Map.Entry.comparingByValue(Comparator.comparingInt(AtomicInteger::get)))
                .map(max -> "Tendencia actual: " + max.getKey() + " (" + max.getValue().get() + " votos)")
                .orElse("Sin votos aún");
    }

    @GetMapping("/resultados/{idEncuesta}/{idPregunta}")
    public Map<String, Integer> obtenerResultados(
            @PathVariable String idEncuesta,
            @PathVariable String idPregunta) {

        Map<String, Integer> res = new HashMap<>();
        Encuesta e = encuestas.get(idEncuesta);
        if (e != null) {
            e.getPreguntas().stream()
                    .filter(p -> p.getId().equals(idPregunta))
                    .findFirst()
                    .ifPresent(p ->
                            p.getConteos().forEach((k, v) -> res.put(k, v.get()))
                    );
        }
        return res;
    }

    public Map<String, Encuesta> getEncuestas() {
        return this.encuestas;
    }
}