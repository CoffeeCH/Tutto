import java.io.File;
import java.util.ArrayList;

import processing.core.PApplet;

/**
 * A CSVDatei saves and loads the forms from and in a CSV-file.
 * 
 * @author Philippe Krüttli
 * 
 */
public class CSVDatei {
	PApplet parent; // The Parent PApplet that we will render ourselves onto
	String[] daten = new String[100];

	CSVDatei(PApplet p) {
		parent = p;
	}
	
	/**
	 * Creates all the records for the csv-file.
	 * Opens the saveFileDialog to choose a path to save to.
	 * 
	 * @param k:int keyCode of pressed key
	 * @param formenListe:ArrayList_Form list of all forms in the editor
	 */
	void dateiSpeichern(int k, ArrayList<Form> formenListe) {
		if (k == 83) {
			int posInArray = 0;
			String eintrag = "";

			for (Form f : formenListe) {

				if (f instanceof Kreis) {
					Kreis d = (Kreis) f;
					eintrag = kreisEintrag(d);
				} else if (f instanceof Kante) {
					Kante d = (Kante) f;
					eintrag = kanteEintrag(d);
				} else if (f instanceof Rechteck) {
					Rechteck d = (Rechteck) f;
					eintrag = rechteckEintrag(d);
				} else if (f instanceof Dreieck) {
					Dreieck d = (Dreieck) f;
					eintrag = dreieckEintrag(d);
				}

				daten[posInArray] = eintrag;
				posInArray++;
			}
			((Formeneditor) parent).saveFileDialog();
		}
	}
	
	/**
	 * Creates a list of all forms from a csv-file.
	 * Creates a form by checking the first word of the record.
	 * 
	 * @param lines:String[] all the records of the csv-file
	 * @return:ArrayList_Form list of all created forms from csv-file
	 */
	ArrayList<Form> formenErstellen(String[] lines) {

		ArrayList<Form> formenListe = new ArrayList<Form>();
		for (String line : lines) {
			String[] list = parent.split(line, ';');
			if (line.startsWith("Kreis")) {
				formenListe.add(new Kreis(parent, (int) Float.parseFloat(list[1]), (int) Float.parseFloat(list[2]),
						(int) Float.parseFloat(list[3]), (int) Float.parseFloat(list[4]),
						(int) Float.parseFloat(list[5])));
			} else if (line.startsWith("Kante")) {
				formenListe.add(new Kante(parent, (int) Float.parseFloat(list[1]), (int) Float.parseFloat(list[2]),
						(int) Float.parseFloat(list[3]), (int) Float.parseFloat(list[4]),
						(int) Float.parseFloat(list[5]), (int) Float.parseFloat(list[6])));
			} else if (line.startsWith("Rechteck")) {
				formenListe.add(new Rechteck(parent, (int) Float.parseFloat(list[1]), (int) Float.parseFloat(list[2]),
						(int) Float.parseFloat(list[3]), (int) Float.parseFloat(list[4]),
						(int) Float.parseFloat(list[5]), (int) Float.parseFloat(list[6]),
						(int) Float.parseFloat(list[7]), (int) Float.parseFloat(list[8]),
						(int) Float.parseFloat(list[9])));
			} else if (line.startsWith("Dreieck")) {
				formenListe.add(new Dreieck(parent, (int) Float.parseFloat(list[1]), (int) Float.parseFloat(list[2]),
						(int) Float.parseFloat(list[3]), (int) Float.parseFloat(list[4]),
						(int) Float.parseFloat(list[5]), (int) Float.parseFloat(list[6]),
						(int) Float.parseFloat(list[7])));
			}
		}

		return formenListe;
	}
	
	/**
	 * Gives away the values of the forms in the editor.
	 * 
	 * @return:String[] all values of the visible forms
	 */
	String[] getDaten() {
		return daten;
	}

	/**
	 * Creates a record if 'd' is a 'Kreis' (circle or ellipse).
	 * 
	 * @param d:Form one of all visible forms of the editor
	 * @return:String csv-record for the used 'Kreis'
	 */
	String kreisEintrag(Kreis d) {
		String eintrag = "";

		eintrag = ("Kreis" + ";" + parent.str(d.punktA.xPosInPix) + ";" + parent.str(d.punktA.yPosInPix) + ";"
				+ parent.str(d.breite) + ";" + parent.str(d.hoehe) + ";" + parent.str(d.fuellFarbe));

		return eintrag;
	}

	/**
	 * Creates a record if 'd' is a 'Kante' (line).
	 * 
	 * @param d:Form one of all visible forms of the editor
	 * @return:String csv-record for the used 'Kante'
	 */
	String kanteEintrag(Kante d) {
		String eintrag = "";

		eintrag = ("Kante" + ";" + parent.str(d.punktA.xPosInPix) + ";" + parent.str(d.punktA.yPosInPix) + ";"
				+ parent.str(d.punktB.xPosInPix) + ";" + parent.str(d.punktB.yPosInPix) + ";" + parent.str(d.dicke)
				+ ";" + parent.str(d.fuellFarbe));

		return eintrag;
	}

	/**
	 * Creates a record if 'd' is a 'Rechteck' (rectangle / quadrangle / square).
	 * 
	 * @param d:Form one of all visible forms of the editor
	 * @return:String csv-record for the used Rechteck
	 */
	String rechteckEintrag(Rechteck d) {
		String eintrag = "";

		eintrag = ("Rechteck" + ";" + parent.str(d.punktA.xPosInPix) + ";" + parent.str(d.punktA.yPosInPix) + ";"
				+ parent.str(d.punktB.xPosInPix) + ";" + parent.str(d.punktB.yPosInPix) + ";"
				+ parent.str(d.punktC.xPosInPix) + ";" + parent.str(d.punktC.yPosInPix) + ";"
				+ parent.str(d.punktD.xPosInPix) + ";" + parent.str(d.punktD.yPosInPix) + ";"
				+ parent.str(d.fuellFarbe));

		return eintrag;
	}
	
	/**
	 * Creates a record if 'd' is a 'Dreieck' (triangle).
	 * 
	 * @param d:Form one of all visible forms of the editor
	 * @return:String csv-record for the used Dreieck
	 */
	String dreieckEintrag(Dreieck d) {
		String eintrag = "";

		eintrag = ("Dreieck" + ";" + parent.str(d.punktA.xPosInPix) + ";" + parent.str(d.punktA.yPosInPix) + ";"
				+ parent.str(d.punktB.xPosInPix) + ";" + parent.str(d.punktB.yPosInPix) + ";"
				+ parent.str(d.punktC.xPosInPix) + ";" + parent.str(d.punktC.yPosInPix) + ";"
				+ parent.str(d.fuellFarbe));

		return eintrag;
	}
	
	/**
	 * Sets formenListe to null.
	 * Sets values from selected file to formenListe.
	 * 
	 * @param k:int keyCode of pressed key
	 * @param formenListe:ArrayList_Form list of all forms
	 */
	void dateiLaden(int k, ArrayList<Form> formenListe) {
		if (k == 79) {
			formenListe.clear();
			parent.selectInput("Datei öffnen", "openFileSelected");
		}
	}
	
	/**
	 * Gives away a list of all the forms in the selected file.
	 * 
	 * @param selection:File selected file
	 * @return:ArrayList_Form list of all the forms in the selected file
	 */
	ArrayList<Form> getFormenListe(File selection) {
		ArrayList<Form> f = new ArrayList<Form>();
		f = openFileSelected(selection);

		return f;
	}
	
	/**
	 * Saves the values of all files to the selected csv-file.
	 * 
	 * @param selection:File selected file from saveFileDialog
	 */
	void saveFormen(File selection) {
		if (selection == null) {
			parent.text("Window was closed or the user hit cancel.", 100, 100);
		} else {
			parent.text("User selected " + selection.getAbsolutePath(), 100, 100);
			parent.saveStrings(selection.getAbsolutePath(), daten);
		}
	}
	
	/**
	 * Opens the selected file.
	 * Saves the values of the file to String[] lines.
	 * 
	 * @param selection:File selected file from openFileDialog
	 * @return:ArrayList_Form list of all the values from the opened file
	 */
	ArrayList<Form> openFileSelected(File selection) {
		ArrayList<Form> f = new ArrayList<Form>();
		if (selection == null) {
			parent.text("Window was closed or the user hit cancel.", 100, 100);
		} else {
			parent.text("User selected " + selection.getAbsolutePath(), 100, 100);
			String[] lines = parent.loadStrings(selection.getAbsolutePath());

			if (lines != null) {
				String name = selection.getName();

				if (lines.length > 0 && (name.endsWith(".csv") || name.endsWith(".txt"))) {
					f = formenErstellen(lines);
				} else {
					parent.text("Keine Formen gefunden", 100, 100);
				}
			} else {
				parent.text("Keine Objekte in lines", 100, 100);
			}
		}

		return f;
	}
}