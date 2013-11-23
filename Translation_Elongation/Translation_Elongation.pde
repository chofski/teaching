/**
 * Translation Elongation Models
 * by Thomas E. Gorochowski (23/11/2013)
 * 
 * Simple examples of ribosome movement along an mRNA to illustrate some of 
 * the physical effects that can occur. Includes numerous options to alter
 * aspects of the simulation to show when certain aspects of a model break
 * down e.g., too short initation time can lead to collisions (model does
 * not handle these interactions).
 *
 * CONTROLS:
 * ---------
 *   R     play/pause simulation
 *   C     clears the current simulation data
 *   -/+   decrease/increase initiation time
 *   S     switch between deterministic and stochastic modes
 *   9/0   decrease/increase noise
 *   T     toggle translational speed profile 
 */
import java.util.*;

// The mRNA to be translated
String mRNA = "AAABBABBABBBBABABCCBCBADBCBABBBBABAAABABBDDBBAAABABBCBAABCCBCBABB";
// Default translation times for each codon time
float A_time = 0.1;
float B_time = 0.5;
float C_time = 1.5;
float D_time = 2.5;

// Some global constants
boolean running = false;
boolean stochastic = false;
boolean time_series = false;
float noise_scale = 0.3;
float profile_y_scale = 25.0;
float aa_scale = 11.0;
float x_indent = 40.0;
float y_indent = 250.0;
float rib_width = aa_scale*2.5;
float rib_height = aa_scale*1.5;
float time_scale = 0.6;
float text_h_indent = 10.0;
float text_space = 30.0;
float profile_indent = 1.5*y_indent;

// Containers for the simulation objects
Initiator initiator = new Initiator(30.0);
Vector ribosomes = new Vector();
Vector profiled = new Vector();

/**
 * Set up the simulation window.
 */
void setup () {
  size(800, 530);
  frameRate(24);
} 

/**
 * Draws the screen.
 */
void draw () { 
  // Update everything
  if (running) {
    initiator.update(time_scale);
    for (Object o : ribosomes) {
      Ribosome r = (Ribosome)o;
      r.update(time_scale);
    }
    cleanUp();
  }
  // Draw everything
  background(255);
  drawInfo();
  drawMRNA();
  for (Object o : ribosomes) {
    Ribosome r = (Ribosome)o;
    r.draw();
    if (time_series) {
      r.drawProfile();
    }
  }
  for (Object o : profiled) {
    Ribosome r = (Ribosome)o;
    if (time_series) {
      r.drawProfile();
    }
  }
}

/**
 * Handle any inputs from the user.
 */
void keyPressed () {
  // Start/stop option
  if (key == 'R' || key == 'r') {
    running = !running;
  }
  // Initiation time options
  if (key == '-' || key == '_') {
    initiator.setTime(initiator.time*0.9);
  }
  if (key == '+' || key == '=') {
    initiator.setTime(initiator.time*1.1);
  }
  // Noise related options
  if (key == 's' || key == 'S') {
    stochastic = !stochastic;
  }
  if (key == '9' || key == '(') {
    noise_scale = noise_scale*0.9;
  }
  if (key == '0' || key == ')') {
    noise_scale = noise_scale*1.1;
  }
  // Start/stop option
  if (key == 'T' || key == 't') {
    time_series = !time_series;
  }
  // Clear profiles
  if (key == 'C' || key == 'c') {
    profiled.clear();
    ribosomes.clear();
    initiator.delay = 1.0;
  }
}

/**
 * Return the translation time for a given codon.
 */
float getCodonTime (int pos) {
  float codonTime = 1.0;
  switch (mRNA.charAt(pos)) {
    case 'A':
      codonTime = A_time;
      break;
    case 'B':
      codonTime = B_time;
      break;
    case 'C':
      codonTime = C_time;
      break;
    case 'D':
      codonTime = D_time;
      break;
  }
  if (stochastic) {
    codonTime = codonTime * (1.0 + ((random(2.0)-1.0)*noise_scale));
    if (codonTime < 0.1) {
      codonTime = 0.1;
    }
    if (codonTime > 5.0) {
      codonTime = 5.0;
    }
  }
  return codonTime;
}

/**
 * Draws the mRNA to the screen.
 */
void drawMRNA () {
  noStroke();
  for (int i=0; i<mRNA.length(); i++) {
    // Set the colour for the codon
    setCodonColour(mRNA.charAt(i));
    ellipse(x_indent+(i*aa_scale), y_indent, aa_scale, aa_scale);
  }
}

/**
 * Sets the current colour to that of a given codon.
 */
void setCodonColour (char codonChar) {
  switch (codonChar) {
      case 'A':
        fill(100,200,15);
        break;
      case 'B':
        fill(200,200,15);
        break;
      case 'C':
        fill(255,160,0);
        break;
      case 'D':
        fill(200,0,0);
        break;
      default:
        fill(0,0,0);
    }
}

/**
 * Draws all the simulation information to the screen.
 */
void drawInfo () {
  noStroke();
  textSize(18);
  fill(0, 0, 0);
  if (running) {
    text("RUNNING", x_indent, text_h_indent+text_space); 
  } else {
    text("PAUSED", x_indent, text_h_indent+text_space); 
  }
  if (stochastic) {
    text("Stochastic (" + str(int(noise_scale*100)) + "% noise)", x_indent, text_h_indent+2.0*text_space);
  } else {
    text("Deterministic", x_indent, text_h_indent+2.0*text_space);
  }
  text("Initiation time: " + str(initiator.time), x_indent, text_h_indent+3.0*text_space);
  // Draw the codon time information
  setCodonColour('A');
  ellipse(x_indent + 300, text_h_indent+text_space-7, 14, 14);
  text(": " + str(A_time), x_indent + 320, text_h_indent+text_space);
  setCodonColour('B');
  ellipse(x_indent + 300, 1.0*text_space+text_h_indent+text_space-7, 14, 14);
  text(": " + str(B_time), x_indent + 320, 1.0*text_space+text_h_indent+text_space);
  setCodonColour('C');
  ellipse(x_indent + 300, 2.0*text_space+text_h_indent+text_space-7, 14, 14);
  text(": " + str(C_time), x_indent + 320, 2.0*text_space+text_h_indent+text_space);
  setCodonColour('D');
  ellipse(x_indent + 300, 3.0*text_space+text_h_indent+text_space-7, 14, 14);
  text(": " + str(D_time), x_indent + 320, 3.0*text_space+text_h_indent+text_space);
  // Label the profile
  if (time_series) {
    fill(0, 0, 0);
    text("Translational speed profile:" , x_indent, profile_indent-30); 
  }
}

/**
 * Clean-up stage required to remove ribosomes that finish translation.
 */
void cleanUp () {
  Vector oldRibosomes = ribosomes;
  ribosomes = new Vector();
  for (Object o : oldRibosomes) {
    Ribosome r = (Ribosome)o;
    if (r.dead == false) {
      ribosomes.add(r); 
    }
  }
  oldRibosomes = null;
}

/**
 * Initiates ribosomes onto the mRNA with a specified delay.
 */
class Initiator {
  public float time = 10.0;
  public float delay = 1.0;
  // Constructor
  public Initiator (float newTime) {
    time = newTime;
    delay = 1.0;
  }
  // Set the time delay between adding ribosomes
  void setTime (float newTime) {
    time = newTime;
    if (newTime < delay) {
      delay = newTime;
    }
  }
  // Update the initator and see if ribosome should be added
  public void update (float ts) {
    // Subtract delay
    delay -= ts;
    // If less that 0 initiate and reset
    if (delay <= 0.0) {
      Ribosome newR = new Ribosome(0);
      ribosomes.add(newR);
      delay = time;
      // Profile everything
      profiled.add(newR);
    }
  } 
}

/**
 * An object to represent features of the ribosome.
 */
class Ribosome {
  public int pos = 0;
  public float delay = 0.0;
  public boolean dead = false;
  public Vector profile = new Vector();
  public float cR = 60.0;
  public float cG = 60.0;
  public float cB = 60.0;
  // Constructor with ribosome at given starting codon
  public Ribosome (int start_pos) {
    pos = start_pos;
    delay = getCodonTime(start_pos);
    // New ribosomes should have randomly allocate colour to see profiles
    cR = 40.0 + random(170.0);
    cG = 40.0 + random(170.0);
    cB = 40.0 + random(170.0);
  }
  // Update ribosome position (if necessary)
  public void update (float ts) {
    // Subtract delay
    delay -= ts;
    if (delay <= 0.0) {
       pos++;
       if (pos >= mRNA.length()) {
         dead = true;
       }
       else {
         delay = getCodonTime(pos);
         profile.add(new Float(delay));
       }
    }
  }
  // Draw the ribosome on the screen
  public void draw () {
    float aa_pos = x_indent+(pos*aa_scale);
    noStroke();
    fill(cR,cG,cB);
    // Large subunit
    quad(aa_pos-rib_width, y_indent-aa_scale*0.5,
         aa_pos+rib_width, y_indent-aa_scale*0.5, 
         aa_pos+rib_width*0.8, y_indent-aa_scale*0.5-rib_height,
         aa_pos-rib_width*0.8, y_indent-aa_scale*0.5-rib_height);
    // Small subunit
    quad(aa_pos-rib_width, y_indent+aa_scale*0.5,
         aa_pos+rib_width, y_indent+aa_scale*0.5, 
         aa_pos+rib_width*0.8, y_indent+aa_scale*0.5+rib_height*0.5,
         aa_pos-rib_width*0.8, y_indent+aa_scale*0.5+rib_height*0.5);
    // Pointer to AA being translated
    fill(255,255,255);
    triangle(aa_pos, y_indent-aa_scale*0.5, 
             aa_pos-rib_width*0.2, y_indent-aa_scale*0.5-rib_width*0.2, 
             aa_pos+rib_width*0.2, y_indent-aa_scale*0.5-rib_width*0.2);
  }
  // Draw the ribosomes historic speed profile
  public void drawProfile () {
    stroke(cR,cG,cB);
    strokeWeight(1.5);
    float x0 = x_indent+aa_scale;
    float x1 = 0.0;
    for (int i=1; i<profile.size(); i++) {
      x1 = x0 + aa_scale;
      Float y1 = (Float)(profile.elementAt(i-1));
      Float y2 = (Float)(profile.elementAt(i));
      line(x0, profile_indent+(profile_y_scale*y1.floatValue()), 
           x1, profile_indent+(profile_y_scale*y2.floatValue()));
      x0 = x0 + aa_scale;
    }
  }
}

