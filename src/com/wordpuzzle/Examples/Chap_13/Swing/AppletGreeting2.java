import java.awt.*;
import javax.swing.*;

public class AppletGreeting2 extends JApplet
{
	public void init()
	{
		JLabel message = new JLabel("Greetings!",JLabel.CENTER);
		message.setFont(new Font("Arial",Font.BOLD,24));
		add(message, BorderLayout.CENTER);
	}
}

