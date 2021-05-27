/**
 * Program: AnalyserEvent.java
 * Purpose: Handles the operation that involved in the Analyser
 * Implements: RoverObserver.java
 */

package Assignment2.Observer;

import Assignment2.API.SoilAnalyser;
import Assignment2.State.StateException;
import Assignment2.State.RoverContext;
import java.util.Base64;

public class AnalyserEvent implements RoverObserver
{
    private SoilAnalyser analyser;
    private RoverContext rover;

    public AnalyserEvent( SoilAnalyser inAnalyser, 
                    RoverContext inRover ) 
    { 
        analyser = inAnalyser; 
        rover = inRover;
    }

    @Override
    public String runCommand( String command )
    {
        String msg = "";
        if ( command.equals("S") ) {
            try {
                rover.startAnalyse();
                analyser.startAnalysis();

                byte[] data = analyser.pollAnalysis();
                msg += "S " + Base64.getEncoder().encodeToString(data) + "\n";
            } catch (StateException e) {
                msg = e.getMessage();
            }
        }
        return msg;
    }
}
