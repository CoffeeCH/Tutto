import java.io.File;
import java.util.ArrayList;

import processing.core.PApplet;

/**
 *  A Viewer manages the forms.
 *  A Viewer sends forms to a CSVDatei.
 *  A Viewer gets forms from a CSVDatei.
 *  
 *  @author Philippe Krüttli
 *  
 */

public class Viewer {

	PApplet parent; // The Parent PApplet that we will render ourselves onto
	ArrayList<Form> formenListe = new ArrayList<Form>();
	CSVDatei myFile;
	Farbenverwalter myColor = new Farbenverwalter();
	boolean farbeAendern = false;

	Viewer(PApplet p) {
		parent = p;
		formenListe.add(new Kreis(parent));
		formenListe.add(new Kreis(parent, 120, 30, 200, 80, parent.color(100, 100, 100)));
		formenListe.add(new Dreieck(parent));
		formenListe.add(new Rechteck(parent, 100, 50, 150, 100, 100, 150, 50, 100, parent.color(0, 0, 0)));
		formenListe.add(new Kante(parent));
		formenListe.add(new Kante(parent, 100, 200, 50, 300, 5, parent.color(120, 100, 50)));
		myFile = new CSVDatei(parent);
	}

	/**
	 * Draws all the forms.
	 */
	void zeichneFormen() {
		for (Form f : formenListe) {
			f.myDraw();
		}
	}

	/**
	 * Chooses a form, using the mouse-position.
	 * 
	 * @param mausX:float horizontal mouse-position in pixel
	 * @param mausY:float vertical mouse-position in pixel
	 */
	void mausPositionen(float mausX, float mausY) {
		for (Form f : formenListe) {
			f.istAusgewaehlt = false;
			f.klickDarauf(mausX, mausY);
		}
	}
	
	/**
	 * Moves the form, using the mouse-position.
	 * 
	 * @param mausX:float horizontal mouse-position in pixel
	 * @param mausY:float vertical mouse-position in pixel
	 */
	void mausZiehen(float mausX, float mausY) {
		if (mausX >= 0 && mausX <= parent.width && mausY >= 0 && mausY <= parent.height) {
			for (Form f : formenListe) {
				if (f.istAusgewaehlt) {
					f.verschiebe(mausX, mausY);
				}
			}
		}
	}
	
	/**
	 * Zooms a form, using the scroll-factor.
	 * User can enlarge or shrink the selected form.
	 * 
	 * @param zoom:float zoom-factor (-1.0 or 1.0)
	 */
	void mausZoom(float zoom) {
		for (Form f : formenListe) {
			if (f.istAusgewaehlt) {
				f.zoomen(zoom);
			}
		}
	}

	/**
	 * Rotates a form, using the arrow-keys.
	 * <b>Doesn't work yet!</b>
	 * 
	 * @param k:int keyCode of pressed key
	 */
	void formDrehen(int k) {
		for (Form f : formenListe) {
			if (f.istAusgewaehlt) {
				f.drehen(k);
			}
		}
	}
	
	/**
	 * Deletes the selected form if 'Delete' is pressed.
	 * 
	 * @param k:int keyCode of pressed key
	 */
	void loeschen(int k) {
		for (Form f : formenListe) {
			if (f.istAusgewaehlt && k == 127) {
				formenListe.remove(f);
				break;
			}
		}
	}
	
	/**
	 * Duplicates the selected form if key 'x' is pressed.
	 * The new form is placed a little bit next to the original form.
	 * 
	 * @param k:int keyCode of pressed key
	 */
	void kopieren(int k) {

		int abstand = 50;
		if (k == 88) {
			for (Form f : formenListe) {

				if (f.istAusgewaehlt) {
					if (f instanceof Dreieck) {
						Dreieck d = (Dreieck) f;
						formenListe.add(new Dreieck(parent, (int) d.punktA.xPosInPix + abstand, (int) d.punktA.yPosInPix,
								(int) d.punktB.xPosInPix + abstand, (int) d.punktB.yPosInPix,
								(int) d.punktC.xPosInPix + abstand, (int) d.punktC.yPosInPix, d.fuellFarbe));
					} else if (f instanceof Rechteck) {
						Rechteck d = (Rechteck) f;
						formenListe.add(new Rechteck(parent, (int) d.punktA.xPosInPix + abstand, (int) d.punktA.yPosInPix,
								(int) d.punktB.xPosInPix + abstand, (int) d.punktB.yPosInPix,
								(int) d.punktC.xPosInPix + abstand, (int) d.punktC.yPosInPix,
								(int) d.punktD.xPosInPix + abstand, (int) d.punktD.yPosInPix, d.fuellFarbe));
					} else if (f instanceof Kreis) {
						Kreis d = (Kreis) f;
						formenListe.add(new Kreis(parent, (int) d.punktA.xPosInPix + abstand, (int) d.punktA.yPosInPix,
								(int) d.breite, (int) d.hoehe, d.fuellFarbe)); 
					} else if (f instanceof Kante) {
						Kante d = (Kante) f;
						formenListe.add(new Kante(parent, (int) d.punktA.xPosInPix + abstand, (int) d.punktA.yPosInPix,
								(int) d.punktB.xPosInPix + abstand, (int) d.punktB.yPosInPix, (int) d.dicke,
								(int) d.strichFarbe));
					}
					break;
				}
			}
		}
	}

	
	/**
	 * Returns the area of all forms together.
	 * @return:float total area of all forms
	 */
	float berechneFlaeche() {
		float gesamtFlaeche = 0;

		for (Form f : formenListe) {
			gesamtFlaeche += f.flaecheBerechnen();
		}

		return gesamtFlaeche;
	}

	/**
	 * Shows the calculated area of all forms as text.
	 * Uses the method berechneFlaeche().
	 */
	void flaecheAusgeben() {
		float hoehe = 50;
		float breite = 300;
		float xPos = 10;
		float yPos = parent.height - hoehe;

		parent.fill(0);
		parent.textSize(30);
		parent.rectMode(parent.CORNER);
		parent.text("Fläche: " + parent.str((int) berechneFlaeche()) + " px" + '\u00B2', xPos, yPos, breite, hoehe);
	}
	
	/**
	 * Checks is total area should be shown by checking keyCode.
	 * If keyCode is keyCode of 'f', it executes flaecheAusgeben().
	 */
	void istFlaecheAngezeigt() {
		if (parent.keyCode == 70) {
			flaecheAusgeben();
		}
	}
	
	/**
	 * Performs the desired action, if key is pressed.
	 * Executes the method where keyCode matches the desired value.
	 */
	void keyAction() {
		formDrehen(parent.keyCode);
		loeschen(parent.keyCode);
		farbeAendern(parent.keyCode);
		kopieren(parent.keyCode);
		myFile.dateiSpeichern(parent.keyCode, formenListe);
		myFile.dateiLaden(parent.keyCode, formenListe);
	}
	
	/**
	 * Gets the CSVDatei if another class needs it.
	 * @return:CSVDatei the CSVDatei that is already used.
	 */
	CSVDatei getCSVDatei() {
		return myFile;
	}
	
	/**
	 * Loads all forms from the CSVDatei myFile.
	 * @param selection:File the selected file to open
	 */
	void loadFormen(File selection) {
		formenListe = myFile.getFormenListe(selection);
		zeichneFormen();
	}
	
	/**
	 * Checks if color should be changed.
	 * If keyCode is keyCode of 'c', color changes to desired value.
	 * The colors have the values specified in Farbenverwalter.
	 * @param k:int keyCode of pressed key
	 */
	void farbeAendern(int k) {
		if (k == 67) {
			farbeAendern = true;
		}

		if (k == 8 && farbeAendern == true) {
			farbeAendern = false;
		}

		if (farbeAendern) {
			for (Form f : formenListe) {
				if (f.istAusgewaehlt) {
					f = myColor.farbeAendern(k, f);
					break;
				}
			}
		}
	}
	
	/**
	 * Saves all the forms to the selected CSVDatei.
	 * @param selection:File the selected file to write to
	 */
	void saveFormen(File selection) {
		myFile.saveFormen(selection);
	}
}
