package javacode;

import java.awt.*;

public abstract class GameObject {
    public static final int awardTag = 5,punishTag = 6,superTag = 7,shitTag = 8;
    protected Color color;
    protected Demo demo;
    protected boolean appeared,exists = false;
    protected int remainingTime,tag,x,y;
    public GameObject(){

    }
    public GameObject(Demo demo,Color color){
        this.demo = demo;
        this.color = color;
    }
    public abstract boolean visible();
    public abstract void renewTime(int renewValue);
    public abstract void summon();
}
