package edu.eci.arsw.tablero.dto;

import edu.eci.arsw.tablero.model.Point;

//PARA STOMP
public record DrawEvent(String author, String name, Point point, String color) {}