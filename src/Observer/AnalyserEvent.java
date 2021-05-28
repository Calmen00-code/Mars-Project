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

public class AnalyserEvent extends RoverObserver
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
    public String runCommand( String command ) throws StateException
    {
        String msg = "";
        if ( command.equals("S") ) {
            rover.startAnalyse();
            analyser.startAnalysis();
        }
        return msg;
    }
}
