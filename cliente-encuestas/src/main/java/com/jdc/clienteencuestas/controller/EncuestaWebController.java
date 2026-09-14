package com.jdc.clienteencuestas.controller;

import com.jdc.clienteencuestas.service.EncuestaClienteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class EncuestaWebController {

    private final EncuestaClienteService clienteService;

    public EncuestaWebController(EncuestaClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/")
    public String menu(Model model, HttpSession session) {
        Set<String> respondidas = (Set<String>) session.getAttribute("respondidas");
        if (respondidas == null) respondidas = new HashSet<>();
        model.addAttribute("respondidas", respondidas);
        model.addAttribute("encuestas", clienteService.obtenerEncuestas());
        return "menu";
    }

    @GetMapping("/encuesta/{id}")
    public String mostrarEncuesta(@PathVariable String id, Model model, HttpSession session) {
        Set<String> respondidas = (Set<String>) session.getAttribute("respondidas");
        if (respondidas != null && respondidas.contains(id)) return "redirect:/";

        for (Map<String, Object> encuesta : clienteService.obtenerEncuestas()) {
            if (encuesta.get("id").equals(id)) {
                model.addAttribute("encuesta", encuesta);
                return "formulario";
            }
        }
        return "redirect:/";
    }

    @PostMapping("/votar/{id}")
    public String votar(
            @PathVariable String id,
            @RequestParam Map<String, String> respuestas,
            HttpSession session) {

        for (Map.Entry<String, String> entry : respuestas.entrySet()) {
            String idPregunta = entry.getKey();
            String opcion = entry.getValue();
            clienteService.enviarVoto(id, idPregunta, opcion);
        }

        Set<String> respondidas = (Set<String>) session.getAttribute("respondidas");
        if (respondidas == null) respondidas = new HashSet<>();
        respondidas.add(id);
        session.setAttribute("respondidas", respondidas);

        return "redirect:/";
    }
}