package javacode;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Demo {
    protected Award awardObj;
    protected Punish punishObj;
    protected Super superObj;
    protected Shit shitObj;
    public static final int UP = 1,DOWN = 2,LEFT = 3,RIGHT = 4,NULLDIRECTION = 0;
    public static final Set<Integer> directionSet = new HashSet<>(){{
        add(UP);add(DOWN);add(LEFT);add(RIGHT);
    }};
    public static Map<Integer,Integer> XDiff = new HashMap<>(){{
        put(UP,0);put(DOWN,0);put(LEFT,-1);put(RIGHT,1);
    }}, YDiff = new HashMap<>(){{
        put(UP,-1);put(DOWN,1);put(LEFT,0);put(RIGHT,0);
    }};
    protected int[][] data;
    protected int speed = Speed.LOW,movingPix,stackAction,registerAction;
    protected boolean gameOverTag,exitTag,replayTag,pauseTag,registerState;
    public static int[] decrease={0,1,2,3};
    protected int headX,headY,tailX,tailY,length,score;
    protected String lengthString,scoreString;
    protected boolean directionChanged,hideString;

    public static int randInt(int a,int b){return a + (int)(Math.random() * (b - a));}

    public Demo(){
        this.rePlay();
    }
    /**
     * 初始化各项游戏参数，重新启动一盘游戏
     */
    public void rePlay(){
        this.awardObj = new Award(this);
        this.punishObj = new Punish(this);
        this.superObj = new Super(this);
        this.shitObj = new Shit(this);
        this.replayTag = this.exitTag = this.gameOverTag = this.registerState = false;
        this.speed = Speed.LOW;
        this.stackAction = this.registerAction = NULLDIRECTION;
        length = GameSegment.initialLength;
        score = GameSegment.initialScore;
        lengthString = Integer.toString(length);
        scoreString = Integer.toString(score);
        directionChanged = hideString = false;
        this.punishObj.remainingTime = GameSegment.punishInitPeriod;
        this.superObj.remainingTime = GameSegment.superInitPeriod;
        data = new int [GameSegment.rectWCount][GameSegment.rectHCount];
        for(int i = 1;i <= GameSegment.initialLength;i++)
            data[i][3] = RIGHT;
        awardObj.summon();
        headX = GameSegment.initialLength;
        headY = tailY = 3;
        tailX = 1;
    }
    /**
     * 更新游戏界面。
     * @param panel: 用于游戏场景绘制的画布。
     * @return 是否退出游戏。
     */
    public boolean renew(DemoPanel panel){
        panel.repaint();
        // 是否重来
        if(this.replayTag){
            this.rePlay();
            panel.repaint();
            return false;
        }
        // 是否退出游戏
        if(this.exitTag)
            return true;
        // 游戏结束的结算画面出来，以及暂停字样出来后，在这里退出 renew 不进行后续操作，以实现窗口待机的效果。
        if(this.gameOverTag || this.pauseTag){
            return false;
        }
        // 如果答辩撞到蛇头了，那么游戏结束
        if(this.shitObj.exists)
            this.gameOverTag = this.shitObj.move();
        // 蛇身假移动，只检查走一步是否会 GameOver
        if(!this.gameOverTag){
            this.registerState = true;
            this.gameOverTag = this.move(false);
        }
        // 如果 GameOver 了
        if(this.gameOverTag){
            this.move(true);
            this.speed = Speed.SUPER;
            this.gameOverTag = false;
            // GameOver 蛇身闪动
            for(int i = 1;i <= 2 * GameSegment.gameOverFlashTimes;i++){
                panel.repaint();
                this.gameOverFlash();
                try {
                    Thread.sleep(GameSegment.gameOverFlashPeriod/2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            this.gameOverTag = true;
            try {
                Thread.sleep(GameSegment.gameOverSleepPeriod);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 显示 GameOver 蛇身闪动后结算画面
            panel.repaint();
        }
        // 如果没有 GameOver，继续移动
        else {
            // 这里的循环展示蛇一个个像素移动的过程，提高动画流畅度
            for(movingPix = 1;movingPix <= GameSegment.rectSize;movingPix++){
                panel.repaint();
                try {

                    Thread.sleep(240 / this.speed / GameSegment.rectSize);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // 更新三个方块的剩余时间
                punishObj.renewTime(240 / this.speed / GameSegment.rectSize);
                superObj.renewTime(240 / this.speed / GameSegment.rectSize);
                shitObj.renewTime(240 / this.speed / GameSegment.rectSize);
            }
            movingPix = 0;
            this.move(true);
            this.registerState = false;
            if(this.registerAction != NULLDIRECTION){
                this.changeDirection(registerAction);
                // 用动作堆栈变量中存的动作更新蛇的最新动向：解决连续两次按下方向键却只让蛇改变了一次方向的问题。
                registerAction = stackAction;
                stackAction = NULLDIRECTION;
            }
            panel.repaint();
            // 需要根据当前的移动速度扣除分数
            this.changeScore(Math.max(this.score - Demo.decrease[this.speed - 1], 0));
            // 如果发现奖励方块被吃掉了，重新生成一个
            if (!this.awardObj.exists)
                this.awardObj.summon();
        }
        panel.repaint();
        return false;
    }
    /**
     * 使得隐藏字符串标记翻转。
     */
    public void reverseHide(){
        this.hideString = !this.hideString;
    }
    /**
     * 使得暂停标记翻转。
     */
    public void reversePauseTag(){
        this.pauseTag = !this.pauseTag;
    }

    /**
     * 改变蛇身长度，同步更改显示长度的字符串
     * @param length: 蛇身改变后的长度。
     */
    public void changeLength(int length){
        this.length = length;
        this.lengthString = Integer.toString(length);
    }

    /**
     * 改变当前分数，同步更改显示分数的字符串
     * @param score: 改变后的分数值
     */
    public void changeScore(int score){
        this.score = score;
        this.scoreString = Integer.toString(score);
    }

    /**
     * 改变蛇移动的方向
     * @param direction: 蛇移动的方向
     */
    public void changeDirection(int direction){
        if(this.registerState){
            if(this.registerAction == NULLDIRECTION)
                this.registerAction = direction;
            // 如果方向已经改变，考虑将输入的动作读入栈变量
            else{
                // 栈变量没有被读入过，那么读取（方向改变、栈变量 stackAction 已经读入过动作时，是不能继续读入动作的）
                if(this.stackAction == NULLDIRECTION)
                    this.stackAction = direction;
            }
            return;
        }
        directionChanged = true;
        if(direction == Demo.UP || direction == Demo.DOWN){
            // 当蛇当前是左、右移动的时候，方向才能更改为上、下移动
            if(data[headX][headY] == Demo.LEFT || data[headX][headY] == Demo.RIGHT)
                data[headX][headY] = direction;
        }
        else{
            // 当蛇当前是上、下移动的时候，方向才能更改为左、右移动
            if(data[headX][headY] == Demo.UP || data[headX][headY] == Demo.DOWN)
                data[headX][headY] = direction;
        }
    }
    /**
     * 蛇进行一个方块的前进移动。
     * @param reallyMove: 如果是 true，让蛇往前移动，否则不移动（只利用返回值，不造成实际影响）
     * @return 是否游戏结束
     */
    public boolean move(boolean reallyMove){
        int nowState;
        // 看看是不是遇到答辩袭击了，如果是的话需要损失袭击处到蛇尾的所有长度，并相应地扣除分数值
        if(shitObj.crash && reallyMove) {
            shitObj.crash = false;
            while (tailX != shitObj.x || tailY != shitObj.y) {
                changeLength(length - 1);
                score = Math.max(GameSegment.shitScoreLBound,score - GameSegment.shitScoreDec);
                nowState = data[tailX][tailY];
                data[tailX][tailY] = 0;
                tailX += XDiff.get(nowState);
                tailY += YDiff.get(nowState);
            }
            changeLength(length - 1);
            score = Math.max(GameSegment.shitScoreLBound,score - GameSegment.shitScoreDec);
            nowState = data[tailX][tailY];
            tailX += XDiff.get(nowState);
            tailY += YDiff.get(nowState);
            data[shitObj.x][shitObj.y] = shitObj.tag;
        }
        if(reallyMove)
            directionChanged = false;
        nowState = data[headX][headY];
        headX += XDiff.get(nowState);
        headY += YDiff.get(nowState);
        // 检查游戏是否结束：撞墙、撞答辩、撞到蛇身或者分数耗尽，任何一个都能使游戏结束
        if(headX < 0 || headX >= GameSegment.rectWCount || headY < 0 || headY >= GameSegment.rectHCount || data[headX][headY] > 0 && data[headX][headY] <= RIGHT || score == 0 || headX == shitObj.x && headY == shitObj.y){
            headX -= XDiff.get(nowState);
            headY -= YDiff.get(nowState);
            return true;
        }
        // 吃到了奖励方块
        if(data[headX][headY] == awardObj.tag){
            if (reallyMove){
                data[headX][headY] = nowState;
                awardObj.exists = false;
                changeLength(length + 1);
                changeScore(score + GameSegment.awardScoreInc);
            }
            else{
                headX -= XDiff.get(nowState);
                headY -= YDiff.get(nowState);
            }
            return false;
        }
        // 吃到了惩罚方块
        else if(data[headX][headY] == punishObj.tag){
            int originScore = score;
            changeScore(Math.max(0,score - GameSegment.punishScoreDec));
            if(reallyMove){
                data[headX][headY] = nowState;
                punishObj.exists = false;
                punishObj.remainingTime = GameSegment.punishSummonPeriod;
            }
            else{
                headX -= XDiff.get(nowState);
                headY -= YDiff.get(nowState);
            }
            if(score == 0){
                if(!reallyMove)
                    this.score = originScore;
                return true;
            }
            return false;
        }
        // 吃到了彩蛋方块
        else if(data[headX][headY] == superObj.tag){
            if(reallyMove){
                data[headX][headY] = nowState;
                changeScore(score + GameSegment.superScoreInc);
                superObj.exists = false;
                superObj.remainingTime = GameSegment.superSummonPeriod;
            }
            else {
                headX -= XDiff.get(nowState);
                headY -= YDiff.get(nowState);
            }
            return false;
        }
        // 尾部向前移动，蛇才是完整地移动了
        if(reallyMove){
            data[headX][headY] = nowState;
            nowState = data[tailX][tailY];
            data[tailX][tailY] = 0;
            tailX += XDiff.get(nowState);
            tailY += YDiff.get(nowState);
        }
        // 如果只是假移动，撤回之前蛇头的移动
        else{
            headX -= XDiff.get(nowState);
            headY -= YDiff.get(nowState);
        }
        return false;
    }
    public void gameOverFlash(){
        for(int i = 0;i < GameSegment.rectWCount;i++)
            for(int j = 0;j < GameSegment.rectHCount;j++)
                if(this.data[i][j] >= -4 && this.data[i][j] <= 4)
                    this.data[i][j] = -this.data[i][j];
    }
}