/**
 * Launch sparks will be rendered diffrently from other sparks, so it is necessary
 * that they are represented by a different class type.
 * @author Christina Chan
 */
public class LaunchSpark extends Spark
{
	/**
	 * The LaunchSpark constructor.
	 * @param creationTime The absolute time of creation of the spark.
	 * @param initialXPos The initial postion of the spark in the x direction.
	 * @param initialYPos The initial position of the spark in the y direction.
	 * @param initialVX The initial x velocity component of the spark.
	 * @param initialVY The initial y velocity component of the spark.
	 * @param starColour The colour of the spark.
	 */
	public LaunchSpark(double creationTime, double initialXPos,
			double initialYPos, double initialVX, double initialVY,
			String starColour)
	{
		super(creationTime, initialXPos, initialYPos, initialVX, initialVY, starColour);
		this.setLifetime(0.15);
		this.setStartingRadius(0.0005);
	} // end constructor
	
	public String toString()
	{
		double[] position = getPosition();
		return this.getClass() + "\t at " + position[0] + ", " + position[1] + "\t" + this.getColour();
	} // end toString

} // end LaunchSpark
