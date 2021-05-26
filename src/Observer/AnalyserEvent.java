/**
 * Program: AnalyserEvent.java
 * Purpose: Handles the operation that involved in the Analyser
 * Implements: RoverObserver.java
 */

package Assignment2.Observer;

import Assignment2.API.SoilAnalyser;
import Assignment2.State.AnalysisState;
import java.util.Base64;

public class AnalyserEvent implements RoverObserver
{
    private SoilAnalyser analyser;
    private AnalysisState analyseState;

    public AnalyserEvent( SoilAnalyser inAnalyser ) { analyser = inAnalyser; }

    @Override
    public String checkCommand( String command )
    {
        String msg = "";
        if ( command.equals("S") ) {
            try {
                analyseState.startAnalyse();
                analyser.startAnalysis();
                msg = "S " + Base64.getEncoder().encodeToString(analyser.pollAnalysis());
            } catch (Exception e) {
                msg = e.getMessage();
            }
        }
        return msg;
    }
}
