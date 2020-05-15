import java.awt.*;
import javax.swing.*;

public class FahrToCelsius2 extends JApplet
{
	private String fahrString;
	private float fahrTemp, celsiusTemp;

	public void init()
	{
		fahrString = JOptionPane.showInputDialog(
				"Enter temperature in degrees Fahrenheit");

		fahrTemp = Float.parseFloat(fahrString);
		celsiusTemp = (fahrTemp-32)*5/9;

		JLabel message = new JLabel(
			"Temperature in degrees Celsius: " + celsiusTemp,JLabel.CENTER);
		add(message,BorderLayout.CENTER);
	}
}

