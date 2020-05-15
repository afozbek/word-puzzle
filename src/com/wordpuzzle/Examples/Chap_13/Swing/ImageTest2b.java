import java.awt.*;
import javax.swing.*;
import java.net.*;

public class ImageTest2b extends JApplet
{
	private Image image;
	private ImageIcon icon;

	public void init()
	{
		image = getImage(getDocumentBase(), "earth.gif");
		icon = new ImageIcon(image);
	}

	public void paint(Graphics g)
	{
		icon.paintIcon(this,g,0,0);
	}
}

