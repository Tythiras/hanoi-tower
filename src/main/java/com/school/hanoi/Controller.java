package com.school.hanoi;

import processing.core.PApplet;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static processing.core.PConstants.CENTER;

class Controller {
    private ArrayList<Tower> towers = new ArrayList<Tower>();
    private PApplet p;
    private int indexToUse = 0;
    private int stepsUsed;
    boolean update;
    Controller(PApplet parent, int plates) {
        this.p = parent;

        //initialize towers
        for (int i = 0; i < 3; i++) {
            towers.add(new Tower(i+1));
        }

        //add plates
        for (int i = plates; i > 0; i--) {
            towers.get(0).plates.add(new Plate(i));
        }

        //printList();
        //recursion(0);
        update = true;
        stepsUsed = 0;
    }

    private void recursion(int currIndex) {
        int nextIndex = currIndex-1<0 ? towers.size() - 1 : currIndex - 1;
        printList();
        if(towers.get(currIndex).plates.size() != 0) {
            //find spots it can move to
            Tower from = towers.get(currIndex);
            Plate toMove = from.plates.get(from.plates.size() - 1);
            for(int i = 0; i < towers.size(); i++) {
                //not current
                if(i!=currIndex) {
                    //plate not coming from there
                    if(toMove.movedFrom!=i) {
                        Tower to = towers.get(i);
                        //new tower is empty or the plate is smaller than the current
                        if(to.plates.size()==0 || toMove.size <= to.plates.get(to.plates.size() - 1).size) {
                            System.out.println("moving: "+currIndex+" - To: "+i);
                            //move plate
                            from.plates.remove(toMove);
                            toMove.movedFrom = currIndex;
                            to.plates.add(toMove);

                            //bad implementation of done detection
                            if(toMove.size==1 && i==towers.size() - 1) {
                                for(int i2 = 0; i2 < towers.size() - 1; i2++) {
                                    if(towers.get(i2).plates.size()!=0) break;
                                    if(i2+1==i) return;
                                }
                            }
                            //start over at same spot
                            //recursion(currIndex);
                            indexToUse = currIndex;
                            update = true;

                            return;
                        }
                    }
                }
            }
        }
        //go to next tower if any of above failed
        //recursion(nextIndex);
        update = true;

        indexToUse = nextIndex;
    }
    private void printList() {
        System.out.println(towers.get(0).plates);
        System.out.println(towers.get(1).plates);
        System.out.println(towers.get(2).plates);
        System.out.println("\n");
    }
    void update() {
        if(update) {
            update = false;
            stepsUsed++;
            recursion(indexToUse);
            System.out.println("Steps: "+stepsUsed);
        }
    }

    void draw() {
        float heightPillar = 400;
        float padding = 50;
        float margin = (p.width - padding * 2) / towers.size();
        float plateHeight = 50;
        for(int i = 0; i < towers.size(); i++) {
            Tower t = towers.get(i);
            float x = padding + margin/2 + i*margin;
            p.rectMode(CENTER);
            p.rect(x, heightPillar, 20, heightPillar);

            for(int i2 = 0; i2 < t.plates.size(); i2++) {
                Plate plate = t.plates.get(i2);
                p.rect(x, heightPillar - i2*plateHeight, 50 * plate.size, plateHeight);
            }
        }
    }
}
