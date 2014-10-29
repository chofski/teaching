

/* ============================================================================== *
 * 3. SIGNALLING - Bring together parts 1 and 2 so that the randomly moving       *
 *                 bacteria signal when they are near the stationary bacterium.   *
 *                 Use the strength of the chemical field to act as the trigger.  *
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
import bsim.BSimChemicalField;
import bsim.export.BSimMovExporter;


// ---------------------------------------------------------------------------------
// Definition of our simulation
// ---------------------------------------------------------------------------------
public class Extensions {
	public static void main(String[] args) {
		
		
		// -------------------------------------------------------------------------
		// Create our virtual BSim world and then sets its size to 100 x 100 x 100
		// -------------------------------------------------------------------------
		BSim sim = new BSim();	
		sim.setBound(100,100,100);
		
		
		final double threshold = 1e4;  // molecules/(micron)^3
		
		
		final double decayRate = 0.9;
		final double diffusivity = 890; // (microns)^2/sec
		final BSimChemicalField field = new BSimChemicalField(sim, new int[]{20,20,20}, diffusivity, decayRate);
		
		
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
		
		final MyChemicalCreatorBacterium chemicalCreatorBacterium = new MyChemicalCreatorBacterium(sim, new Vector3d(50,50,50));
		chemicalCreatorBacterium.setRadius(4);
		
		
		
		class MySignallingBacterium extends BSimBacterium {
			public boolean activated = false;
			
			public MySignallingBacterium(BSim sim, Vector3d position) {
				super(sim, position);		
			}
			
			@Override
			public void action() {
				super.action();
				
				if(field.getConc(position) > threshold)
					activated = true; 
				else
					activated = false;
			}
		}		
		
		
		
		
		// -------------------------------------------------------------------------
		// Create a list of bacteria and add some new bacteria to it
		// -------------------------------------------------------------------------
		final Vector<MySignallingBacterium> randomBacteria = new Vector<MySignallingBacterium>();
		
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(10,10,90)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(50,50,50)));
		randomBacteria.add(new MySignallingBacterium(sim, new Vector3d(90,90,10)));
		
		
		// -------------------------------------------------------------------------
		// The ticker decides what to updates as the simulation runs
		// -------------------------------------------------------------------------
		sim.setTicker(new BSimTicker() {
			@Override
			public void tick() {
				chemicalCreatorBacterium.action();
				field.update();
				for(BSimBacterium b : randomBacteria) {
					b.action();		
					b.updatePosition();
					
					
				}
			}
		});
		

		// -------------------------------------------------------------------------
		// Draws the simulation to the screen
		// -------------------------------------------------------------------------
		BSimP3DDrawer drawer = new BSimP3DDrawer(sim, 800,600) {
			public float rot = 0.0f;
			@Override
			public void scene(PGraphics3D p3d) {	
				
				p3d.scale(0.35f);
				p3d.rotateY(rot * (float)(Math.PI/2.0));
				p3d.rotateX(rot * (float)(Math.PI/2.0));
				rot += 0.015;
				p3d.translate(-50, -50, -50);
				
				
				// -----------------------------------------------------------------
				// Draw the chemical field
				// -----------------------------------------------------------------
				draw(field, Color.RED, (float)(255/12.0e5));
				
				// -----------------------------------------------------------------
				// Draw the stationary bacterium that generates a chemical signal
				// -----------------------------------------------------------------
				draw(chemicalCreatorBacterium, Color.RED);
				
				// -----------------------------------------------------------------
				// Loop through all the randomly moving bacteria and draw
				// -----------------------------------------------------------------
				for(MySignallingBacterium b : randomBacteria) {
					if (b.activated == true)
						draw(b, Color.BLUE);
					else
						draw(b, Color.GREEN);
				}	
			}
		};	
		sim.setDrawer(drawer);
		
		
		// -------------------------------------------------------------------------
		// Preview what the simulation looks like
		// -------------------------------------------------------------------------
		//sim.preview();
		
		BSimMovExporter movExporter = new BSimMovExporter(sim, drawer, "simulation.mov");	
		movExporter.setDt(0.03);
		sim.addExporter(movExporter);
		sim.setSimulationTime(20);
		sim.export();
	}
}
