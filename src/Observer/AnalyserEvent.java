/**
 * Program: AnalyserEvent.java
 * Purpose: Handles the operation that involved in the Analyser
 * Implements: RoverObserver.java
 */

package Assignment2.Observer;

import Assignment2.API.SoilAnalyser;

public class AnalyserEvent implements RoverObserver
{
    private SoilAnalyser analyser;

    public AnalyserEvent( SoilAnalyser inAnalyser ) { analyser = inAnalyser; }

    @Override
    public String checkCommand( String command )
    {
        String msg = "";
        if ( command.equals("S") ) {
            analyser.startAnalysis();
            msg = "S " + Base64.getEncoder().encodeToString(analyser.pollAnalysis());
        }
        return msg;
    }
}
