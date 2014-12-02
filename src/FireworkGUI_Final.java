import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class FireworkGUI_Final extends JFrame {
	
	// Constants for angle slider
	static final int ANGLE_MIN = -15;
    static final int ANGLE_MAX = 15;
    static final int ANGLE_INIT = 0;
	
    // Constants for wind speed slider
    static final int WIND_MIN = -20;
    static final int WIND_MAX = 20;
    static final int WIND_INIT = 0;
    
    // Values for wind and launch angle
    private static int wind;
    private static int angle;
    
    // Values for startFireworks
	static ParticleManager manager = null;
	static ArrayList<Particle> fireworks;
	static double time;
	
	// Create timer as an attribute
	Timer timer;
		
    // Constructor function
	public FireworkGUI_Final() {
		super();
		
		// Set up basic window layout
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		setSize(600, 500);
		setTitle("Wow! Look at those fireworks!");
		setLocation(200, 200);
		Font f = new Font("Arial", Font.PLAIN, 16);
        Font smallF = new Font("Arial", Font.PLAIN, 10);
		
		// Define the JPanels and their layouts
        				// for the bottom section of window
		JPanel bottom = new JPanel(new BorderLayout());
		
		JPanel leftBottom = new JPanel();
		leftBottom.setLayout(new BoxLayout(leftBottom, BoxLayout.Y_AXIS));
		
		JPanel centerBottom = new JPanel(new GridLayout(2, 1));
		
		JPanel rightBottom = new JPanel();
		rightBottom.setLayout(new BoxLayout(rightBottom, BoxLayout.Y_AXIS));
		
		DrawingArea drawing = new DrawingArea();
		
		// Create labels for sliders
		JLabel angleLabel = new JLabel("\t\tLaunch Angle in degrees: ");
		JLabel windLabel = new JLabel("\t\tWind Speed in km/h: ");
		angleLabel.setFont(f);
		windLabel.setFont(f);
 
        // Create the sliders
        final JSlider angleSlider = new JSlider(JSlider.HORIZONTAL,
        		ANGLE_MIN, ANGLE_MAX, ANGLE_INIT);
        final JSlider windSlider = new JSlider(JSlider.HORIZONTAL,
        		WIND_MIN, WIND_MAX, WIND_INIT);
        
        angleSlider.setMajorTickSpacing(5);
        angleSlider.setMinorTickSpacing(1);
        angleSlider.setPaintTicks(true);
        angleSlider.setPaintLabels(true);
        
        windSlider.setMajorTickSpacing(10);
        windSlider.setMinorTickSpacing(1);
        windSlider.setPaintTicks(true);
        windSlider.setPaintLabels(true);
        
        angleSlider.setFont(smallF);
        windSlider.setFont(smallF);
        
        // Create listeners for sliders
	    angleSlider.addChangeListener(new ChangeListener() {
	        public void stateChanged(ChangeEvent ce)
	        {
	            if (!angleSlider.getValueIsAdjusting())
	                angle = angleSlider.getValue();
	        }
	    }); // end angleSlider listener
	    
	    windSlider.addChangeListener(new ChangeListener() {
	        public void stateChanged(ChangeEvent ce)
	        {
	            if (!windSlider.getValueIsAdjusting())
	                wind = windSlider.getValue();
	        }
	    }); // end windSlider listener
	    
	    // instantiate timer and create ActionListener
	    timer = new Timer(50, new ActionListener() {
	        public void actionPerformed(ActionEvent evt) {
	        	time+=0.050;
	        	fireworks = manager.getFireworks(time);
	        	repaint();
	        	if(fireworks.size() == 0)
	        		timer.stop();
			}
	    }); // end timer listener
	    
	    // Create the buttons 
		JButton exitButton = new JButton("Exit");
		exitButton.setFont(f);
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}); // end exitButton listener
		
		JButton runButton = new JButton("Start");
		runButton.setFont(f);
		runButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				time = 0;
				timer.start();
				startFireworks();
			}
		}); // end runButton listener
        
		// add components to JPanels
		leftBottom.add(Box.createRigidArea(new Dimension(0, 10)));
        leftBottom.add(angleLabel);
		leftBottom.add(Box.createRigidArea(new Dimension(0, 25)));
		leftBottom.add(windLabel);
		
		centerBottom.add(angleSlider);
		centerBottom.add(windSlider);
       
		rightBottom.add(Box.createRigidArea(new Dimension(0, 10)));
		rightBottom.add(runButton);
		rightBottom.add(Box.createRigidArea(new Dimension(0, 10)));
		rightBottom.add(exitButton);
		
		bottom.add(rightBottom, BorderLayout.EAST);
        bottom.add(centerBottom, BorderLayout.CENTER);
        bottom.add(leftBottom, BorderLayout.WEST);
        
		// add JPanels to frame
		add(bottom, BorderLayout.SOUTH);
		add(drawing, BorderLayout.CENTER);
		
		// enforce minimum size of window
	    setMinimumSize(getSize());
	   
	    pack();
	
	} // end FireworkFrame constructor
	
	// A method to instantiate a ParticleManager called in runButton listener
	private static void startFireworks(){
		try {
			manager = new ParticleManager(wind, angle);
		} catch (EnvironmentException except) {
			System.out.println(except.getMessage());
			return;
		} catch (EmitterException except) {
			System.out.println(except.getMessage());			
			return;
		}
		manager.start(time);
		fireworks = manager.getFireworks(time);
	} // end startFireworks
	
	// A method to translate colour from a string to an object
	private Color translateColour(String colour) {
		Color returnColour = Color.BLACK;
		switch (colour.toLowerCase()) {
			case "blue" :
				returnColour = Color.BLUE;
				break;
			case "green" :
				returnColour = Color.GREEN;
				break;
			case "orange" :
				returnColour = Color.ORANGE;
				break;
			case "red" :
				returnColour = Color.RED;
				break;
			case "yellow" :
				returnColour = Color.YELLOW;
				break;
			case "white" :
				returnColour = Color.WHITE;
				break;
			case "cyan" :
				returnColour = Color.CYAN;
				break;
			case "magenta" :
				returnColour = Color.MAGENTA;
		}
		return returnColour;
	} // end translateColour
	
	// Create an innter class to be added
		private class DrawingArea extends JPanel {
			// override paint method
		    public void paint(Graphics g) {
		        super.paint(g);
				
		        // set size of margin
		        int margin = 10;
		        
		        // get dimensions of component
		        Dimension dim = getSize();
		        
		        // draw the background with margins
		       g.setColor(Color.black);
		       g.fillRect(margin, margin, dim.width - margin * 2,
		        		dim.height - margin * 2);
		       
		        // draw some snowflakes
		        for (int i = 0; i<100; i++) {
		        	// set each star colour to white with random alpha value
					g.setColor(new Color(255, 255, 255, (int) (Math.random() * 100)));
		        	int xPos = (int) (Math.random() * (dim.width - margin*2)) + margin;
					int yPos = (int) (Math.random() * (dim.height - margin*2)) + margin;
					int snowSize = (int) ((Math.random() * 2) + 2);
					g.fillOval(xPos, yPos, snowSize, snowSize);
				}

		        // draw the Launch Tube
		        int tubeHeight = 30;
		        int tubeWidth = 10;
		        g.setColor(Color.cyan);
		        g.fillRect(dim.width/2 - (tubeWidth/2), dim.height - tubeHeight
		        		- 2*margin, tubeWidth, tubeHeight + margin);
		        
		        // draw the fireworks, scaling the positions
		        int startPosX = dim.width/2;
		        int startPosY = dim.height - tubeHeight - 5;
		        if (fireworks != null) {
		        	for (Particle firework : fireworks) {
						double position[] = firework.getPosition();
						int size = firework.getRenderSize();
						int posX = (int) (startPosX + (position[0] * dim.width/26));
						int posY = (int) (startPosY - (position[1] * dim.height/26));
						g.setColor(translateColour(firework.getColour()));
						g.fillOval(posX, posY, size, size);
		        	}
		        }
				
		    } // end paint
		    
		} // end DrawingArea inner class
	
} // end FireworkFrame class

