/**
 * Program: RobotRoverSubject.java
 * Purpose: Polymorphism for the RoverContext to implement 
            addition, removal, and update of observer
 */

package Assignment2.Observer;

public interface RobotRoverSubject
{
    public void addObserver(RoverObserver o);
    public void removeObserver(RoverObserver o);
    public void roverUpdate();
}
