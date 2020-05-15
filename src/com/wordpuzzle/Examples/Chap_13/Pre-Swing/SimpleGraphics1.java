import java.applet.*;
import java.awt.*;

public class SimpleGraphics1 extends Applet
{
	public void paint(Graphics g)
	{
		g.setFont(new Font("TimesRoman",Font.BOLD,36));
		g.setColor(Color.blue);
		g.drawString("Simple Applet Graphics",50,80);
		g.setColor(Color.red);
		g.drawLine(50,85,410,85);
		g.setFont(new Font("TimesRoman",Font.PLAIN,24));
		g.setColor(Color.magenta);
		g.drawString("Here's my message in a box",110,150);
		g.setColor(Color.green);
		g.drawRect(100,120,280,50);
	}
}
