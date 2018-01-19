package com.abhai.towerDefense.gameObjects;

import com.abhai.towerDefense.gameWorld.GameWorld;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class Button extends Sprite {
    public static final int BUTTON_WIDTH = 202;
    public static final int BUTTON_HEIGHT = 28;

    private String name;



    public Button(String name, Texture texture, int x, int y, int width, int height) {
        super(texture, 0, 0, width, height);
        setX(x);
        setY(y);
        this.name = name;
    }


    public void runEvent() {
       if (name.equals("Save")) {
           for (int ay = 0; ay < GameWorld.getInstance().getGrid().size(); ay++) {
               for (int ax = 0; ax < GameWorld.getInstance().getGrid().get(0).size(); ax++)
                   System.out.print(GameWorld.getInstance().getGrid().get(ay).get(ax).getState() + ",");
               System.out.println();
           }
       } else if (name.equals("Clear"))
           GameWorld.getInstance().clearGrid();
    }
}
