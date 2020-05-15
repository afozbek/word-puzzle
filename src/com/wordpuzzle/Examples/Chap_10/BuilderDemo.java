import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.beans.*;

public class BuilderDemo extends JFrame
{
	public static void main(String[] args)
	{
		new BuilderDemo();
	}

	public BuilderDemo()
	{
        try
        {
			BufferedInputStream inStream =
					new BufferedInputStream(
						new FileInputStream("bean.xml"));

			XMLDecoder decoder = new XMLDecoder(inStream);
			decoder.readObject();
        }
        catch (FileNotFoundException ex)
        {
	   		System.out.println("XML file not found!");
        }
	}
}

