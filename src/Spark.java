/**
 * A spark.
 * @author Christina Chan
 */
public class Spark extends Particle
{
	/**
	 * The Spark constructor. The lifetime of the spark is set to 0.6 seconds
	 * and the render size to 2 pixels.
	 * @param creationTime The absolute time of creation of the spark.
	 * @param initialXPos The initial position of the spark in the x direction.
	 * @param initialYPos The initial position of the spark in the y direction.
	 * @param initialVX The initial x velocity component of the spark.
	 * @param initialVY The lifetime of the spark in seconds.
	 * @param colour The colour of the spark.
	 */
	public Spark(double creationTime, double initialXPos, double initialYPos,
			double initialVX, double initialVY, String colour) {
		super(creationTime, initialXPos, initialYPos, initialVX, initialVY, colour);
		this.setLifetime(0.6);
		this.setStartingRadius(0.0015);
		this.setStartMass(2.0*Math.pow(Math.E, -6));
		
	} // end constructor
	
	/**
	 * Return the render size of the particle.
	 * @return The render size in pixels.
	 */
	public int getRenderSize() { return 2; }
	
	public String toString()
	{
		double[] position = getPosition();
		return this.getClass() + "\t\t at " + position[0] + ", " + position[1] + "\t" + this.getColour();
	} // end toString
	
} // end Spark
