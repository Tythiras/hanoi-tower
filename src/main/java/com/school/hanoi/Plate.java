package com.school.hanoi;

class Plate {
    int movedFrom = -1;
    float size;
    Plate(float size) {
        this.size = size;
    }
    @Override
    public String toString() {
        return "Size: "+this.size;
    }
    boolean moveTo(int newTower) {
        if(newTower==movedFrom) return false;
        newTower = movedFrom;
        return true;
    }
}