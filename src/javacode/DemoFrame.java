package javacode;

import javax.swing.*;

public class DemoFrame extends JFrame{
    public DemoFrame(){
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(GameSegment.frameWidth, GameSegment.frameHeight);
        Demo demo = new Demo();
        DemoPanel panel = new DemoPanel(demo);
        this.add(panel);
        this.setVisible(true);
        panel.setBackground(GameSegment.backGroundColor);
        this.addKeyListener(new DemoListener(demo)); // 增加监听器，用来监控玩家的键盘I/O。
        while(!demo.renew(panel)); // 不断地刷新游戏界面直到退出游戏。
    }

    /**
     * 开启一个新的游戏。
     */
    public static void startNewGame(){
        new DemoFrame();
    }
}
