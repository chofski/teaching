
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
public class BSimExercises {
	public static void main(String[] args) {
		
		
		// -------------------------------------------------------------------------
		// Create our virtual BSim world and then sets its size to 100 x 100 x 100
		// -------------------------------------------------------------------------
		BSim sim = new BSim();	
		sim.setBound(100,100,100);
		
		
		
		// -------------------------------------------------------------------------
		// The ticker decides what to update as the simulation runs
		// -------------------------------------------------------------------------
		sim.setTicker(new BSimTicker() {
			@Override
			public void tick() {
				// Do nothing at the moment
			}
		});
		
		
		// -------------------------------------------------------------------------
		// Draws the simulation to the screen
		// -------------------------------------------------------------------------
		sim.setDrawer(new BSimP3DDrawer(sim, 800,600) {
			@Override
			public void scene(PGraphics3D p3d) {						
				// Nothing to draw at the moment
			}
		});	
		
		
		// -------------------------------------------------------------------------
		// Preview what the simulation looks like
		// -------------------------------------------------------------------------
		sim.preview();
	}
}
