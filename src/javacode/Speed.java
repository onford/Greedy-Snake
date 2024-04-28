package javacode;

import java.util.HashSet;
import java.util.Set;

public class Speed {
    public static final int LOW = 1,MID = 2,HIGH = 3,SUPER = 4;
    public static final Set<Integer> speedSet = new HashSet<>(){{
        add(LOW);add(MID);add(HIGH);add(SUPER);
    }};
    public static void segmentCheck() throws Exception {
        if(speedSet.size() < 4) {
            throw new Exception("Incompatible definition for speed \"LOW\", \"MID\", \"HIGH\" and \"SUPER\". They should be different.");
        }
        if(LOW != 1 || MID != 2 || HIGH !=3 || SUPER != 4){
            throw new Exception("Bad definition for speed. Try LOW = 1, MID = 2, HIGH = 3 and SUPER = 4.");
        }
    }
}
