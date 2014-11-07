/**
 * A base class for all Emitter and Particle objects.
 * This class holds position and velocity data.
 * @author Christina Chan
 */
public class Firework
{
	private double xPosition;						// meters
	private double yPosition;						// meters
	private double xVelocity;						// m/sec
	private double yVelocity;						// m/sec
	
	/**
	 * The Firework constructor.
	 * @param xPos The x position in meters.
	 * @param yPos The y position in meters.
	 * @param xVel The x velocity component in m/sec.
	 * @param yVel The y velocity component in m/sec.
	 */
	public Firework(double xPos, double yPos, double xVel, double yVel)
	{
		xPosition = xPos;
		yPosition = yPos;
		xVelocity = xVel;
		yVelocity = yVel;
	} // end constructor
	
	/**
	 * An accessor for position data.
	 * @return An array for the (x, y) position in meters.
	 */
	public double[] getPosition()
	{
		double[] values = new double[2];
		values[0] = xPosition;
		values[1] = yPosition;
		return values;
	} // end getPosition
	
	/**
	 * An accessor for velocity data.
	 * @return An array for the (vx, vy) velocity data in m/sec.
	 */
	public double[] getVelocity()
	{
		double[] values = new double[2];
		values[0] = xVelocity;
		values[1] = yVelocity;
		return values;
	} // end getVelocity
	
	/**
	 * A mutator for the position data.
	 * @param position An array containing the (x, y) position data in meters.
	 */
	public void setPosition(double[] position)
	{
		xPosition = position[0];
		yPosition = position[1];
	} // end setPosition
	
	/**
	 * A mutator for the velocity data.
	 * @param velocity An array containing the (vx, vy) velocity data in m/sec.
	 */
	public void setVelocity(double[] velocity)
	{
		xVelocity = velocity[0];
		yVelocity = velocity[1];
	} // end setVelocity
	
	/**
	 * Returns a string representation of the position.
	 * @return A string containing the position in meters.
	 */
	public String toString()
	{
	    return "XPosition: " + this.xPosition + "YPosition: " + this.yPosition;
	} // end toString

} // end Firework
