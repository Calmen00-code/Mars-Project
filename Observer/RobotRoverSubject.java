/**
 * Program: RobotRoverSubject.java
 * Purpose: Polymorphism for the RoverContext to implement 
            addition, removal, and update of observer
 */

public interface RobotRoverSubject
{
    public void addObserver(Observer o);
    public void removeObserver(Observer o);
    public void roverUpdate();
}
