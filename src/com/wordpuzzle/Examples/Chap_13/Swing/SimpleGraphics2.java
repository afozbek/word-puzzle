import java.awt.*;
import javax.swing.*;

public class SimpleGraphics2 extends JApplet
{
	public void init()
	{
		ImagePanel pane = new ImagePanel();
		setContentPane(pane);
	}

	class ImagePanel extends JPanel
	{
		public void paintComponent(Graphics g)
		{
//			super.paintComponent(g); //Paint background.
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
}
