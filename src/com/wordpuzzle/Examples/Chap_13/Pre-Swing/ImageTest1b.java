import java.awt.*;
import java.applet.*;
import java.net.*;

public class ImageTest1b extends Applet
{
	private Image image;

	public void init()
	{
		try
		{
			image = getImage(new URL(
				"file:///F:/Java Text 2nd Ed/Examples/Chap 13/Pre-Swing/earth.gif"));
		}
		catch(MalformedURLException e)
		{
			System.out.println("Invalid URL!");
			System.exit(0);
		}
	}

	public void paint(Graphics g)
	{
		g.drawImage(image,0,0,this);
	}
}
