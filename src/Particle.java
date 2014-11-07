/**
 * A base class for all particles.
 * @author Christina Chan
 */
public abstract class Particle extends Firework implements ODESystem
{
	private final double DRAG_COEFF = 0.4;
	
	private double creationTime;				// seconds
	private String colour;
	private double wind;						// m/sec
	private double lifetime;					// seconds
	private double startMass;					// kg
	private double startRadius;					// meters
	private double burnRate;					// kg/sec
	private int systemSize = 2;
	private double density;
	
	private RungeKuttaSolver solver;
	
	/**
	 * The Particle constructor.
	 * 
	 * @param creationTime The absolute time of creation of the particle.
	 * @param initialXPos The initial position of the particle in the x direction.
	 * @param initialYPos The initial position of the particle in the y direction.
	 * @param initialVX The initial x velocity component of the particle.
	 * @param initialVY The initiial y velocity component of the particle.
	 * @param colour The colour of the particle.
	 */
	public Particle(double creationTime, double initialXPos, double initialYPos,
			double initialVX, double initialVY, String colour)
	{
		super(initialXPos, initialYPos, initialVX, initialVY);
		this.creationTime = creationTime;
		this.colour = colour;
		try {
			solver = new RungeKuttaSolver(this);
		} catch (SolverException e) {
			System.out.println(e.getMessage());
			System.out.println("Exiting Particle Manager!");
		}
	} // end constructor
	
	/**
	 * A mutator for the starting mass.
	 * @param startMass The mass of the particle in kg.
	 */
	public void setStartMass(double startMass) { this.startMass = startMass;}
	
	/**
	 * A mutator for the burn rate.
	 * @param burnRate The burn rate in kg/second.
	 */
	public void setBurnRate(double burnRate) { this.burnRate = burnRate;}
	
	public void setSystemSize(int systemSize) { this.systemSize = systemSize;}
		
	public void setDensity(double density) { this.density = density; }
	
	public double getStartRadius(){ return startRadius; }
	
	/**
	 * An accessor for the particle colour.
	 * @return The colour of the particle.
	 */
	public String getColour() { return colour; }
	
	/**
	 * An accessor for the particle creation time.
	 * @return The particle creation time in seconds.
	 */
	public double getCreationTime() { return creationTime; }
	
	private double getVelocityMag(double vx, double vy)
	{
		return Math.sqrt(vx * vx + vy * vy);
	} // end getVelocity
	
	private double getDragForce(double time, double vx, double vy)
	{
		double velocityMag = getVelocityMag(vx, vy);
		double radius = getRadius(time);
		double area = Math.PI * radius * radius;
		return Environment.DENSITY_AIR * velocityMag * velocityMag * area * DRAG_COEFF / 2;
	} // end getDragForce

	private double xDE(double time, double vx, double vy)
	{
		// Use apparent x velocity to calculate drag.
		double vxa = vx - wind;
		double velocityMag = getVelocityMag(vxa, vy);
		double mass = getMass(time);
		double dragForce = getDragForce(time, vxa, vy);
		return -dragForce * vxa / (mass * velocityMag);
	} // end xDE
	
	private double yDE(double time, double vx, double vy)
	{
		// Use apparent x velocity to calculate drag.
		double vxa = vx - wind;
		double velocityMag = getVelocityMag(vxa, vy);
		double mass = getMass(time);
		double dragForce = getDragForce(time, vxa, vy);
		return -Environment.G - dragForce * vy / (mass * velocityMag);
	} // end yDE
	
	// from interface
	public double[] getFunction(double time, double[] values)
	{
		double[] functionVal = new double[systemSize];
		double vX = values[0];
		double vY = values[1];
		functionVal[0] = xDE(time, vX, vY);
		functionVal[1] = yDE(time, vX, vY);
		return functionVal;
	} // end getFunction
	
	/**
	 * An accessor for the particle lifetime.
	 * @return The particle lifetime in seconds.
	 */
	public double getLifetime() { return lifetime; }
	
	/**
	 * Returns the mass at a given time.
	 * @param time The time in seconds.
	 * @return The mass in kg.
	 */
	public double getMass(double time)
	{
		return startMass - time * burnRate;
	} // end getMass
	
	/**
	 * Returns the particle radius at a given time.
	 * @param time The time in seconds.
	 * @return The radius in meters.
	 */
	public double getRadius(double time)
	{
		double volume = getMass(time) / density;
		return Math.pow(3 * volume / (4 * Math.PI), 1.0 / 3.0);
	} // end getRadius
	
	/**
	 * Return the render size of the particle.
	 * @return The render size in pixels.
	 */
	public abstract int getRenderSize();
	
	// from interface
	public int getSystemSize() { return systemSize; }
	
	/**
	 * A mutator for the particle lifetime.
	 * @param lifeT The particle lifetime in seconds.
	 */
	public void setLifetime(double lifeT) { lifetime = lifeT; }
	
	/**
	 * A mutator for the starting radius of a non-Newtonian particle.
	 * @param radius The radius in meters.
	 */
	public void setStartingRadius(double radius) { this.startRadius = radius; }
	
	/**
	 * A string representation of the particle.
	 * @return A string containing the colour and position of the particle.
	 */
	public String toString()
	{
	    return "Colour: " + this.colour + " Position: " + getPosition();
	} // end toString
	
	/**
	 * A mutator that updates the current position of the particle.
	 * @param time The current time in seconds.
	 * @param deltaTime The time interval in seconds.
	 * @param env An instance of the current Environment object is needed to supply
	 * the wind velocity. which is used to calculate the apparent velocity.
	 */
	public void updatePosition(double time, double deltaTime, Environment env)
	{
		wind = env.getWindVelocity();											
		double[] newValues = solver.getNextPoint(time, deltaTime);
		this.setVelocity(newValues);
		double[] oldPosition = getPosition();
		double [] velocity = getVelocity();
		double[] position = {(oldPosition[0] + (velocity[0] + wind) * deltaTime),
				(oldPosition[1] + velocity[1] * deltaTime)};
		this.setPosition(position);
	} // end updatePosition

} // end Particle
