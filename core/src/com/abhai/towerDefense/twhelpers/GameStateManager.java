package com.abhai.towerDefense.twhelpers;


import com.abhai.towerDefense.gameWorld.GameWorld;
import com.abhai.towerDefense.states.State;
import java.util.Stack;

public class GameStateManager {
    private Stack<State> states;



    public GameStateManager() {
        states = new Stack<State>();
    }



    public State peek() {
        return states.peek();
    }


    public void pop() {
        states.pop().dispose();
    }


    public void push(State state) {
        states.push(state);
    }


    public void render() {
        states.peek().render();
    }


    public void clear() {
        states.clear();
    }


    public void update(float delta) {
        if (delta > GameWorld.getInstance().getMaxDeltaTime())
            states.peek().update((float)GameWorld.getInstance().getMaxDeltaTime());
        else
            states.peek().update(delta);
    }
}
