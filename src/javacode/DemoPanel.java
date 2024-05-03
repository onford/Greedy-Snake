package javacode;

import javax.swing.*;
import java.awt.*;

public class DemoPanel extends JPanel {
    private final Demo demo;
    public DemoPanel(Demo demo){
        this.demo = demo;
    }
    public void fillObject(int x,int y,Color color,Graphics g){
        g.setColor(color);
        g.fillRect(GameSegment.rectBeginX + GameSegment.rectSize * x,GameSegment.rectBeginY + GameSegment.rectSize * y,GameSegment.rectSize,GameSegment.rectSize);
    }
    public void cutObject(int x,int y,int direction,Color color,Graphics g){
        g.setColor(color);
        if(direction == Demo.UP)
            g.fillRect(GameSegment.rectBeginX + GameSegment.rectSize * x,GameSegment.rectBeginY + GameSegment.rectSize * (y + 1) - demo.movingPix,GameSegment.rectSize,demo.movingPix);
        else if(direction == Demo.DOWN)
            g.fillRect(GameSegment.rectBeginX + GameSegment.rectSize * x,GameSegment.rectBeginY + GameSegment.rectSize * y,GameSegment.rectSize,demo.movingPix);
        else if(direction == Demo.LEFT)
            g.fillRect(GameSegment.rectBeginX + GameSegment.rectSize * (x + 1) - demo.movingPix,GameSegment.rectBeginY + GameSegment.rectSize * y,demo.movingPix,GameSegment.rectSize);
        else g.fillRect(GameSegment.rectBeginX + GameSegment.rectSize * x,GameSegment.rectBeginY + GameSegment.rectSize * y,demo.movingPix,GameSegment.rectSize);
    }
    public void paint(Graphics g){
        super.paint(g);
        if(demo.gameOverTag){
            g.setColor(GameSegment.gameOverGround);
            g.fillRect(GameSegment.gameOverGroundX, GameSegment.gameOverGroundY, GameSegment.gameOverGroundWidth, GameSegment.gameOverGroundHeight);
            g.drawImage(GameSegment.gameOverImage,GameSegment.gameOverImageX,GameSegment.gameOverImageY, GameSegment.gameOverImageWidth, GameSegment.gameOverImageHeight,null);
            g.setFont(GameSegment.endFont);
            g.setColor(GameSegment.finalScoreColor);
            g.drawString("Your final score: " + demo.score,GameSegment.finalScoreX,GameSegment.finalScoreY);
        }
        else {
            for(int i = 0;i < GameSegment.rectWCount;i++)
                for(int j = 0;j < GameSegment.rectHCount;j++)
                    if(demo.data[i][j] >= Demo.UP && demo.data[i][j] <= Demo.RIGHT)
                        this.fillObject(i,j,GameSegment.snakeBodyColor,g);
                    else if(demo.data[i][j] == GameObject.awardTag)
                        this.fillObject(i,j,GameSegment.awardColor,g);
                    else if(demo.data[i][j] == GameObject.punishTag) {
                        this.fillObject(i, j, GameSegment.punishColor, g);
                    }
            for(Super superObj : this.demo.superList){
                if(superObj.visible() && superObj.exists)
                    this.fillObject(superObj.x,superObj.y,GameSegment.superColor,g);
            }
            if(demo.data[demo.headX][demo.headY] > 0)
                this.fillObject(demo.headX,demo.headY,GameSegment.snakeHeadColor,g);
            for(Shit shitObj : this.demo.shitList){
                if(shitObj.exists)
                    this.fillObject(shitObj.x,shitObj.y,GameSegment.shitColor,g);
            }
            if(demo.movingPix > 0){
                cutObject(demo.tailX,demo.tailY,demo.data[demo.tailX][demo.tailY],GameSegment.backGroundColor,g);
                cutObject(demo.headX,demo.headY,demo.data[demo.headX][demo.headY],GameSegment.snakeBodyColor,g);
                cutObject(demo.headX + Demo.XDiff.get(demo.data[demo.headX][demo.headY]),demo.headY + Demo.YDiff.get(demo.data[demo.headX][demo.headY]),demo.data[demo.headX][demo.headY],GameSegment.snakeHeadColor,g);
            }
            if(!demo.hideString){
                g.setFont(GameSegment.normalFont);
                g.setColor(GameSegment.normalColor);
                g.drawString("Snake Length: "+demo.lengthString,GameSegment.lengthStringX,GameSegment.lengthStringY);
                g.drawString("Current Score: "+demo.scoreString,GameSegment.scoreStringX,GameSegment.scoreStringY);
            }
            if(demo.pauseTag){
                g.setFont(GameSegment.pauseFont);
                g.setColor(GameSegment.pauseColor);
                g.drawString("PAUSE",GameSegment.pauseX,GameSegment.pauseY);
            }
        }
    }
}

