package javacode;

public class SegementChecker {
    public static void check() throws Exception {
        if(Demo.directionSet.size() < 4 || Demo.directionSet.contains(Demo.NULLDIRECTION)) {
            throw new Exception("Incompatible definition for direction \"UP\", \"DOWN\", \"LEFT\", \"RIGHT\" and \"NULLDIRECTION\". They should be different.");
        }
        Speed.segmentCheck();
        if(GameSegment.initialLength <= 0)
            throw new PositiveValueException("initialLength");
        if(GameSegment.initialLength < 2)
            throw new Exception("The initial length for snake should at least be 2.");
        if(GameSegment.initialLength >= GameSegment.rectWCount - 1)
            throw new Exception("The initial length for snake is too long.");
        if(GameSegment.initialScore <= 0)
            throw new PositiveValueException("initialScore");
        if(GameSegment.rectHCount < 5 || GameSegment.rectWCount < 5)
            throw new Exception("The initial size for the frame should at least be 5 * 5.");
        if(GameSegment.gameOverFlashTimes <= 0)
            throw new PositiveValueException("gameOverFlashTimes");
        if(GameSegment.gameOverFlashPeriod <= 0)
            throw new PositiveValueException("gameOverFlashPeriod");
        if(GameSegment.gameOverSleepPeriod <= 0 )
            throw new PositiveValueException("gameOverSleepPeriod");
        if(GameSegment.rectSize <= 0)
            throw new PositiveValueException("rectSize");
    }
}
