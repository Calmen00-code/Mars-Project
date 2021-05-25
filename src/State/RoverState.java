/**
 * Program: RoverState.java
 * Purpose: Allows Polymorphism on each state class idle and not idle
 */

package Assignment2.State;

public interface RoverState
{
    public String startDrive();    
    public String stopDrive();
    public void startAnalyse() throws StateException;
}
