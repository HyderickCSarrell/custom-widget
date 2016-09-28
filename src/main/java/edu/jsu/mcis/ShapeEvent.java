package edu.jsu.mcis;

public class ShapeEvent {
    private String selected;
    public ShapeEvent() {
        this("Hexagon");
    }
    public ShapeEvent(String selected) {
        this.selected = selected;
    }
    public String returnSelectedShape() { return selected; }
}