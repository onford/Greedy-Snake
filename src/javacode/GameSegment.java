package javacode;

import javax.swing.*;
import java.awt.*;

public class GameSegment {
    // 游戏框架的宽度（像素数）
    public static final int frameWidth = 518;
    // 游戏框架的高度（像素数）
    public static final int frameHeight = 540;
    // 游戏界面的背景颜色
    public static final Color backGroundColor = Color.white;
    // 贪吃蛇身体的颜色
    public static final Color snakeBodyColor = Color.green;
    //贪吃蛇头部的颜色
    public static final Color snakeHeadColor = new Color(21, 220, 60, 234);
    // 奖励方块的颜色
    public static final Color awardColor = new Color(69, 235, 252);
    // 惩罚方块的颜色
    public static final Color punishColor = new Color(45, 14, 45);
    // 彩蛋方块的颜色
    public static final Color superColor = new Color(238, 106, 145);
    // 答辩方块的颜色
    public static final Color shitColor = new Color(68, 38, 16, 163);
    // 常规字体的样式（用于显示长度 length 和分数 score）
    public static final Font normalFont = new Font("TimesRoman",Font.PLAIN,16);
    // 暂停字体的样式（用于显示暂停时的 PAUSE 字样）
    public static final Font pauseFont = new Font("TimesRoman",Font.BOLD,50);
    // 结束字体的样式（用于在游戏结算画面显示分数）
    public static final Font endFont = new Font("Arial",Font.BOLD,40);
    // GameOver 结算画面展示的图片
    public static final Image gameOverImage = new ImageIcon(System.getProperty("user.dir") + "/src/images/GameOver.png").getImage();
    // GameOver 结算画面的背景颜色
    public static final Color gameOverGround = Color.black;
    // GameOver 结算时结算背景的起点坐标 (gameOverGroundX,gamOverGroundY) 以及背景的宽度、高度
    public static final int gameOverGroundX = 0,gameOverGroundY = 0,gameOverGroundWidth = 800,gameOverGroundHeight = 800;
    // GameOver 结算时结算图片的起点坐标 (gameOverImageX,gamOverImageY) 以及图片的宽度、高度
    public static final int gameOverImageX = 10,gameOverImageY = 115,gameOverImageWidth = 480,gameOverImageHeight = 270;
    // GameOver 结算时显示最终分数的文字的颜色
    public static final Color finalScoreColor = Color.white;
    // GamOver 结算时最终分数文字的起点坐标 (finalScoreX,finalScoreY)
    public static final int finalScoreX = 50,finalScoreY = 400;
    // 游戏时场景分为 rectWCount * rectHCount 个方块
    public static final int rectWCount = 50,rectHCount = 50;
    // 游戏中所有方块的起始点坐标 (rectBeginX,rectBeginY) （都是 0 的话，由于框架是圆角的，会出现遮挡，为了美观可以自行设置） 以及方块的尺寸 rectSize * rectSize
    public static final int rectBeginX = 2,rectBeginY = 2,rectSize = 10;
    // 游戏中常规字体的颜色（用于显示长度 length 和分数 score）
    public static Color normalColor = Color.black;
    // 用于显示长度的字符串的起点坐标 (lengthStringX,lengthStringY) 以及用于显示分数的字符串的起点坐标 (scoreStringX,scoreStringY)
    public static int lengthStringX = 12,lengthStringY = 16,scoreStringX = 150,scoreStringY = 16;
    // 游戏中暂停字体的颜色（用于显示暂停时的 PAUSE 字样）
    public static Color pauseColor = Color.red;
    // 用于显示暂停的字符串（也即 PAUSE）的起点坐标 (pauseX,pauseY)
    public static int pauseX = 150,pauseY = 250;
    // 游戏时蛇的初始长度 initialLength 以及初始分数 initialScore
    public static int initialLength = 4,initialScore = 2000;
    // 游戏时答辩方块造成单位蛇身的分数损失 shitScoreDec 以及单次答辩袭击所能够损失成为的最低分数 shitScoreLBound
    public static int shitScoreDec = 300,shitScoreLBound = 1000;
    // 游戏时奖励方块给予的分数加成
    public static int awardScoreInc = 500;
    // 游戏时惩罚方块给予的分数损失
    public static int punishScoreDec = 400;
    // 游戏时彩蛋方块给予的分数加成
    public static int superScoreInc = 5000;
    // GameOver 时蛇身闪动，闪动周期的毫秒数。
    public static int gameOverFlashPeriod = 400;
    // GameOver 时蛇身闪动，一共闪动的次数。
    public static int gameOverFlashTimes = 5;
    // GameOver 蛇身闪动后，需要展示游戏结算画面，两者之间时间间隔的毫秒数。
    public static int gameOverSleepPeriod = 1000;
    // 游戏开始时惩罚方块第一次获得生成机会的时间毫秒数
    public static int punishInitPeriod = 18000;
    // 惩罚方块被吃掉后，距离下次生成机会到来的时间毫秒数
    public static int punishSummonPeriod = 27000;
    // 游戏开始时彩蛋方块第一次获得生成机会的时间毫秒数
    public static int superInitPeriod = 18000;
    // 彩蛋方块被吃掉后，距离下次生成机会到来的时间毫秒数
    public static int superSummonPeriod = 54000;
    // 游戏开始时答辩方块第一次获得生成机会的时间毫秒数
    public static int shitInitPeriod = 72000;
}
