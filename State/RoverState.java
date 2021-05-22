/**
 * Program: RoverState.java
 * Purpose: Allows Polymorphism on each state class idle and not idle
 */

public interface RoverState
{
    public String startDrive();    
    public String stopDrive();
}
