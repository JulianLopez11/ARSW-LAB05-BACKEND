package edu.eci.arsw.tablero.model;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private String author;
    private String name;
    private List<Point> points = new ArrayList<>();

    public Board(String author, String name) {
        this.author = author;
        this.name = name;
    }

    public void addPoint(Point p) {
        points.add(p);
    }

    public void clear(){
        points.clear();
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public List<Point> getPoints() {
        return points;
    }
}