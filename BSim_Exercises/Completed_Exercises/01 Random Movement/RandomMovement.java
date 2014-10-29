

/* ============================================================================== *
 * 1. RANDOM MOVEMENT - Create a number of bacteria that move randomly in the     *
 *                      BSim environment.                                         *
 * ============================================================================== */


// ---------------------------------------------------------------------------------
// Elements from the toolkit that we want to use
// ---------------------------------------------------------------------------------
import java.awt.Color;
import java.util.Vector;
import javax.vecmath.Vector3d;
import processing.core.PGraphics3D;
import bsim.BSim;
import bsim.BSimTicker;
import bsim.draw.BSimP3DDrawer;
import bsim.particle.BSimBacterium;


// ---------------------------------------------------------------------------------
// Definition of our simulation
// ---------------------------------------------------------------------------------
public class RandomMovement {
	public static void main(String[] args) {
		
		
		// -------------------------------------------------------------------------
		// Create our virtual BSim world and then sets its size to 100 x 100 x 100
		// -------------------------------------------------------------------------
		BSim sim = new BSim();	
		sim.setBound(100,100,100);
		
		
		// -------------------------------------------------------------------------
		// Create a list of bacteria and add some new bacteria to it
		// -------------------------------------------------------------------------
		final Vector<BSimBacterium> randomBacteria = new Vector<BSimBacterium>();
		
		randomBacteria.add(new BSimBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new BSimBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new BSimBacterium(sim, new Vector3d(90,90,10)));
		
		
		// -------------------------------------------------------------------------
		// The ticker decides what to update as the simulation runs
		// -------------------------------------------------------------------------
		sim.setTicker(new BSimTicker() {
			@Override
			public void tick() {
				// -----------------------------------------------------------------
				// Loop through all the randomly moving bacteria and update position
				// -----------------------------------------------------------------
				for(BSimBacterium b : randomBacteria) {
					b.action();		
					b.updatePosition();
				}
			}
		});
		
		
		// -------------------------------------------------------------------------
		// Draws the simulation to the screen
		// -------------------------------------------------------------------------
		sim.setDrawer(new BSimP3DDrawer(sim, 800,600) {
			@Override
			public void scene(PGraphics3D p3d) {						
				// -----------------------------------------------------------------
				// Loop through all the randomly moving bacteria and draw
				// -----------------------------------------------------------------
				for(BSimBacterium b : randomBacteria) {
					draw(b, Color.GREEN);
				}			
			}
		});	
		
		
		// -------------------------------------------------------------------------
		// Preview what the simulation looks like
		// -------------------------------------------------------------------------
		sim.preview();
	}
}
