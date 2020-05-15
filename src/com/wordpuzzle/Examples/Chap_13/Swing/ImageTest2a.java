import java.awt.*;
import javax.swing.*;
import java.net.*;

/**********************************************
DOESN'T WORK IN APPLETVIEWER OR ANY OF THE
4 BROWSER/VERSION VARIATIONS USED IN TESTING!!!
SEE ImageTest2b.
**********************************************/

public class ImageTest2a extends JApplet
{
	private ImageIcon image;

	public void init()
	{
		image = new ImageIcon(getDocumentBase(), "drinker.gif");
	}

	public void paint(Graphics g)
	{
		image.paintIcon(this,g,0,0);
	}
}

