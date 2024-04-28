package javacode;

public class Punish extends GameObject{
    public Punish(Demo demo) {
        super(demo,GameSegment.punishColor);
        this.tag = GameObject.punishTag;
    }
    public boolean visible(){
        return true;
    }
    public void renewTime(int renewValue){
        if(this.exists){
            this.remainingTime = Math.max(this.remainingTime - renewValue, 0);
            if (this.remainingTime == 0) {
                this.exists = false;
                this.demo.data[x][y] = 0;
                this.remainingTime = 720;
            }
        }
        else if (this.remainingTime == 0) {
            this.remainingTime = 720;
            if (Math.random() * 5 < 4)
                this.summon();
        }
        else this.remainingTime = Math.max(this.remainingTime - renewValue, 0);
    }
    public void summon(){
        if(Math.random() * 5 < 4){
            if(this.demo.data[demo.headX][demo.headY] == Demo.UP){
                x = demo.headX;
                y = demo.headY - Demo.randInt(4,12);
            }
            else if(this.demo.data[demo.headX][demo.headY] == Demo.DOWN){
                x = demo.headX;
                y = demo.headY + Demo.randInt(4,12);
            }
            else if(this.demo.data[demo.headX][demo.headY] == Demo.LEFT){
                x = demo.headX - Demo.randInt(4,12);
                y = demo.headY;
            }
            else{
                x = demo.headX + Demo.randInt(4,12);
                y = demo.headY;
            }
            if(x >= 0 && x < GameSegment.rectWCount && y >= 0 && y < GameSegment.rectHCount && this.demo.data[x][y] == 0){
                this.demo.data[x][y] = this.tag;
                this.remainingTime = 1800;
                exists = true;
            }
        }
        if(!exists){
            while(true){
                x = Demo.randInt(0,50);
                y = Demo.randInt(0,50);
                if(this.demo.data[x][y] == 0){
                    this.demo.data[x][y] = this.tag;
                    break;
                }
            }
            remainingTime = 1800;
        }
        exists = true;
    }
}
