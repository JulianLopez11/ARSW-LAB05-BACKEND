package edu.eci.arsw.tablero.model;

public class Point {

    private float x;
    private float y;
    private String color;

    public Point(){}

    public Point(float x, float y, String color){
        this.x = x;
        this.y = y;
        this.color = color;
    }


    public float getX() { 
        return x; 
    }

    public void setX(float x) { 
        this.x = x; 
    }

    public float getY() { 
        return y; 
    }

    public void setY(float y) { 
        this.y = y; 
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}