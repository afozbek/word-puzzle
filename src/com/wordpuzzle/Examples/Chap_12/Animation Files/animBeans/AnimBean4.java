package animBeans;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.beans.*;

public class AnimBean4 extends JPanel
						implements ActionListener
{
	private ImageIcon[] image;
	private String imageName = "juggler";
	private String oldImage = "juggler";
	private int numFrames;
	private int oldNumFrames = 0;
	private int currentImage = 0;
	private int delay = 100;	//??
	private Timer animTimer;
	private PropertyChangeSupport changeSupport;

	public static void main(String[] args)
	{
		AnimBean4 anim = new AnimBean4();

		//If panel size set here, BeanBox ignores it!!!
		anim.setVisible(true);
	}

	public AnimBean4()
	{
		changeSupport = new PropertyChangeSupport(this);
		loadFrames();
		startAnimation();
	}

	private void loadFrames()
	{
		//Check number of frames in animation first...
		numFrames = 0;
		File fileName = new File(
							imageName + (numFrames) + ".gif");
		while (fileName.exists())
		{
			numFrames++;
			fileName = new File(
							imageName + (numFrames) + ".gif");
		}
		if (numFrames==0)	//No image found!
			return;	//Abandon loading of frames.

		image = new ImageIcon[numFrames];
		//Now load frames...
		for (int i=0; i<numFrames; i++)
		{
			image[i] = new ImageIcon(
								imageName + (i+1) + ".gif");
		}
	}

	public void startAnimation()
	{
		if (animTimer == null)
		{
			currentImage = 0;
			animTimer = new Timer(delay,this);
			animTimer.start();
		}
		else
			if (!animTimer.isRunning())
				animTimer.restart();
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
				g.fillRect(0,0,getWidth(),getHeight());
				currentImage = 0;
			}
		}
		if (numFrames>0)	//Image exists.
		{
			image[currentImage].paintIcon(this,g,0,0);
			currentImage = (currentImage+1)% numFrames;
		}
	}

	public void stopAnimation()
	{
		animTimer.stop();
	}

	public void actionPerformed(ActionEvent e)
	{
		repaint();
	}

	public String getImageName()
	{
		return imageName;
	}

	public void setImageName(String name)
	{
		String oldName = imageName;
		imageName = name;
		changeSupport.firePropertyChange(
					"imageName", oldName, imageName);
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

	public void addPropertyChangeListener(
						PropertyChangeListener listener)
	{
		changeSupport.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(
						PropertyChangeListener listener)
	{
		changeSupport.removePropertyChangeListener(listener);
	}
}
