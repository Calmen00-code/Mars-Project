package Assignment2.API;

public class Sensors
{
    /** Performs a temperature reading and returns the result in °C. */
    public double readTemperature() { return -15.8; }

    /** Performs a visibility reading and returns the result in km. */
    public double readVisibility() { return 7.1; }

    /** Performs a light-level reading, and returns the result in lux (units). */
    public double readLightLevel() { return 13774; }

    /** Takes a photo and returns the binary data making up the image. */
    public byte[] takePhoto() 
    { 
        byte[] data = "Photo".getBytes();
        return data; 
    }
}
