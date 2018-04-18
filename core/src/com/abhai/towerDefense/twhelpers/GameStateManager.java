package com.abhai.towerDefense.twhelpers;


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


    public void set(State state) {
        states.pop().dispose();
        states.push(state);
    }


    public void clear() {
        states.clear();
    }


    public void update(float delta) {
        states.peek().update(delta);
    }
}
