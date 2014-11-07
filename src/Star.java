/**
 * This class describes the star from a Roman Candle.
 * @author Christina Chan
 */
public class Star extends Particle
{
	private final double BURN_RATE = 0.0030;	// kg/second
	private final double DENSITY_STAR = 1900;	// kg/m^3
	private final double STARTING_MASS = 0.008;	// kg
	private final int SYSTEM_SIZE = 2;
	
	/**
	 * The Star constructor. The lifetime of the star is
	 * set to (STARTING_MASS/BURN_RATE -0.5) seconds.
	 * @param creationTime The absolute time of creation of the star.
	 * @param initialXPos The initial position of the star in the x direction.
	 * @param initialYPos The initial posistion of the star in the y direction.
	 * @param initialVX The initial x velocity component of the star.
	 * @param initialVY The initial y velocity component of the star.
	 * @param starColour The colour of the star.
	 */
	public Star(double creationTime, double initialXPos, double initialYPos,
			double initialVX, double initialVY, String starColour)
	{
		super(creationTime, initialXPos, initialYPos, initialVX,
				initialVY, starColour);
		this.setLifetime(STARTING_MASS / BURN_RATE -0.5);
		this.setBurnRate(BURN_RATE);
		this.setDensity(DENSITY_STAR);
		this.setStartMass(STARTING_MASS);
		this.setSystemSize(SYSTEM_SIZE);
	} // end constructor

	/**
	 * Return the render size of the particle.
	 * @return The render size in pixels.
	 */
	public int getRenderSize() { return 6; }
	
	/**
	 * A simple string representation of the particle.
	 * @return A string containing the type, colour and position
	 * in meters.
	 */
	public String toString()
	{
		double[] position = getPosition();
		return this.getClass() + "\t\t at " + position[0] + ", " + position[1] + "\t" + this.getColour();
	} // end toString

} // end Star