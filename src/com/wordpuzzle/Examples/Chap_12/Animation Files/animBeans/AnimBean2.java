package animBeans;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;  //Needed for the File class.

public class AnimBean2 extends JPanel
							implements ActionListener
{
	private ImageIcon[] image;
	private String imageName = "juggler";
	private String oldImage = "juggler";
	private int numFrames; //(No longer a constant.)
	private int oldNumFrames = 0;
	private int currentImage = 0;
	private  int delay = 100;  //(No longer a constant.)
	private Timer animTimer;

	public static void main(String[] args)
	{
		AnimBean2 anim = new AnimBean2();
		anim.setVisible(true);
	}

	public AnimBean2()
	{
		loadFrames();
		animTimer = new Timer(delay,this);
		animTimer.start();
	}

	private void loadFrames()
	{
		//Check no. of frames first...
		numFrames = 0;
		File fileName = new File(
					imageName + (numFrames) + ".gif");
		while (fileName.exists())
		{
			numFrames++;
			fileName = new File(
					imageName + numFrames + ".gif");
		}
		if (numFrames==0)	//No image found!
			return;	//Abandon loading of frames.

		//Following lines moved from constructor:
		image = new ImageIcon[numFrames];
		//Now load frames...
		for (int i=0; i<numFrames; i++)
		{
			image[i] = new ImageIcon(
							imageName + i + ".gif");
		}
	}

	public void paint(Graphics g)
	{
		if (!imageName.equals(oldImage))
		{
			loadFrames();
			if (numFrames==0)	//No image found!
			{
				//Reset image name and no. of
				//frames to their old values...
				setImageName(oldImage);
				numFrames = oldNumFrames;
			}
			else
			{
				oldImage = imageName;
				oldNumFrames = numFrames;
				g.setColor(getBackground());
				g.fillRect(
						0,0,getWidth(),getHeight());
				currentImage = 0;
			}
		}
		if (numFrames>0)	//Image exists.
		{
			image[currentImage].paintIcon(this,g,0,0);
			currentImage = (currentImage+1)%numFrames;
		}
	}

	public void actionPerformed(ActionEvent event)
	{
		repaint();
	}

	public String getImageName()
	{
		return imageName;
	}

	public void setImageName(String name)
	{
		imageName = name;
	}

	public int getDelay()
	{
		return delay;
	}

	public void setDelay(int delayIn)
	{
		delay = delayIn;
		animTimer.setDelay(delay);
	}

	public Dimension getPreferredSize()
	{
		return new Dimension(140,120);
	}
}
