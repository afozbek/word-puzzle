import java.awt.*;
import java.applet.*;
import java.net.*;

public class ImageTest1a extends Applet
{
	private Image image;

	public void init()
	{
		image = getImage(getDocumentBase(),"cutekittie.gif");
	}

	public void paint(Graphics g)
	{
		g.drawImage(image,0,0,this);
	}
}
