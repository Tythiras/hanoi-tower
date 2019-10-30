package com.school.hanoi;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class Main extends PApplet {
    Controller m;

    public void settings() {
        size(1000, 500);
    }
    public void setup() {
        frameRate(50);

        m = new Controller(this, 7);
    }
    public void draw() {
        clear();
        m.update();
        m.draw();
    }

    public static void main(String[] args) {
        PApplet.main("com.school.hanoi.Main");
    }

}