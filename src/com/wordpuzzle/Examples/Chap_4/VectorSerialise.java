import java.io.*;
import java.util.*;

public class VectorSerialise
{
	public static void main(String[] args)
			throws 	IOException, ClassNotFoundException
	{
		ObjectOutputStream outStream =
			new ObjectOutputStream(
				new FileOutputStream("personnelvec.dat"));

		Vector<Personnel> staffVectorOut = new Vector<Personnel>();
		Vector<Personnel> staffVectorIn = new Vector<Personnel>();

		Personnel[] staff =
			{new Personnel(123456,"Smith", "John"),
			 new Personnel(234567,"Jones", "Sally Ann"),
			 new Personnel(999999,"Black", "James Paul")};

		for (int i=0; i<staff.length; i++)
			staffVectorOut.add(staff[i]);

		outStream.writeObject(staffVectorOut);

		outStream.close();

		ObjectInputStream inStream =
				new ObjectInputStream(
					new FileInputStream("personnelvec.dat"));

		int staffCount = 0;

		try
		{
			staffVectorIn =
					(Vector<Personnel>)inStream.readObject();
			//The compiler will issue a warning for the above line,
			//but ignore this!

			for (Personnel person:staffVectorIn)
			{
				staffCount++;
				System.out.println(
								"\nStaff member " + staffCount);

				System.out.println(
						"Payroll number: " + person.getPayNum());
				System.out.println(
						"Surname: " + person.getSurname());
				System.out.println(
						"First names: " + person.getFirstNames());
			}
			System.out.println("\n");
		}
		catch (EOFException eofEx)
		{
			System.out.println("\n\n*** End of file ***\n");
			inStream.close();
		}
	}
}

class Personnel implements Serializable
{
	private long payrollNum;
	private String surname;
	private String firstNames;

	public Personnel(long payNum,String sName, String fNames)
	{
		payrollNum = payNum;
		surname = sName;
		firstNames = fNames;
	}

	public long getPayNum()
	{
		return payrollNum;
}

	public String getSurname()
	{
		return surname;
	}

	public String getFirstNames()
	{
		return firstNames;
	}

	public void setSurname(String sName)
	{
		surname = sName;
	}
}
