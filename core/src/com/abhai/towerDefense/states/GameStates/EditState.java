package com.abhai.towerDefense.states.GameStates;

import com.abhai.towerDefense.editor.Brush;

public class EditState extends BaseGameState {
    private static Brush brush;

    public EditState() {
        super( 0);
        brush = new Brush();
    }


    public static Brush getBrush() {
        return brush;
    }
    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        brush = null;
        super.dispose();
    }
}