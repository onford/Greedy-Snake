package javacode;

import java.awt.*;

public class Shit extends GameObject{
    public Shit(Demo demo){
        super(demo,GameSegment.shitColor);
        this.tag = GameObject.shitTag;
    }
    protected boolean crash;
    protected int axis,pixels;
    public boolean visible(){
        return true;
    }

    public boolean move(){
        if(demo.headX == nextX() && demo.headY == nextY())
            return true;
        demo.data[x][y] = 0;
        do{
            x = nextX();
            y = nextY();
        } while(x >= 0 && x < GameSegment.rectWCount && y >= 0 && y < GameSegment.rectHCount && demo.data[x][y] != 0 && !Demo.directionSet.contains(demo.data[x][y]));
        if(x < 0 || x >= GameSegment.rectWCount || y < 0 || y >= GameSegment.rectHCount){
            this.exists = false;
            return false;
        }
        this.crash = !(demo.data[x][y] == 0);
        return false;
    }
    public int nextX(){
        if((this.remainingTime + 240 / demo.speed) / 320 - this.remainingTime / 320 == 1){
            if(axis == Demo.UP || axis == Demo.DOWN)
                return x;
            else
                return x + (axis == Demo.LEFT?-1:1);
        }
        else return x;
    }
    public int nextY(){
        if((this.remainingTime + 240 / demo.speed) / 320 - this.remainingTime / 320 == 1) {
            if (axis == Demo.LEFT || axis == Demo.RIGHT)
                return y;
            else
                return y + (axis == Demo.UP ? -1 : 1);
        }
        else return y;
    }
//    public int nextX(){
//        if(this.remainingTime >= 320){
//            this.remainingTime -= 320;
//            if(axis == Demo.UP || axis == Demo.DOWN)
//                return x;
//            else
//                return x + (axis == Demo.LEFT?-1:1);
//        }
//        else return x;
//    }
//    public int nextY(){
//        if(this.remainingTime >= 320) {
//            this.remainingTime -= 320;
//            if (axis == Demo.LEFT || axis == Demo.RIGHT)
//                return y;
//            else
//                return y + (axis == Demo.UP ? -1 : 1);
//        }
//        else return y;
//    }
    public void renewTime(int renewValue){
        if (this.exists) {
            this.remainingTime += renewValue;
        } else if (this.remainingTime == 36000) {
            this.remainingTime = 30000;
            if (Math.random() * 9 < 8)
                this.summon();
        } else this.remainingTime = Math.min(this.remainingTime + renewValue, 36000);
    }
    public void summon(){
        while(true){
            axis = Demo.randInt(1,5);
            if(axis == Demo.UP || axis == Demo.DOWN){
                x = Demo.randInt(0,GameSegment.rectWCount);
                y = (axis == Demo.DOWN?0:GameSegment.rectWCount - 1);
            }
            else{
                y = Demo.randInt(0,GameSegment.rectHCount);
                x = (axis == Demo.RIGHT?0:GameSegment.rectHCount - 1);
            }
            if(this.demo.data[x][y] == 0){
                this.demo.data[x][y] = this.tag;
                break;
            }
        }
        exists = true;
        this.remainingTime = this.pixels = 0;
    }
//    public void summon(){
//        axis = Demo.randInt(1,5);
//        if(axis == Demo.UP || axis == Demo.DOWN){
//            x = Demo.randInt(0,GameSegment.rectWCount);
//            y = (axis == Demo.DOWN?-1:GameSegment.rectWCount);
//        }
//        else{
//            y = Demo.randInt(0,GameSegment.rectHCount);
//            x = (axis == Demo.RIGHT?-1:GameSegment.rectHCount);
//        }
//        exists = true;
//        this.remainingTime = this.pixels = 0;
//    }
}
