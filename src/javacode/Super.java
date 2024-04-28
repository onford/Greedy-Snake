package javacode;

public class Super extends GameObject{
    public Super(Demo demo){
        super(demo,GameSegment.superColor);
        super.tag = GameObject.superTag;
    }
    public boolean visible(){
        return this.remainingTime >= 800 || (this.remainingTime + 60) / 120 % 2 == 1;
    }
    public void renewTime(int renewValue){
        if (this.exists) {
            this.remainingTime = Math.max(this.remainingTime - renewValue, 0);
            if (this.remainingTime == 0) {
                this.exists = false;
                this.demo.data[x][y] = 0;
                this.remainingTime = 5400;
            }
        } else if (this.remainingTime == 0) {
            this.remainingTime = 5400;
            if (Math.random() * 3 < 1)
                this.summon();
        } else this.remainingTime = Math.max(this.remainingTime - renewValue, 0);
    }
    public void summon(){
        while(true){
            x = Demo.randInt(0,50);
            y = Demo.randInt(0,50);
            if(this.demo.data[x][y] == 0) {
                this.demo.data[x][y] = this.tag;
                break;
            }
        }
        this.remainingTime = 3600;
        exists = true;
    }
}
