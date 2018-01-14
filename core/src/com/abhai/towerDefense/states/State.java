package com.abhai.towerDefense.states;

public abstract class State {
    String state;


    public abstract void update(float delta);
    public abstract void render();
    public abstract void dispose();
}
