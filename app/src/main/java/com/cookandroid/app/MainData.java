package com.cookandroid.app;

public class MainData {

    String name;
    String food;

    public MainData(String name, String food) {

        this.name = name;
        this.food = food;
    }

    public String get_name() {
        return name;
    }

    public void set_name(String iv_name) {
        this.name = name;
    }

    public String get_food() {
        return food;
    }

    public void set_food(String iv_food) {
        this.food = food;
    }
}
