package com.wordpuzzle.Examples.Chap_4;

import java.io.*;
import java.util.*;

public class Copy
{
	public static void main(String[] arg) throws IOException
	{
		//First check that 2 file names have been supplied...
		if (arg.length < 2)
		{
			System.out.println(
					"You must supply TWO file names.");
			System.out.println("Syntax:");
			System.out.println(
					"    java Copy <source> <destination>");
			return;
		}

		Scanner source = new Scanner(new File(arg[0]));
		PrintWriter destination =
						new PrintWriter(new File(arg[1]));

		String input;
		while (source.hasNext())
		{
			input = source.nextLine();
			destination.println(input);
		}

		source.close();
		destination.close();
	}
}
