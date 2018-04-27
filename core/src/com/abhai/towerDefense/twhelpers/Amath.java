package com.abhai.towerDefense.twhelpers;

import com.badlogic.gdx.math.Vector2;

public class Amath {

    public static double angle(float x1, float y1, float x2, float y2) {
        float dx = x2 - x1;
        float dy = y2 - y1;
        return Math.atan2(dy, dx) / Math.PI * 180;
    }


    public static Vector2 asSpeed(double speed, double angle) {
        return new Vector2((float)(speed * Math.cos(angle)), (float)(speed * Math.sin(angle)));
    }


    public static double toRadians(double degrees) {
        return degrees * Math.PI / 180;
    }
}
