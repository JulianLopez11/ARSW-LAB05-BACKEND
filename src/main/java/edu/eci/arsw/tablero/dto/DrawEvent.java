package edu.eci.arsw.tablero.dto;

import edu.eci.arsw.tablero.model.Point;

public class DrawEvent {
    private String author;
    private String name;
    private Point point;

    public DrawEvent() {}

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Point getPoint() { return point; }
    public void setPoint(Point point) { this.point = point; }
}