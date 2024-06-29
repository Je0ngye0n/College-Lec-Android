package com.example.happyChristmas;

public class ItemDeco {
    String name;
    int image;

    public ItemDeco(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName(){
        return name;
    }

    public void setName(){
        this.name = name;
    }

    public int getImage(){
        return image;
    }

    public void setImage(){
        this.image = image;
    }
}
