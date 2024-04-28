package javacode;

public class GameLauncher{
    /**
     * 贪吃蛇游戏的入口函数。
     * @param args: 命令行参数
     */
    public static void main(String[] args) throws Exception {
        SegementChecker.check();
        DemoFrame.startNewGame();
        System.exit(0);
    }
}