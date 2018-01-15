package com.abhai.towerDefense.states;

public abstract class State {

    public abstract void update(float delta);
    public abstract void render();
    public abstract void dispose();
}
