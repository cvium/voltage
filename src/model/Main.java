package model;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class Main extends MIDlet implements CommandListener {

	private Display display;
	
	public Main() {
	}
	
	protected void startApp() throws MIDletStateChangeException {
		display = Display.getDisplay(this);
		/*volt1 = new TextField("U1", "0", 4, TextField.DECIMAL);
		form = new Form(null);
		form.append(volt1);
		display.setCurrent(form);*/
		display.setCurrent(new VoltCanvas());
		
	}
	
	public void commandAction(Command arg0, Displayable arg1) {
		// TODO Auto-generated method stub
		
	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		// TODO Auto-generated method stub
		
	}

	protected void pauseApp() {
		// TODO Auto-generated method stub
		
	}



}
