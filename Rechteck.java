import java.util.ArrayList;

import processing.core.PApplet;

/**
 * A Rechteck has four points ('Punkte').
 * A Rechteck is a rectangle / quadrangle / square.
 * A Rechteck doesn't have to have parallel lines.
 * 
 * @author Philippe Krüttli
 *
 */

public class Rechteck extends MultipointForm{
	PApplet parent;	//The Parent PApplet that we will render ourselves onto
	String name = "rechteck";

	  Punkt punktD;
	  
	  Rechteck(PApplet p)
	  {
			super(p, 50, 50, 100, 50, 100, 100, p.color(100, 100, 100));
			parent = p;
		    punktD = new Punkt(p, 50, 100);
	  }

	  Rechteck(PApplet p, int newx1, int newy1, int newx2, int newy2, int newx3, int newy3, int newx4, int newy4, int newFarbe)
	  {
	    super (p, newx1, newy1, newx2, newy2, newx3, newy3, newFarbe);
	    parent = p;
	    punktD = new Punkt(p, newx4, newy4);
	  }
	  
	  /**
	   * Draws a Rechteck (rectangle / quadranlge / square).
	   * Uses the given values for the positions.
	   */
	  void myDraw()
	  {
	    initDraw();
	    parent.stroke(0, 0, 0);
	    parent.quad(punktA.xPosInPix, punktA.yPosInPix, punktB.xPosInPix, punktB.yPosInPix, punktC.xPosInPix, punktC.yPosInPix, punktD.xPosInPix, punktD.yPosInPix);
	  }
	  
	  /**
	   * Checks if user clicked on form.
	   * If yes, it changes its color to red.
	   */
	  void klickDarauf(float mausX, float mausY)
	  {
	    ArrayList<Kante> kanten = new ArrayList<Kante>();
	    int xCounter = 0;
	    int yCounter = 0;
	    float maxX = 0;
	    float minX= 0;
	    float maxY= 0;
	    float minY= 0;

	    kanten.add(new Kante (parent, punktA, punktB));
	    kanten.add(new Kante (parent, punktB, punktC));
	    kanten.add(new Kante (parent, punktC, punktD));
	    kanten.add(new Kante (parent, punktD, punktA));

	    for (Kante k : kanten)
	    {


	      if (k.getXfromY(mausY) != 0)
	      {
	        xCounter++;

	        if (k.getXfromY(mausY) > maxX)
	        {
	          maxX = k.getXfromY(mausY);
	        }

	        if (minY == 0)
	        {
	          minY = maxY;
	        }

	        if (k.getXfromY(mausY) < minX )
	        {
	          minX = k.getXfromY(mausY);
	        }
	      }

	      if (k.getYfromX(mausX) != 0)
	      {
	        yCounter++;

	        if (k.getYfromX(mausX) > maxY)
	        {
	          maxY = k.getYfromX(mausX);
	        }

	        if (minY == 0)
	        {
	          minY = maxY;
	        }

	        if (k.getYfromX(mausX) < minY)
	        {
	          minY = k.getYfromX(mausX);
	        }
	      }
	    }
	    if (xCounter == 2 && yCounter == 2)
	    {
	      if (mausX >= minX && mausX <= maxX && mausY >= minY && mausY <= maxY)
	      {
	        istAusgewaehlt = true;
	      }
	    }
	  }
	  
	  /**
	   * Moves the form using the point ('Punkt') of abstandZu(pMaus) from class MultipointForm.
	   * Moves every point ('Punkt') of the form in the same direction.
	   * @param pMaus:Punkt created point using mouse-positions
	   */
	  void verschiebeNach(Punkt pMaus)
	  {
	    Punkt abstand = punktC.abstandZu(pMaus);

	    punktB.verschiebeUm(abstand);
	    punktA.verschiebeUm(abstand);
	    punktC.verschiebeUm(abstand);
	    punktD.verschiebeUm(abstand);
	  }
	  
	  /**
	   * Starts method verschiebeNach().
	   * Creates a point ('Punkt') from the mouse-positions.
	   */
	  void verschiebe(float mausX, float mausY)
	  {
	    verschiebeNach(new Punkt(parent, mausX, mausY));
	  }
	  
	  /**
	   * Enlarges or shrinks the form, using the zoom-factor.
	   */
	  void zoomen(float zoom)
	  {
	    float hoehe = parent.dist(punktA.xPosInPix, punktA.yPosInPix, punktC.xPosInPix, punktC.yPosInPix);
	    float breite = parent.dist(punktB.xPosInPix, punktB.yPosInPix, punktD.xPosInPix, punktD.yPosInPix);

	    if ((breite > 50 && hoehe > 50 && zoom < 0)||(breite > 0 && hoehe > 0 && zoom > 0))
	    {
	      float xM = (punktA.xPosInPix + punktB.xPosInPix)/2;
	      float yM = (punktA.yPosInPix + punktB.yPosInPix)/2;
	      float r =  parent.dist(xM, yM, punktA.xPosInPix, punktA.yPosInPix);
	      float r2 = r + (10*zoom);
	      float zf = r2 / r;

	      float dXA = (punktA.xPosInPix - xM) * zf;
	      float dYA = (punktA.yPosInPix - yM) * zf;
	      float dXB = (punktB.xPosInPix - xM) * zf;
	      float dYB = (punktB.yPosInPix - yM) * zf;
	      float dXC = (punktC.xPosInPix - xM) * zf;
	      float dYC = (punktC.yPosInPix - yM) * zf;
	      float dXD = (punktD.xPosInPix - xM) * zf;
	      float dYD = (punktD.yPosInPix - yM) * zf;


	      punktA.xPosInPix = dXA + xM;
	      punktA.yPosInPix = dYA + yM;
	      punktB.xPosInPix = dXB + xM;
	      punktB.yPosInPix = dYB + yM;
	      punktC.xPosInPix = dXC + xM;
	      punktC.yPosInPix = dYC + yM;
	      punktD.xPosInPix = dXD + xM;
	      punktD.yPosInPix = dYD + yM;
	    }
	  }
	   
	  /**
	   * Rotates a form using the key-arrows.
	   * <b>Doesn't work yet.</b>
	   */
	  void drehen(int k)
	  {
	    float drehFaktor = 5;
	    switch(k)
	    {
	    case 38:
	      punkteReihenfolge();
	      break;
	    case 37:
	      linksDrehen(drehFaktor);
	      break;
	    case 39:
	      rechtsDrehen(drehFaktor);
	      break;
	    default:
	      break;
	    }
	  }

	  void linksDrehen(float drehFaktor)
	  {
	  }

	  void rechtsDrehen(float drehFaktor)
	  {
	    float xRichtung;
	    float yRichtung;
	  }
	  
	  /**
	   * Sets the order of the form's points ('Punkte').
	   */
	  void punkteReihenfolge()
	  {

	    ArrayList<Punkt> punkt = new ArrayList<Punkt>();
	    ArrayList<Punkt> sortPunkt = new ArrayList<Punkt>();

	    punkt.add(new Punkt(parent, punktA.xPosInPix, punktA.yPosInPix));
	    punkt.add(new Punkt(parent, punktB.xPosInPix, punktB.yPosInPix));
	    punkt.add(new Punkt(parent, punktC.xPosInPix, punktC.yPosInPix));
	    punkt.add(new Punkt(parent, punktD.xPosInPix, punktD.yPosInPix));

	    Punkt groessterPunkt = new Punkt(parent, 0, 0);

	    int position = 0;
	    int punkteSize =punkt.size();


	    while (punkteSize != 0)
	    {
	      for (int o = 1; o < position; o++)
	      {            

	        if (punkt.get(o).xPosInPix > groessterPunkt.xPosInPix)
	        {
	          groessterPunkt = punkt.get(o);
	          position = o;
	        }
	      }
	      sortPunkt.add(groessterPunkt); 
	      groessterPunkt = new Punkt(parent, 0, 0);
	      punkt.remove(punkt.get(position));

	      punkteSize--;
	    }
	  }
	  
	  /**
	   * Calculates the area of the form.
	   */
	  float flaecheBerechnen()
	  {
	    float flaeche = 0;

	    float winkelAlphaAusSinus = 0;
	    float winkelGammaAusSinus = 0;
	    float groessererWinkel = 0;
	    float kleinererWinkel = 0;
	    float groessereSteigung = 0;
	    float kleinereSteigung = 0;

	    float temp1 = ((punktA.yPosInPix)-(punktB.yPosInPix))/((punktA.xPosInPix)-(punktB.xPosInPix));
	    float temp2 = ((punktA.yPosInPix)-(punktD.yPosInPix))/((punktA.xPosInPix)-(punktD.xPosInPix));

	    if (temp1 > temp2)
	    {
	      groessereSteigung = temp1;
	      kleinereSteigung = temp2;
	    } else 
	    {
	      groessereSteigung = temp2; 
	      kleinereSteigung = temp1;
	    }

	    groessererWinkel = parent.atan(groessereSteigung);
	    kleinererWinkel = parent.atan(kleinereSteigung);

	    winkelAlphaAusSinus = groessererWinkel - kleinererWinkel;


	    temp1 = ((punktC.yPosInPix)-(punktD.yPosInPix))/((punktC.xPosInPix)-(punktD.xPosInPix));
	    temp2 = ((punktC.yPosInPix)-(punktB.yPosInPix))/((punktC.xPosInPix)-(punktB.xPosInPix));

	    if (temp1 > temp2)
	    {
	      groessereSteigung = temp1;
	      kleinereSteigung = temp2;
	    } else 
	    {
	      groessereSteigung = temp2; 
	      kleinereSteigung = temp1;
	    }

	    groessererWinkel = parent.atan(groessereSteigung);
	    kleinererWinkel = parent.atan(kleinereSteigung);

	    winkelGammaAusSinus = groessererWinkel - kleinererWinkel;

	    flaeche = (parent.dist(punktA.xPosInPix, punktA.yPosInPix, punktB.xPosInPix, punktB.yPosInPix) *
	      parent.dist(punktA.xPosInPix, punktA.yPosInPix, punktD.xPosInPix, punktD.yPosInPix) *
	      parent.sin(winkelAlphaAusSinus) +
	      parent.dist(punktC.xPosInPix, punktC.yPosInPix, punktD.xPosInPix, punktD.yPosInPix) *
	      parent.dist(punktC.xPosInPix, punktC.yPosInPix, punktB.xPosInPix, punktB.yPosInPix) *
	      parent.sin(winkelGammaAusSinus))/2;
	    return flaeche;
	  }
}
