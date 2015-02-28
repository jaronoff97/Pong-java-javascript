package pong;

import java.awt.*;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;


public class SineWave {

public int wavelength, amplitude, phase;


public SineWave(int w, int a, int p) {
   wavelength = w;
   amplitude = a;
   phase = p;
}

public void advance(int phaseIncrement) {
    // note: this advances the initial phase, so a positive value
    // moves the wave form to the left, negative to the right.
   phase -= phaseIncrement;
   if(phase >= 360) phase %= 360;
   if(phase <    0) phase += 360; 
}

public SineWave copy() {
   return new SineWave(wavelength, amplitude, phase);
}

public int getY(int x) {
   double degreesPerPixel = 360.0 / wavelength;
   double degrees = (-phase+180) + x*degreesPerPixel;
   return (int)(amplitude*Math.sin(degrees*Math.PI/180.0));
}


// draws a very nice looking sine curve if the wavelength is not
// too small or amplitude too big. Good for presentations.
public void drawThick(Graphics g, int x, int y, int width) {
   double degreesPerPixel = 360.0 / wavelength;
   int yOffset = (int)(amplitude/2.0)+2;
   double degrees;
   int xloc;
   int yloc;
   for (int i=0; i<width; i++) {
      degrees = phase + i*degreesPerPixel;
      xloc=x+i;
      yloc=(int)(y+yOffset+amplitude*Math.sin(degrees*Math.PI/180.0));
      g.drawLine(xloc,yloc-1,xloc,yloc+1);
   }
}
  

//this function draws a thin sine wave by connecting two
// adjacent points with a line.
// Sine wave could only be vertical
public void draw(Graphics g, int x, int y, int length) 
{   for (int i=1; i<=length; i++)
    {  Point p1 = new Point(x+i, y+getY(i));
       Point p2 = new Point(x+i+1, y+getY(i+1));
       g.drawLine(p1.x, p1.y, p2.x, p2.y);
    }
}
  

public void draw(Graphics g, int x, int y, int length, int thickness) {
   for (int i=0; i<length; i++)
   {  Point p1 = new Point(x+i, y+getY(i));
      Point p2 = new Point(x+i+1, y+getY(i+1));
      for (int k=0; k<thickness; k++) 
         g.drawLine(p1.x+k, p1.y, p2.x+k, p2.y);
   }
}
  
  
// This function does the same thing as the previous one,
// plus it can draw a sine wave between any two points
public void draw(Graphics g, Point start, Point stop)
{   float angle = MyMath.getAngle(start, stop);
    float length = MyMath.length(start.x, start.y, stop.x, stop.y);
    for (int i=1; i<=length; i++)
    {  Point p1 = new Point(start.x+i, start.y+getY(i));
       Point p2 = new Point(start.x+i+1, start.y+getY(i+1));
       Point p3 = MyMath.translate(start, p1, angle);
       Point p4 = MyMath.translate(start, p2, angle);
       g.setLineWidth(20);
       g.setColor(Color.black);
       g.drawLine(p3.x, p3.y, p4.x, p4.y);
       g.setColor(Color.white);
    }
}
  

// return phase in degrees when x = length.
public int getPhase(float length) 
{   return (int)(360*(length/wavelength) + phase);
}
  

// returns the number of wavelengths that fits into length=x
public  float getWaveNum(int x) {
   return (float)(x/wavelength);
}
  
  
}


