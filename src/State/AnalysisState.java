/**
 * Program: AnalysisState.java
 * Purpose: Handles all the operations when the robot rover
 *          is in analysing states
 * @implements RoverState.java
 */

package Assignment2.State;

public class AnalysisState implements RoverState
{
    private RoverContext rover;

    public AnalysisState( RoverContext inRoverContext )
    {
        rover = inRoverContext;
    }

    @Override
    public String startDrive( double newDistance ) throws StateException
    {
        throw new StateException(
                "! Rover cannot move while performing soil analysis");
    }

    @Override
    public String stopDrive()
    {
        String feedback = "Rover stopped moving";
        rover.setRoverState( rover.getIdleState() );
        return feedback;
    }

    @Override
    public String startAnalyse() throws StateException
    { 
        throw new StateException(
                "! Rover is already performing analysis");
    }
}
