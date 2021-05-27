/**
 * Program: AnalyserEvent.java
 * Purpose: Handles the operation that involved in the Analyser
 * Implements: RoverObserver.java
 */

package Assignment2.Observer;

import Assignment2.API.SoilAnalyser;
import Assignment2.State.IdleState;
import java.util.Base64;

public class AnalyserEvent implements RoverObserver
{
    private SoilAnalyser analyser;
    private IdleState idleState;

    public AnalyserEvent( SoilAnalyser inAnalyser, 
                    IdleState inIdleState ) 
    { 
        analyser = inAnalyser; 
        idleState = inIdleState;
    }

    @Override
    public String runCommand( String command )
    {
        String msg = "";
        if ( command.equals("S") ) {
            try {
                idleState.startAnalyse();
                analyser.startAnalysis();

                byte[] data = analyser.pollAnalysis();
                msg += "S " + Base64.getEncoder().encodeToString(data) + "\n";
            } catch (Exception e) {
                System.out.println("EXCEPTION HERE");
                msg = e.getMessage();
            }
        }
        return msg;
    }
}
