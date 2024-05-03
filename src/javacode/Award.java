package javacode;

public class Award extends GameObject{
    public static int tag = GameObject.awardTag;
    public Award(Demo demo){
        super(demo,GameSegment.awardColor);
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
                demo.data[x][y] = tag;
                break;
            }
        }
    }
}
