/**
 * Program: RoverState.java
 * Purpose: Allows Polymorphism on each state class idle and not idle
 */

public interface RoverState
{
    public void startDrive();    
    public void stopDrive();
}
