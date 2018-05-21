package com.abhai.towerDefense.gameObjects.simpleObjects;

public class User {
    private int hp;
    private int money;





    public User() {
        newGame();
    }




    public void newGame() {
        hp = 100;
        money = 100;
    }


    public int getHp() {
        return hp;
    }


    public void setHp(int hp) {
        this.hp = hp;
    }


    public int getMoney() {
        return money;
    }


    public void setMoney(int money) {
        this.money = money;
    }
}
