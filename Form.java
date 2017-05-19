import processing.core.PApplet;

/**
 * A Form is a form with one point.
 * A Form manages the settings of every form.
 * A Form draws itself.
 * 
 * @author Philippe Krüttli
 *
 */

public abstract class Form {
	PApplet parent; // The Parent PApplet that we will render ourselves onto
	int fuellFarbe;
	int strichFarbe;
	String name = "";

	boolean istAusgewaehlt = false;

	int dicke;

	Punkt punktA;

	Form(PApplet p) {
		parent = p;
		dicke = 2;
		fuellFarbe = parent.color(100, 100, 100);
		strichFarbe = parent.color(0, 0, 0);
	}

	Form(PApplet p, int newDicke) {
		parent = p;
		dicke = newDicke;
		fuellFarbe = parent.color(100, 100, 100);
		strichFarbe = parent.color(0, 0, 0);
	}
	
	/**
	 * Draws the form which uses this method, using the default values.
	 */
	void initDraw() {
		if (istAusgewaehlt) {
			parent.fill(parent.color(200, 0, 0));
		} else {
			parent.fill(fuellFarbe);
		}
		parent.strokeWeight(dicke);
	}

	
	/**
	 * Sets the color of the form using this method, using the parameter values.
	 * 
	 * @param r:int 'R'-value of RGB-value
	 * @param g:int 'G'-value of RGB-value
	 * @param b:int 'B'-value of RGB-value
	 */
	void setColor(int r, int g, int b) {
		fuellFarbe = parent.color(r, g, b);
		istAusgewaehlt = false;
	}

	abstract void klickDarauf(float mausX, float mausY);

	abstract void myDraw();

	abstract void verschiebe(float mausX, float mausY);

	abstract void zoomen(float zoom);

	abstract void drehen(int k);

	abstract float flaecheBerechnen();
}