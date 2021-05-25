/**
 * Program: RoverState.java
 * Purpose: Allows Polymorphism on each state class idle and not idle
 */

package Assignment2.State;

public interface RoverState
{
    public String startDrive() throws StateException;
    public String stopDrive();
    public String startAnalyse() throws StateException;
}
