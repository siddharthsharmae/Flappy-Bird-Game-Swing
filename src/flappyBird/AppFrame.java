package flappyBird;

import javax.swing.JFrame;

public class AppFrame extends JFrame{
	
	
	AppFrame(){
		setTitle("Flappy Bird");
		setDefaultCloseOperation(AppFrame.EXIT_ON_CLOSE);
		add(new AppPanel());
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
