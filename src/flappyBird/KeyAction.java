package flappyBird;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyAction implements KeyListener{

	public boolean up = false,down=false,R;
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_UP) up = true;
		if(code == KeyEvent.VK_R) R = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_UP) up = false;
		if(code == KeyEvent.VK_R) R = false;
	}

}
