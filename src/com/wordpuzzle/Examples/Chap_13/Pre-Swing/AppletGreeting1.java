import java.applet.*;
import java.awt.*;

public class AppletGreeting1 extends Applet
{
	public void paint(Graphics g)
	{
		g.setFont(new Font("Arial",Font.BOLD,24));
		g.drawString("Greetings!",100,75);
	}
}

