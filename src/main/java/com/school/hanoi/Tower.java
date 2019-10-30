package com.school.hanoi;

import java.util.ArrayList;

class Tower {
    int index;
    ArrayList<Plate> plates = new ArrayList<Plate>();
    public Tower(int index) {
        this.index = index;
    }
}