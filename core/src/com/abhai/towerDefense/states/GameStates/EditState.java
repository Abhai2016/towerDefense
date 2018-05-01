package com.abhai.towerDefense.states.GameStates;

import com.abhai.towerDefense.editor.Brush;
import com.abhai.towerDefense.levels.LevelManager;

public class EditState extends BaseGameState {
    private static Brush brush;

    public EditState() {
        super(LevelManager.CUSTOM_LEVEL);

        currentLevel.loadCustomLevel();
        brush = new Brush();

        gameWorld.createEditButtons();
        gameWorld.setEdit(true);
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