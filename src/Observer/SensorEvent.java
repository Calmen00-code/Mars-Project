/**
 * Program: SensorEvent.java
 * Purpose: Handles all the operations that involved in Sensor
 *          such as reading temperature, visibility, light level,
 *          and taking photo
 * Implements: RoverObserver.java
 */

package Assignment2.Observer;

import Assignment2.API.Sensors;
import java.util.Base64;

public class SensorEvent implements RoverObserver
{
    private Sensors sensor;

    public SensorEvent( Sensors inSensor ) { sensor = inSensor; }

    @Override
    public String checkCommand( String command )
    {
        String msg = "";

        if ( command.charAt(0) == 'E' ) {                   // Generating Report
            msg = "E " + sensor.readTemperature() + " " + 
                  sensor.readVisibility() + " " + sensor.readLightLevel();
        } else if ( command.charAt(0) == 'P' )              // Taking Photo
            msg = "P " + Base64.getEncoder().encodeToString(sensor.takePhoto());

        return msg;
    }
}
