package javacode;

public class Award extends GameObject{
    public Award(Demo demo){
        super(demo,GameSegment.awardColor);
        this.tag = GameObject.awardTag;
    }
    public void renewTime(int renewValue){

    }
    @Override
    public boolean visible() {
        return true;
    }
    public void summon(){
        this.exists = true;
        while(true){
            this.x = Demo.randInt(1,GameSegment.rectWCount - 1);
            this.y = Demo.randInt(1,GameSegment.rectWCount - 1);
            if(demo.data[x][y] == 0){
                demo.data[x][y] = this.tag;
                break;
            }
        }
    }
}
