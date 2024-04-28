package javacode;

public class PositiveValueException extends Exception{
    public PositiveValueException(String variable){
        super("The variable \"" + variable + "\" should be a positive value.");
    }
}
