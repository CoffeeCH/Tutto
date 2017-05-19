/*
 *******************************************************
 *******************************************************
 ********** Formeneditor *******************************
 ********** Version 10.0 *******************************
 ********** C#, Java-Kurs ******************************
 ********** Philippe Krüttli & Frithjof Hoppe **********
 ********** 18.05.2017 *********************************
 *******************************************************
 *******************************************************
 
 To do:
 ------
 - 5.1) rotate forms
 - 10)  subtract area of superimposed forms
 - 14)  make it run on Swing-Toolkit without Processing-libraries
 
*/

import java.io.File;

import processing.core.PApplet;
import processing.event.MouseEvent;

/**
 *  Formeneditor is the main class.
 *  Formeneditor manages all the components.
 *  Formeneditor implements all the Processing-functions.
 *  Formeneditor is the parent of all other classes.
 *  
 *  @author Philippe Krüttli
 *  
 */

public class Formeneditor extends PApplet {
	Viewer viewer;

	public static void main(String[] args) {
		PApplet.main("Formeneditor");
	}

	public void settings() {
		size(800, 800);
	}

	public void setup() {
		frameRate(500);
		viewer = new Viewer(this);
	}

	public void draw() {
		background(255);
		viewer.zeichneFormen();
		viewer.istFlaecheAngezeigt();
	}

	public void mousePressed() {
		viewer.mausPositionen(mouseX, mouseY);
	}

	public void mouseDragged() {
		viewer.mausZiehen(mouseX, mouseY);
	}

	public void mouseWheel(MouseEvent event) {
		float e = event.getCount();
		viewer.mausZoom(e);
	}

	public void keyPressed() {
		viewer.keyAction();
	}

	public void saveFileDialog() {
		selectOutput("Datei speichern", "saveFileSelected");
	}

	public void openFileDialog() {
		selectInput("Datei öffnen", "openFileSelected");
	}

	public void saveFileSelected(File selection) {
		viewer.saveFormen(selection);
	}

	public void openFileSelected(File selection) {
		viewer.loadFormen(selection);
	}
}