package javacode;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DemoListener implements KeyListener {
    public final Demo demo;
    public DemoListener(Demo demo){
        this.demo = demo;
    }
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        if(!demo.pauseTag){
            if(KeyEvent.VK_W == e.getKeyCode())
                demo.changeDirection(Demo.UP);
            else if(KeyEvent.VK_S == e.getKeyCode())
                demo.changeDirection(Demo.DOWN);
            else if(KeyEvent.VK_A == e.getKeyCode())
                demo.changeDirection(Demo.LEFT);
            else if(KeyEvent.VK_D == e.getKeyCode())
                demo.changeDirection(Demo.RIGHT);
            else if(KeyEvent.VK_1 == e.getKeyCode())
                demo.speed = Speed.LOW;
            else if(KeyEvent.VK_2 == e.getKeyCode())
                demo.speed = Speed.MID;
            else if(KeyEvent.VK_3 == e.getKeyCode())
                demo.speed = Speed.HIGH;
            else if(KeyEvent.VK_4 == e.getKeyCode())
                demo.speed = Speed.SUPER;
            else if(KeyEvent.VK_H == e.getKeyCode())
                demo.reverseHide();
        }
        if(KeyEvent.VK_E == e.getKeyCode())
            demo.exitTag = true;
        else if(KeyEvent.VK_R == e.getKeyCode())
            demo.replayTag = true;
        else if(KeyEvent.VK_P == e.getKeyCode())
            demo.reversePauseTag();
    }
    @Override
    public void keyReleased(KeyEvent e) {}
}
