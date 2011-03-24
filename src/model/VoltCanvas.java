package model;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
//import javax.microedition.lcdui.Font;

public class VoltCanvas extends Canvas {

	private int volt1, volt2, turn1, turn2;
	private int selectedComponent;
	
	public VoltCanvas() {
		super();
		volt1 = 40;
		volt2 = 10;
		turn1 = 400;
		turn2 = 1600;
		selectedComponent = 0;
	}
	
	protected void showNotify() {
		setFullScreenMode(true);
	}
	
	
	protected void paint(Graphics g) {
		// Clears the screen
		g.setColor(255, 255, 255);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		// Draws a nice small rectangle in the middle of the screen.
		g.setColor(0,0,0);
		g.drawRect(80, 100, 80, 120);
		
		// Draw voltages and turns 
		String u1 = "U1: " + volt1;
		String u2 = "U2: " + volt2;
		String n1 = "N1: " + turn1;
		String n2 = "N2: " + turn2;
		
		g.drawString(u1, 80, 95, Graphics.BOTTOM|Graphics.RIGHT);
		g.drawString(u2, 160, 95, Graphics.BOTTOM|Graphics.LEFT);
		g.drawString(n1, 80, 225, Graphics.TOP|Graphics.RIGHT);
		g.drawString(n2, 160, 225, Graphics.TOP|Graphics.LEFT);
		
		// Draw a rectangle around the selected component
		switch(selectedComponent) {
		case 0: 
			g.drawRoundRect(80 - g.getFont().stringWidth(u1), 
							95 - g.getFont().getHeight(),
							g.getFont().stringWidth(u1),
							g.getFont().getHeight(), 3, 3);
			break;
		case 1:
			g.drawRoundRect(160, 95 - g.getFont().getHeight(),
					g.getFont().stringWidth(u2),
					g.getFont().getHeight(), 3, 3);
			break;
		case 2:
			g.drawRoundRect(80 - g.getFont().stringWidth(n1), 225,
					g.getFont().stringWidth(n1),
					g.getFont().getHeight(), 3, 3);
			break;
		case 3:
			g.drawRoundRect(160, 225,
					g.getFont().stringWidth(n2),
					g.getFont().getHeight(), 3, 3);
			break;
		}
		
		// Draw arrows in the sides
		int maxx = getWidth()-1;
		int cy = getHeight()>>1;
		
		g.fillTriangle(0,cy,15,cy-5,15,cy+5);
		g.fillTriangle(maxx,cy,maxx-15,cy-5,maxx-15,cy+5);
	}
	
	private void handleKeyEvent(int ga) {
		if (Canvas.LEFT == ga) {
			selectedComponent--;
			if(selectedComponent<0) selectedComponent = 3;
		} else if (Canvas.RIGHT == ga) {
			selectedComponent++;
			if(selectedComponent>3) selectedComponent = 0;
		}
		
		repaint();
	}
	
	protected void keyPressed(int keyCode){
		handleKeyEvent(getGameAction(keyCode));
	}
	
	/*
	 * Standard keyRepeated handler (called by system)
	 * When holding key (repeat), handle event
	 */
	protected void keyRepeated(int keyCode){
		handleKeyEvent(getGameAction(keyCode));
	}
	
	// Handle touch events for devices with a touch screen
	protected void pointerPressed(int x, int y) {
		super.pointerPressed(x, y);
		
		// NOTE:  This is a very quick method to detect touch events in 
		//        the border of the screen and translate to key game action events.
		if( x>getWidth()-50 )
			handleKeyEvent(Canvas.RIGHT);
		else if( x<50 )
			handleKeyEvent(Canvas.LEFT);
		else if( y>getHeight()-50 )
			handleKeyEvent(Canvas.DOWN);
		else if( y<50 )
			handleKeyEvent(Canvas.UP);
	}
}
