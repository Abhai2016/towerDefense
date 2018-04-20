package com.abhai.towerDefense.twhelpers;

public class Amath {

    public static double angle(float x1, float y1, float x2, float y2) {
        float dx = x2 - x1;
        float dy = y2 - y1;
        return Math.atan2(dy, dx) / Math.PI * 180;
    }

}
