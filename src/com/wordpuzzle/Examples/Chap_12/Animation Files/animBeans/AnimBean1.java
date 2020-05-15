package animBeans;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AnimBean1 extends JPanel
							implements ActionListener
{
	private ImageIcon[] img;
	private String imageName = "juggler";
	private final int NUM_FRAMES = 4;
	private int currentImg = 0;
	private final int DELAY = 100;	//??
	private Timer animTimer;

	public static void main(String[] args)
	{
		AnimBean1 anim = new AnimBean1();

		//If panel size set here, BeanBox ignores it!!!
		anim.setVisible(true);
	}

	public AnimBean1()
	{
		img = new ImageIcon[NUM_FRAMES];
		for (int i=0; i<img.length; i++)
		{
			img[i] = new ImageIcon(imageName + i + ".gif");
		}
		animTimer = new Timer(DELAY,this);
		animTimer.start();
	}

	public void paint(Graphics g)
	{
		img[currentImg].paintIcon(this,g,0,0);
		currentImg = (currentImg+1)% NUM_FRAMES;
	}

	public void actionPerformed(ActionEvent e)
	{
		repaint();
	}

	public Dimension getPreferredSize()
	{
		return new Dimension(140,120);
	}
}
