

/* ============================================================================== *
 * 2. CHEMICAL CREATION - Create a stationary bacterium that generates a chemical *
 *                        signal.                                                 *
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
// This is the BSim library that lets us use chemical fields
// ---------------------------------------------------------------------------------
import bsim.BSimChemicalField;


// ---------------------------------------------------------------------------------
// Definition of our simulation
// ---------------------------------------------------------------------------------
public class ChemicalCreation {
	public static void main(String[] args) {
		
		
		// -------------------------------------------------------------------------
		// Create our virtual BSim world and then sets its size to 100 x 100 x 100
		// -------------------------------------------------------------------------
		BSim sim = new BSim();	
		sim.setBound(100,100,100);
		
		
		
		final double decayRate = 0.9;
		final double diffusivity = 890; // (microns)^2/sec
		final BSimChemicalField field = new BSimChemicalField(sim, new int[]{20,20,20}, diffusivity, decayRate);
		
		
		// -------------------------------------------------------------------------
		// Define our new type of bacteria by extending the standard bacterium
		// -------------------------------------------------------------------------
		class MyChemicalCreatorBacterium extends BSimBacterium {
			final double productionRate = 8e9; // molecules/sec
			public MyChemicalCreatorBacterium(BSim sim, Vector3d position) {
				super(sim, position);			
			}
			@Override
			public void action() {
				super.action();							
				field.addQuantity(position, productionRate*sim.getDt());
			}
		}
		
		// -------------------------------------------------------------------------
		// Create a single stationary bacterium and chnage its size
		// -------------------------------------------------------------------------
		final MyChemicalCreatorBacterium chemicalCreatorBacterium = new MyChemicalCreatorBacterium(sim, new Vector3d(50,50,50));
		chemicalCreatorBacterium.setRadius(4);
		
		
		
		// -------------------------------------------------------------------------
		// The ticker decides what to update as the simulation runs
		// -------------------------------------------------------------------------
		sim.setTicker(new BSimTicker() {
			@Override
			public void tick() {
				chemicalCreatorBacterium.action();
				field.update();
			}
		});
		

		// -------------------------------------------------------------------------
		// Draws the simulation to the screen
		// -------------------------------------------------------------------------
		sim.setDrawer(new BSimP3DDrawer(sim, 800,600) {
			@Override
			public void scene(PGraphics3D p3d) {						
				draw(field, Color.RED, (float)(255/12.0e5));
				draw(chemicalCreatorBacterium, Color.RED);
			}
		});	
		
		
		// -------------------------------------------------------------------------
		// Preview what the simulation looks like
		// -------------------------------------------------------------------------
		sim.preview();
	}
}
