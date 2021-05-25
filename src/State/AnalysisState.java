/**
 * Program: AnalysisState.java
 * Purpose: Handles all the operations when the robot rover
 *          is in analysing states
 * @implements RoverState.java
 */

package Assignment2.State;

public interface AnalysisState implements RoverState
{
    @Override
    public String startDrive() throws StateException
    {
        throw new StateException(
                "Rover cannot move while performing soil analysis");
        return "";
    }
