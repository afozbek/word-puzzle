import java.util.*;

public class VectorTest
{
	public static void main(String[] args)
	{
		Vector<Personnel> staffList =
								new Vector<Personnel>();

		Personnel[] staff =
			{new Personnel(123456,"Smith", "John"),
			 new Personnel(234567,"Jones", "Sally Ann"),
			 new Personnel(999999,"Black", "James Paul")};

		for (int i=0; i<staff.length; i++)
			staffList.add(staff[i]);	//Insert into Vector.

		for (Personnel person:staffList)
		{
			System.out.println(
						"\nPayroll number: " + person.getPayNum());
			System.out.println("Surname: " + person.getSurname());
			System.out.println("First names: "
 										+ person.getFirstNames());
		}
		System.out.println("\n");
	}
}

class Personnel
//As defined in earlier example, but without implementation
//of the Serializable interface.
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
