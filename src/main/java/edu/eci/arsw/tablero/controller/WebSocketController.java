package edu.eci.arsw.tablero.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import edu.eci.arsw.tablero.dto.DrawEvent;
import edu.eci.arsw.tablero.model.Board;
import edu.eci.arsw.tablero.services.BoardService;

@Controller
public class WebSocketController {

    private final SimpMessagingTemplate template;
    private final BoardService services; 

    public WebSocketController(SimpMessagingTemplate template, BoardService services) {
        this.template = template;
        this.services = services;
    }

    @MessageMapping("/draw")
    public void onDraw(DrawEvent evt) {
        try {
            services.addPoint(evt.author(), evt.name(), evt.point().getX(), evt.point().getY(), evt.point().getColor());
            Board updatedBoard = services.getBoard(evt.author(), evt.name());
            template.convertAndSend("/topic/boards." + evt.author() + "." + evt.name(), updatedBoard);
            
        } catch (Exception e) {
            System.err.println("Error en dibujo RT: " + e.getMessage());
        }
    }
}