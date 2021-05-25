/**
 * Program: StateException.java
 * Purpose: Handles all the exceptions thrown by the states
 * @extends Exception
 */

package Assignment2.State;

public class StateException extends Exception
{
    public StateException( String msg ) { super(msg); }

    public StateException( String msg, 
                           Throwable cause ) { super(msg); }
}
