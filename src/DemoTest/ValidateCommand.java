/**
 * Program: ValidateCommand.java
 * Purpose: Checking the command passed from Earth
 */

package Assignment2.DemoTest;

public class ValidateCommand
{
    public boolean commandIsValid( String command )
    {
        boolean exist = false;
        char mode = command.charAt(0);
        exist = mode == 'D' || mode == 'T' || mode == 'P' ||
                mode == 'E' || mode == 'S';
        return exist;
    }
}
